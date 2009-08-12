<?php
class gender extends facetbase {
	static $categories_array = array (
		'Male' => array (
			'男',
			'男性'
		),
		'Female' => array (
			'女',
			'女性'
		)
	);
	
	function getCategoriesArray() {
		return acceptor :: $categories_array;
	}

	function getWeight() {
		return 0.94;
	}

	function isCategorical() {
		return true;
	}

}
?>
