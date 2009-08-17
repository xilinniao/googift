<?php
require_once('facetbase.php');
class age extends facetbase {

	function getName() {
		return 'age';
	}

	function getMatchPatterns() {
		return array (
			'/年龄[:：]?([0-9]+)/',
			'/([0-9]+)岁/'
			);
	}
}
?>
