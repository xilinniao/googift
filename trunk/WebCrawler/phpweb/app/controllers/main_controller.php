<?php
class MainController extends AppController {
	var $name = "Main";
	var $useTable = false;
	
	function index() {
		$this->layout = 'MainPage';
	}
}
?>