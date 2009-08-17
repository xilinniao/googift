<?php
class KeywordsController extends AppController {
	var $name = "Keyword";
	function add() {
		$thisKeyword = $this->Keyword->find('first', array('conditions'=>array('Keyword.content'=>$this->data['Keyword']['content'])));
		if($thisKeyword['id']) {
			$this->Session->setFlash($this->data['Keyword']['content'] . ' 已存在！');
		} else {
			if($this->data['Keyword']['primary'] === '') $this->data['Keyword']['primary'] = $this->data['Keyword']['id'];
			$this->Keyword->save($this->data);
			$this->Session->setFlash($this->data['Keyword']['content'] . ' 添加成功！');
		}
		$this->redirect($this->getLastStoredLink());
	}

	function getPrimaryKeywordsOfFacet() {
		$facetId = $this->data['Keyword']['facet_id'];
		$this->set('pKeywords', $this->Keyword->find('all', array('conditions'=>array('Keyword.facet_id'=>$facetId, 'is_primary'=>'1'), 'fields' => array('Keyword.id DISTINCT', 'Keyword.content'))));
	}
}

?>