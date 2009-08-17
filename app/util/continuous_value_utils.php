<?php
class ContinuousValueUtil extends Object {
    static $POS_INFINITE = '+\u221E';
    static $NEG_INFINITE = '-\u221E';
    static function parseStringToRange($value) {
        $startKey = substr($value, 0, 1);
        if ($startKey === '>') {
            return array(substr($value, 1), self::$POS_INFINITE);
        }

        if($startKey === '<') {
            return array(self::$NEG_INFINITE, substr($value, 1));
        }

        $index = strpos($value, '~');
        if ($index) {
            $start = substr($value, 0, $index);
            $end = substr($value, $index +1);
            return array($start, $end);
        }
        return array($value, $value);
    }
    
    static function isIntersect($array1, $array2) {
        if(self::compare($array1[1], $array2[0]) < 0 || self::compare($array1[0], $array2[1]) > 0) return false;
        return true;
    }
    
    static function compare($arrayBoundary1, $arrayBoundary2) {
        if($arrayBoundary1 === self::$POS_INFINITE || $arrayBoundary2 === self::$NEG_INFINITE) {
            if($arrayBoundary1 === $arrayBoundary2) return 0;
            return 1;
        } 
        if($arrayBoundary1 === self::$NEG_INFINITE || $arrayBoundary2 === self::$POS_INFINITE) {
            if($arrayBoundary1 === $arrayBoundary2) return 0;
            return -1;
        } 
        return $arrayBoundary1 - $arrayBoundary2;
    }
}
?>