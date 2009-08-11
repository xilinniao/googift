<?php
class AdvicesController extends AppController {
	var $name = 'Advice';
	
	function index() {
		$this->set('adviceItems', $this->Advice->find('all', array('order' => 'id ASC')));
	}
	
	function add() {
		if(!empty($this->data) && $this->Advice->save($this->data));
	}
	
	function delete() {
		$id = $this->params['url']['id'];
		if($id && $this->Advice->del($id)){
			$this->set('result', '成功');
		} else
			$this->set('result', '失败, id:'.$id);
	}
}
?>
