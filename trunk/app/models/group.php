<?php
class Group extends AppModel {

	var $name = 'Group';
	
	var $hasMany = array(
			'User' => array(
				'className' => 'User',
				'foreignKey' => 'group_id',
				'dependent' => false,
				'conditions' => '',
				'fields' => '',
				'order' => '',
				'limit' => '',
				'offset' => '',
				'exclusive' => '',
				'finderQuery' => '',
				'counterQuery' => ''
			)
	);
	
	function listVisibleUserGroups() {
		$results = $this->find( 'list', array( 
			'conditions'=>array('Group.is_visible'=>'1'),
			'fields' => array('Group.id', 'Group.title'),
			'order' => 'Group.id ASC' ));		
		return $results;
	}

}
?>