<?php
require_once('facetbase.php');
class day extends facetbase {

	static $categories_array = array (
		'Birthday' => array (
			'生日',
			'birthday'
		),
		'Valentine' => array (
			'情人节',
			'valentine'
		),
		'ChineseValentine' => array (
			'七夕',
			'中国情人节'
		),
		'Christmas' => array (
			'圣诞',
			'圣诞节'
		),
		'SpringFestival' => array (
			'春节',
			'过年'
		),
		'NewYear' => array (
			'元旦',
			'新年'
		)
	);

	function getCategoriesArray(){
		return day::$categories_array;
	}
	
	function getWeight(){
		return 0.9;
	}
	
	function isCategorical() {
		return true;
	}
}
?>
