<?php
require_once('facetbase.php');
class acceptor extends facetbase {
	static $categories_array = array (
		'Husband' => array (
			'丈夫',
			'老公'
		),
		'Wife' => array (
			'妻子',
			'老婆'
		),
		'BoyFriend' => array (
			'男朋友',
			'男友',
			'BF',
			'bf'
		),
		'GirlFriend' => array (
			'女朋友',
			'女友',
			'GF',
			'gf'
		),
		'GoodFriend' => array (
			'好朋友',
			'好友',
			'死党'
		),
		'Friend' => array (
			'普通朋友',
			'朋友'
		),
		'Colleague' => array (
			'同事',
			'同僚'
		),
		'ClassMate' => array (
			'同学',
			'同校',
			'校友'
		),
		'Leader' => array (
			'领导',
			'经理',
			'主任',
			'manager',
			'leader'
		),
		'Father' => array (
			'父亲',
			'爸爸',
			'爸'
		),
		'Mother' => array (
			'母亲',
			'妈妈',
			'妈'
		),
		'FatherInLaw' => array (
			'岳父',
			'丈人',
			'老丈人',
			'外父'
		),
		'MotherInLaw' => array (
			'岳母',
			'丈母娘',
			'外母'
		),
		'Son' => array (
			'儿子'
		),
		'Daughter' => array (
			'女儿'
		),
		'Uncle' => array (
			'父辈','舅舅','叔叔','伯父','姨父','姑父'
		),
		'Aunt' => array (
			'母辈','姨姨','阿姨','婶婶','舅妈','姑姑','妗母','妗子'
		)
	);
	
	function getCategoriesArray(){
		return acceptor::$categories_array;
	}
	
	function getWeight(){
		return 0.94;
	}
	
	function isCategorical() {
		return true;
	}
}
?>
