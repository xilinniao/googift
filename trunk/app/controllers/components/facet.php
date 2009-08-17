<?php
class FacetComponent extends Object {

    var $components = array('VectorGenerator');
    /**
     * @param $plusLine e.g. day{Birthday/Christmas},age{<40}
     * @param $keywords The search input. e.g. 生日 妻子 29岁
     */
    public function computePlus($plusLine, $keywords) {
        $facetPlus_array = explode(',', $plusLine);
        $totalPlus = 0;
        foreach ($facetPlus_array as $facetPlus) {
            $index = strpos($facetPlus, "{");
            $facetName = substr($facetPlus, 0, $index);
            $cates = substr($facetPlus, $index +1, strlen($facetPlus) - $index -2);

            require_once ("facet/" . $facetName . ".php");
            $class = new ReflectionClass($facetName);
            if ($class) {
                $method = $class->getMethod("getKeywordPlus");
                $plus = $method->invoke($class->newInstance(), $cates, $keywords);
                $totalPlus += $plus;
            }
        }
        return $totalPlus / sizeof($facetPlus_array);
    }

    /**
     * Given a facet plus values and a keywords input, compute the plus value for this facet to add to the keywords.
     * @param $plusKeys The plus values of this facet. e.g. 生日,情人节 或者 30~40.
     * @param $keywords The keywords user input. e.g. 生日 妻子 29岁
     */
    //	static abstract function getKeywordPlus($plusKeys, $keywords);

    function getFacetArray() {
        $inputString = '女的  50岁 生日 妗子';
        $array = array();
        require_once ("facet/facetbase.php");
        $facetBase = facetbase::getFacetArray();
        foreach ($facetBase as $facetName => $facetArray) {
            require_once ("facet".DS.$facetName.".php");
            $class = new ReflectionClass($facetName);
            if ($class) {
                $method = $class->getMethod("process");
                $array = $method->invoke($class->newInstance(), $inputString, $array);
            }
        }

        $vector = array();
        foreach ($facetBase as $facetName => $facetArray) {
            $class = new ReflectionClass($facetName);
            if ($class) {
                $method = $class->getMethod("toVector");
                $vector = $method->invoke($class->newInstance(), $array, $vector);
            }
        }


//        $array = array(
//            'day' => '情人节|生日',
//            'acceptor'=>'父辈|母亲',
//            'age'=>'<10|35~45',
//            'gender'=>'男|女'
//            );
//            $vector = array();
//            foreach ($facetBase as $facetName => $facetArray) {
//                $class = new ReflectionClass($facetName);
//                if ($class) {
//                    $method = $class->getMethod("toVector");
//                    $vector = $method->invoke($class->newInstance(), $array, $vector);
//                }
//            }
//            return $vector;

    $string = 'day{生日|情人节},age{<20|45~24}';
    $a = $this->VectorGenerator->deserialize($string);
    $s = $this->VectorGenerator->serialize($a);
    $a['original'] = $s;
    return $a;
    }
}
?>
