<?php
class Gift extends AppModel {
	public static $keywords = 'keywords';
	var $name = "Gift";
	var $belongsTo = array('Provider' =>   
                           array('className'  => 'Provider',   
                                 'conditions' => '',   
                                 'order'      => '',   
                                 'foreignKey' => 'provider_id'  
                           )   
                     );   
	
	function suffixName($suffix = "1") {
		$this->data['name'] = $suffix . $this->data['name'];
	}
}
?>
