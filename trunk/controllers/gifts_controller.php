<?php
class GiftsController extends AppController {
	var $name = "Gift";
	
	var $components = array('Searchgift');
	
	function index(){
		$keyword_str = $this->data['Gift']['keywords'];
		$allGift = $this->Gift->find('all');
		$matchedGift = $this->Searchgift->search($allGift, $keyword_str);
		$this->set('gifts', $matchedGift);
		$this->set('searchKey', $keyword_str);
		$this->layout = "searchResult";
	}
	
	function addGift(){
		
	}
	
	function add() {
		if(!empty($this->data) && $this->Gift->save($this->data)) {
			$this->set('result', '成功');
		}
		else
			$this->set('result', '失败');
	}
	
	function showOneGift() {
		$gid = $this->params['url']['id'];
		$gift = $this->Gift->find('first', array('conditions' => array('Gift.id' => $gid)));
		$this->set('gift', $gift);
	}
	
	function delete() {
		$gid = $this->params['url']['id'];
		if($gid && $this->Gift->del($gid)){
			$this->set('result', '成功');
		} else
			$this->set('result', '失败, id:'.$gid);
	}
}
?>
