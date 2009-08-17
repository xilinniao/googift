<?php
require_once('facetbase.php');
class age extends facetbase {

	function getName() {
		return 'age';
	}
	
	function getPrefixIndex() {
		return '1';
	}
	
	function getRangeSplits() {
		$ranges = array(
		    '0',
			'1',
			'3',
			'5',
			'10',
			'15',
			'20',
			'25',
			'30',
			'35',
			'40',
			'45',
			'50',
			'60',
			'70',
			'80',
		    '100',
		    ContinuousValueUtil::$POS_INFINITE
		);
		return $ranges;
	}

	function getMatchPatterns() {
		return array (
			'/年龄[:：]?([0-9]+)/',
			'/([0-9]+)岁/'
			);
	}
}
?>
