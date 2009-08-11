<?php
class ProvidersController extends AppController {
	var $name = 'Provider'; 
	
	function index() {
		$this->set('providers', $this->Provider->find('all'));
	}
	
	function addProvider() {}
	
	function add() {
		if(!empty($this->data) && $this->Provider->save($this->data)) {
			$this->set('result', '成功');
		}
		else
			$this->set('result', '失败');
	}
	
	function showOneProvider() {
		$id = $this->params['url']['id'];
		$this->set('provider', $this->Provider->find('first', array('conditions'=>array('id'=>$id))));
	}
	
	function delete() {
		$id = $this->params['url']['id'];
		if($id && $this->Provider->del($id)){
			$this->set('result', '成功');
		} else
			$this->set('result', '失败, id:'.$id);
	}
}
?>
