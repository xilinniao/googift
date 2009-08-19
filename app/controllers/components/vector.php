<?php
class VectorComponent extends Object {
    const GROUP_SEPARATOR = ';';
    const ITEM_SEPARATOR = ',';
    const KEY_VALUE_SEPARATOR = ':';
    
    function computeRelation($vectorArray_base, $vectorArray_match) {
        $bsize = sizeof($vectorArray_base);
        $msize = sizeof($vectorArray_match);

        if($msize === 0) return 0;
        $sum = 0;
        foreach ($vectorArray_base as $dim => $value) {
            if(array_key_exists($dim, $vectorArray_match)) {
                $mv = $vectorArray_match[$dim];
                $sum = $sum + $value * $mv;
            }
        }
        return ($sum * 1.0) / max($bsize, $msize);
    }

    function split($vectorGroup) {
        return explode(self::GROUP_SEPARATOR, $vectorGroup);
    }

    function merge($vectorStringArray){
        $multi = '';
        foreach ($vectorStringArray as $vectorString) {
            $multi = $multi . $vectorString . self::GROUP_SEPARATOR;
        }
        return substr($multi, 0, strlen($multi) - 1);
    }

    /**
     * array('21'=>'0.8', '33'=>'0.76') to (21,0.8);(33,0.76)
     * @param $vectorArray
     * @return unknown_type
     */
    function serialize($vectorArray) {
        $vectorString = '';
        foreach ($vectorArray as $dim => $value) {
            $vectorString = $vectorString . '(' . $dim . self::KEY_VALUE_SEPARATOR . $value . ')' . self::ITEM_SEPARATOR;
        }
        return substr($vectorString, 0, strlen($vectorString) - 1);
    }

    /**
     * (21,0.8);(33,0.76) to array('21'=>'0.8', '33'=>'0.76')
     * @param $vectorString
     * @return array Vector
     */
    function deserialize($vectorString) {
        $array = array();
        $splits = explode(self::ITEM_SEPARATOR, $vectorString);
        foreach ($splits as $split) {
            $index1 = strpos($split, self::KEY_VALUE_SEPARATOR);
            $dim = substr($split, 1, $index1 + 1);
            $value = substr($split, $index1 + 1, strlen($split) - $index1 - 2);
            $array[$dim] = $value;
        }
        return $array;
    }
}
?>