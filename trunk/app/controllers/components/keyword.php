<?php
class KeywordComponent extends Object {
    const GROUP_SEPARATOR = ';';
    const ITEM_SEPARATOR = ',';
    
    function split($keywordGroup) {
        return explode(self::GROUP_SEPARATOR,$keywordGroup);
    }

    function merge($keywordStringArray){
        $multi = '';
        foreach ($keywordStringArray as $keywordString) {
            $multi = $multi . $keywordString . self::GROUP_SEPARATOR;
        }
        return substr($multi, 0, strlen($multi) - 1);
    }

    function serialize($keywordArray) {
        $keywordString = '';
        foreach ($keywordArray as $facet => $value) {
            $keywordString = $keywordString . $facet . '{' . $value . '}' . self::ITEM_SEPARATOR;
        }
        return substr($keywordString, 0, strlen($keywordString) - 1);
    }

    /**
     * day{生日|情人节},age{<20|45~24} to array ('day'=>'生日|情人节', 'age'=>'<20|45~24')
     */
    function deserialize($keywordString) {
        $array = array();
        $splits = explode(',', $keywordString);
        foreach ($splits as $split) {
            $index1 = strpos($split, '{');
            $facet = substr($split, 0, $index1);
            $value = substr($split, $index1 + 1, strlen($split) - $index1 - 2);
            $array[$facet] = $value;
        }
        return $array;
    }
}
?>