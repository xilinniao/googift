<?php
class HelpsController extends AppController {
	var $name = "Help";
	
	function index() {
		$this->addToNavigatorItem(1, '使用帮助', '/helps');
	}
	
	function addHelp() {
		$this->addToNavigatorItem(2, '添加帮助', '/helps/addHelp');
		$this->set('categories', $this->Help->find('all', array('fields' => array('DISTINCT category'))));
	}
	
	function add() {
		if(!empty($this->data) && $this->Help->save($this->data)) {
			$this->set('result', '成功');
		}
		else
			$this->set('result', '失败');
	}
	
	function delete() {
		$id = $this->params['url']['id'];
		if($id && $this->Help->del($id)){
			$this->set('result', '成功');
		} else
			$this->set('result', '失败, id:'.$id);
	}
}
?>
