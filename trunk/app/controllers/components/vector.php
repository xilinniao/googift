<?php
class VectorComponent extends Object {
	function computeRelation($base, $match) {
		$bsize = sizeof($base);
		$msize = sizeof($match);
		
		if($msize === 0) return 0;
		$sum = 0;
		foreach ($base as $dim => $value) {
			if(array_key_exists($dim, $match)) {
				$mv = $match[$dim];
				$sum = $sum + $value * $mv;
			}
		}
		return ($sum * 1.0) / max($bsize, $msize);
	}
}
?>