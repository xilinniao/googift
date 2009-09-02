<?php
class MainController extends AppController {
	
	function beforeFilter() {  
        parent::beforeFilter();
        $this->Auth->allow('index'); 
    }
	
	function index() {
        $this->addToNavigatorItem(0, '首页', '/');
        $this->layout = 'mainPage';
    }
}
?>
