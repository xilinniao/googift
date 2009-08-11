<?php
class AdminsController extends AppController {
	var $name = 'Admin';
	var $components = array('Acl');
	
	function index() {
		$this->layout = "admin";
	}
	
	function addARO(){    
		$aro = new Aro();        
		//这是我们需要迭代的所有组信息数组   
		$groups = array(        
		 	0 => array('alias' => 'administrator'), 
		 	1 => array('alias' => 'enterprise')
		);        
		//迭代并且创建ARO组    
		foreach($groups as $data)    {
			//Remember to call create() when saving in loops...        
			$aro->create();                
			//Save data        
			$aro->save($data);
		}    //这儿放置action的其它逻辑...
	}
	
	function test(){}
}
?>
