<?php
class GiftKnowledgesController extends AppController {
    var $name = "GiftKnowledge";

    function index() {
        $this->addToNavigatorItem(1, '提高礼商', '/gift_knowledges');
        $categories = $this->GiftKnowledge->find('all', array('fields'=>array('category DISTINCT'), 'order'=>'update_date DESC'));
        $all = $this->GiftKnowledge->find('all');
        $item_map = array();
        $cateCount_map = array();
        foreach ( $categories as $item ) {
        	$category = $item['GiftKnowledge']['category'];
            $item_map[$category] = array();
            $cateCount_map[$category] = array();
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
        $category = $this->params['url']['category'];
    	$this->addToNavigatorItem(2, $category, '/gift_knowledges/showAllInCategory?category=' . $category);
        $this->set('item_map', $this->GiftKnowledge->find('all', array('conditions' => array('category' => $category))));
        $this->set('category', $category);
    }

    function showOneItem() {
        $category = $this->params['url']['category'];
        $id = $this->params['url']['id'];
        $item = $this->GiftKnowledge->find('first', array('conditions' => array('GiftKnowledge.id' => $id)));
        $this->addToNavigatorItem(3, $item['GiftKnowledge']['title'], '/gift_knowledges/showOneItem?category=' . $category . 'id=' . $id);
        $this->set('item', $item);
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
        if($id && $this->GiftKnowledge->del($id)) {
            $this->set('result', '成功');
        } else
            $this->set('result', '失败, id:'.$id);
    }
}
?>
