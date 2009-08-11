<?php
class GiftKnowledgesController extends AppController {
	var $name = "GiftKnowledge";
	
	function index() {
		$category_array = $this->GiftKnowledge->getGiftCates();
		$all = $this->GiftKnowledge->find('all');
		$item_map = array();
		$cateCount_map = array();
		foreach ( $category_array as $key => $value ) {
       		$item_map[$key] = array();
       		$cateCount_map[$key] = array();
		}
		
		foreach ( $all as $item ) {
			$cate = $item['GiftKnowledge']['category'];
			if(!$cateCount_map[$cate]) $cateCount_map[$cate] = 0;
			$item_map[$cate][$cateCount_map[$cate]] = $item['GiftKnowledge'];
			$cateCount_map[$cate]++;
		}
		$this->set('item_map', $item_map);
	}
	
	function showAllInCategory() {
		$category_array = $this->GiftKnowledge->getGiftCates();
		$category = $this->params['url']['category'];
		$this->set('item_map', $this->GiftKnowledge->find('all', array('conditions' => array('category' => $category))));
		$this->set('category_name', $category_array[$category]);
		$this->set('category', $category);
	}
	
	function showOneItem() {
		$category_array = $this->GiftKnowledge->getGiftCates();
		$category = $this->params['url']['category'];
		$this->set('item', $this->GiftKnowledge->find('first'), array('conditions' => array('id' => $this->params['url']['id'])));
		$this->set('category', $category);
		$this->set('category_name', $category_array[$category]);
	}
	
	function addGiftKnowledge() {}
	
	function add() {
		if(!empty($this->data) && $this->GiftKnowledge->save($this->data)) {
			$this->set('result', '成功');
		}
		else
			$this->set('result', '失败');
	}
	
	function delete() {
		$id = $this->params['url']['id'];
		if($id && $this->GiftKnowledge->del($id)){
			$this->set('result', '成功');
		} else
			$this->set('result', '失败, id:'.$id);
	}
}
?>
