<?php
class Product extends AppModel {
	public static $keywords = 'keywords';
	var $name = "Product";
	
	function suffixName($suffix = "1") {
		$this->data['name'] = $suffix . $this->data['name'];
	}
}
?>
