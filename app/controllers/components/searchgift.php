<?php
class SearchgiftComponent extends Object {
	var $components = array (
		'Facet'
	);
	
	function getFacetArray() {
		return $this->Facet->getFacetArray();
	}
	
	function search($allGifts, $inputString = null) {
		$matched = Array ();
		if ($inputString) {
		    $inputArray = $this->Facet->inputStringToVectorArray($inputString);
			foreach ($allGifts as $gift) {
			    $gift['Gift']['plus'] = $this->Facet->correlateVector($inputArray, $gift['Gift']['keywords']);
			    array_push($matched, $gift);
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

}
?>