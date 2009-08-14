<?php
class SearchgiftComponent extends Object {
	var $components = array (
		'Facet'
	);
	
	function getFacetArray() {
		return $this->Facet->getFacetArray();
	}
	
	function search($allGifts, $keyword_array = null) {
		$matched = Array ();
		$index = 0;
		if ($keyword_array) {
			foreach ($allGifts as $gift) {
				$plus = SearchgiftComponent :: computePlus($gift['Gift']['keywords'], $keyword_array);
				if ($plus > 0) {
					$matched[$index] = $gift;
					$matched[$index]['Gift']['plus'] = $plus;
					$index++;
				}
			}
		}

		function giftComparator($g1, $g2) {
			if($g2['Gift']['plus'] === $g1['Gift']['plus']) return 0;
			return $g2['Gift']['plus'] > $g1['Gift']['plus'] ? 1 : -1;
		}

		if (!empty ($matched)) {
			usort($matched, 'giftComparator');
		}
		return $matched;
	}

	/**
	 * @param $gKeys e.g. 
	 * day{Birthday/Christmas},age{<40};day{SpringFestival},age{>35}
	 */
	function computePlus($gKeys, $inputKeys) {
		if (!$gKeys || !$inputKeys)
			return 0;
		$lines = explode(';', $gKeys);
		$maxPlus = 0;
		foreach ($lines as $line) {
			$plus = $this->Facet->computePlus($line, $inputKeys);
			if ($plus > $maxPlus)
				$maxPlus = $plus;
		}
		return $maxPlus;
	}
}
?>