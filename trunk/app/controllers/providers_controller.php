<?php
class ProvidersController extends AppController {
	var $name = 'Provider';

	function index() {
		$this->set('providers', $this->Provider->find('all'));
	}

	function add() {
		if(!empty($this->data) && $this->Provider->save($this->data)) {
			$this->Session->setFlash('添加成功！');
		}
		else
		$this->Session->setFlash('添加失败！');
		$this->redirect('addProvider?user_id=' . $this->data['Provider']['user_id']);
	}


	function showGiftsOfProvider() {
		$pid = $this->params['form']['providerSelect'];
		$result = $this->Provider->find('first', array('conditions'=>array('provider.id'=>$pid)));
		$this->set('gifts', $result['Gift']);
	}

	function showOneProvider() {
		$id = $this->params['url']['id'];
		$this->set('provider', $this->Provider->find('first', array('conditions'=>array('provider.id'=>$id))));
	}

	function delete() {
		$id = $this->params['url']['id'];
		if($id && $this->Provider->del($id)){
			$this->set('result', '成功');
		} else
		$this->set('result', '失败, id:'.$id);
		$this->redirect($this->getLastStoredLink());
	}
}
?>
