<?php
class VectorGeneratorComponent extends Object {

    function serialize($vectorArray) {
        $vectorString = '';
        foreach ($vectorArray as $facet => $value) {
            $vectorString = $vectorString . $facet . '{' . $value . '},';
        }
        return substr($vectorString, 0, strlen($vectorString) - 1);
    }
    
    /**
     * Deserialize the vector string to vector array.
     * @param $vectorString Vector string. e.g. day{生日|情人节},age{<20|45~24}
     * @return array Vector array. e.g. array ('day'=>'生日|情人节', 'age'=>'<20|45~24')
     */
    function deserialize($vectorString) {
        $array = array();
        $splits = explode(',', $vectorString);
        foreach ($splits as $split) {
            $index1 = strpos($split, '{');
            $facet = substr($split, 0, $index1);
            $value = substr($split, $index1 + 1, sizeof($split) - $index1 + 1);
            $array[$facet] = $value;
        }
        return $array;
    }

}

?>