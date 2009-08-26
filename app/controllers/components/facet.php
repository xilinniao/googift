<?php
require_once ("facet/facetbase.php");
class FacetComponent extends Object {

    var $components = array('VectorGenerator', 'Vector');

    /**
     * Get the vector array based on an input string.
     * @param $inputString e.g. 生日 男 23岁
     * @return array e.g. array('531'=>'0.7','641'=>'0.8','1080'=>'0.6')
     */
    function inputStringToVectorArray($inputString) {
        $facetBase = facetbase::getFacetArray();
        $array = array();
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
        return $vector;
    }

    /**
     * keyword array to vector array.
     * @param $array
     * @return unknown_type
     */
    function arrayToVector($array) {
        $facetBase = facetbase::getFacetArray();

        $vector = array();
        foreach ($facetBase as $facetName => $facetArray) {
            require_once ("facet/" . $facetName . ".php");
            $class = new ReflectionClass($facetName);
            if ($class) {
                $method = $class->getMethod("toVector");
                $vector = $method->invoke($class->newInstance(), $array, $vector);
            }
        }
        return $vector;
    }

    function correlateVector($base_array, $multiVectorString) {
        $cors = array();
        $vectorStringArray = $this->Vector->split($multiVectorString);
        foreach ($vectorStringArray as $vectorString) {
            $vectorArray = $this->Vector->deserialize($vectorString);
            array_push($cors, $this->Vector->computeRelation($base_array, $vectorArray));
        }
        return max($cors);
    }


    function getBaseFacetArray() {
        return facetbase::getFacetArray();
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
