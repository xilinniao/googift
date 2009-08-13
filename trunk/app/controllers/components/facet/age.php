<?php
require_once('facetbase.php');
class age extends facetbase {
	
	function getWeight(){
		return 0.8;
	}
	
	function isCategorical() {
		return false;
	}
	
	function getMatchPatterns() {
		return array (
			'/年龄[:：]?([0-9]+)/',
			'/([0-9]+)岁/'
		);
	}
}
?>
