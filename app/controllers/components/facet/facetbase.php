<?php
require_once 'app' . DS . 'models' . DS . 'facet.php';
require_once 'app' . DS . 'models' . DS . 'keyword.php';
abstract class facetbase extends Object {
	/**
	 * array (
	 * 		'day' => array(
	 * 					'facet'=>array ('id'=>'1', 'name'=>'day', 'is_categorical'=>'1', 'weight'=>'0.7'),
	 * 					'keywords' => array (
	 * 										'生日'=>array(),
	 * 										'七夕'=>array('中国情人节'),
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
				echo 'here';
			$facet =& new Facet();
			$facetResult = $facet->find('all');
			foreach ($facetResult as $item) {
				$facetName = $item['Facet']['name'];
				$value_array = array();
				$keyword_array = array();
				$value_array['facet'] = $item['Facet'];
				foreach ($item['Keyword'] as $keyword) {
					$keyword_array[$keyword['content']] = array();
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
				array_push(self::$facet_array[$relateFacet['Facet']['name']]['keywords'][$primarySynonym['Keyword']['content']], $item['Keyword']['content']);
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
	 * @param $vector
	 */
	function process($inputString, $vector) {
		$baseArray = self::getFacetArray();
		$facetArray = $baseArray[$this->getName()];
		if('1' === $facetArray['facet']['is_categorical']) {
			foreach ($facetArray['keywords'] as $primary => $nonKeywords) {
				$regex = '/( ' . $primary . ' )';
				foreach ($nonKeywords as $nk) {
					$regex = $regex . '|( ' . $nk . ' )';
				}
				$regex = $regex . '/';
				if (preg_match($regex, ' '.$inputString.' ')) {
					$vector[$this->getName()] = $primary;
					break;
				}
			}
		} else {
			$patterns = $this->getMatchPatterns();
			if(!$patterns) return $vector;
			foreach ($patterns as $pattern) {
				if (preg_match($pattern, ' '.$inputString.' ', $matches)) {
					$number = $matches[1];
					$vector[$this->getName()] = $number;
					break;
				}
			}
		}
		return $vector;
	}
	
	function getMatchPatterns() {
		return null;
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
