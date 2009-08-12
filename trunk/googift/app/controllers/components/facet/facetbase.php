<?php
abstract class facetbase extends Object {
	abstract function getWeight();
	abstract function isCategorical();
	function getCategoriesArray() {
		return null;
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
