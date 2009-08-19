<?php
require_once 'app' . DS . 'models' . DS . 'facet.php';
require_once 'app' . DS . 'models' . DS . 'keyword.php';
require_once 'app' . DS . 'util' . DS . 'continuous_value_utils.php';
abstract class facetbase extends Object {
    /**
     * array (
     * 		'day' => array(
     * 					'facet'=>array ('id'=>'1', 'name'=>'day', 'is_categorical'=>'1', 'weight'=>'0.7'),
     * 					'keywords' => array (
     * 										'生日'=>array('12',array()),
     * 										'七夕'=>array('25', array(中国情人节')),
     * 										...
     * 									)
     * 					)
     * 		...
     * 	)
     * @var unknown_type
     */
    static $facet_array;

    static function getFacetArray() {
        if(!self::$facet_array) {
            self::$facet_array = array();
            $facet =& new Facet();
            $facetResult = $facet->find('all');
            foreach ($facetResult as $item) {
                $facetName = $item['Facet']['name'];
                $value_array = array();
                $keyword_array = array();
                $value_array['facet'] = $item['Facet'];
                foreach ($item['Keyword'] as $keyword) {
                    $keyword_array[$keyword['content']] = array();
                    $keyword_array[$keyword['content']][0] = $keyword['id'];
                    $keyword_array[$keyword['content']][1] = array();
                }
                $value_array['keywords'] = $keyword_array;
                self::$facet_array[$facetName] = $value_array;
            }

            $keyword =& new Keyword();
            $keywordResult = $keyword->find('all', array('conditions'=>array('is_primary'=>'0'), 'fields'=>array('content DISTINCT','primary', 'facet_id')));
            foreach ($keywordResult as $item) {
                $primary_id = $item['Keyword']['primary'];
                $primarySynonym = $keyword->find('first', array('conditions'=>array('Keyword.id'=>$primary_id)));
                $relateFacet = $facet->find('first', array('conditions'=>array('Facet.id'=>$item['Keyword']['facet_id'])));
                array_push(self::$facet_array[$relateFacet['Facet']['name']]['keywords'][$primarySynonym['Keyword']['content']][1], $item['Keyword']['content']);
            }
        }
        return self::$facet_array;
    }

    abstract function getName();
    /**
     * array (
     * 		'day' => '生日',
     * 		'acceptor'=>'丈夫',
     * 		'age' => '25~60',
     * 		'gender' => '男'
     * )
     * @param $array
     */
    function process($inputString, $array) {
        $baseArray = self::getFacetArray();
        $facetArray = $baseArray[$this->getName()];
        if('1' === $facetArray['facet']['is_categorical']) {
            foreach ($facetArray['keywords'] as $primary => $nonKeywords) {
                $regex = '/( ' . $primary . ' )';
                foreach ($nonKeywords[1] as $nk) {
                    $regex = $regex . '|( ' . $nk . ' )';
                }
                $regex = $regex . '/';
                if (preg_match($regex, ' '.$inputString.' ')) {
                    $array[$this->getName()] = $primary;
                    break;
                }
            }
        } else {
            $patterns = $this->getMatchPatterns();
            if(!$patterns) return $array;
            foreach ($patterns as $pattern) {
                if (preg_match($pattern, ' '.$inputString.' ', $matches)) {
                    $number = $matches[1];
                    $array[$this->getName()] = $number;
                    break;
                }
            }
        }
        return $array;
    }

    static $CATEGORICAL_SUFFIX = "1";
    static $CONTINUOUS_SUFFIX = "0";
    /**
     *
     * @param $array The array return by process();
     * 				e.g. array(
     * 						'day'=>'生日|情人节',
     * 						'age'=>'<25|>60';
     * 						}
     * @param $vector array('21'=>'0.5', '54'=>'0.9');
     */
    function toVector($array, $vector) {
//        print_r($array);
        if(!array_key_exists($this->getName(), $array)) return $vector;
        $value = $array[$this->getName()];
        if(!$value) return $vector;
        $baseArray = self::getFacetArray();
        $facetArray = $baseArray[$this->getName()];
        if('1' === $facetArray['facet']['is_categorical']) {
            $valueArray = explode('|', $value);
            foreach ($valueArray as $aValue) {
                $keyword_id = $facetArray['keywords'][$aValue][0];
                $vector[$keyword_id . self::$CATEGORICAL_SUFFIX] = $facetArray['facet']['weight'];
            }
        } else {
            $rangeSplits = $this->getRangeSplits();
            if(!$rangeSplits) return $vector;
            $valueArray = explode('|', $value);
            foreach ($valueArray as $aValue) {
                $valueRange = ContinuousValueUtil::parseStringToRange($aValue);
                $lower = $rangeSplits[0];
                for ($i = 1; $i<sizeof($rangeSplits); $i++) {
                    $r = array($lower, $rangeSplits[$i]);
                    $lower = $rangeSplits[$i];
                    if(ContinuousValueUtil::isIntersect($r, $valueRange)) {
                        $dimension = $this->getPrefixIndex();
                        // Fix the length to 2.
                        if($i < 10) $dimension = $dimension . '0' . $i . self::$CONTINUOUS_SUFFIX;
                        else $dimension = $dimension . $i . self::$CONTINUOUS_SUFFIX;
                        $vector[$dimension] = $facetArray['facet']['weight'];
                    }
                }
            }
        }
        return $vector;
    }

    function getMatchPatterns() {
        return null;
    }

    function getRangeSplits() {
        return null;
    }

    function getPrefixIndex() {
        return 0;
    }

    function getKeywordPlus($plusKeys, $keywords) {
        $categorical = $this->isCategorical();
        if($categorical) {
            return $this->getCategoricalFacetPlus($plusKeys, $keywords);
        }
        else {
            return $this->getContinuousFacetPlus($plusKeys, $keywords);
        }
    }

    function getCategoricalFacetPlus($plusKeys, $keywords) {
        if (!$keywords)
        return 0;
        $cate_array = explode("/", $plusKeys);
        if (empty ($cate_array))
        return 0;
        $categories_array = $this->getCategoriesArray();
        foreach ($cate_array as $cate) {
            $words = $categories_array[$cate];

            if (!$words || empty ($words))
            continue;
            foreach ($words as $word) {
                if (mb_substr_count(" " . $keywords . " ", " " . $word . " ") > 0) {
                    return $this->getWeight();
                }
            }
        }
        return 0;
    }

    function getContinuousFacetPlus($plusKeys, $keywords) {
        if (!$keywords)
        return 0;
        $patterns = $this->getMatchPatterns();
        if(!$patterns) return 0;
        foreach ($patterns as $pattern) {
            if (preg_match($pattern, $keywords, $matches)) {
                $age_i = $matches[1];

                $startKey = substr($plusKeys, 0, 1);
                $age_p = substr($plusKeys, 1);
                if (($startKey === '>' && $age_i >= $age_p) || ($startKey === '<' && $age_i <= $age_p) || ($startKey === '=' && $age_i = $age_p)) {
                    return $this->getWeight();
                } else {
                    $index = strpos($plusKeys, '~');
                    if ($index) {
                        $start = substr($plusKeys, 0, $index);
                        $end = substr($plusKeys, $index +1);
                        if ($age_i > $start && $age_i < $end)
                        return $this->getWeight();
                    }
                }
            }
        }
        return 0;
    }

}
?>
