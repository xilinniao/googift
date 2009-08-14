<?php
class FacetsController extends AppController {
	var $name = 'Facet';
	function addFacet() {
		$this->addToNavigatorItem(2, '添加Facet', '/facets/addFacet');
	}
	
	function addKeyword() {
		$this->set('facets', $this->Facet->find('all', array('conditions'=>array('isCategorical'=>'1'))));
		$this->addToNavigatorItem(2, '添加关键词', '/facets/addKeyword');
	}

	function add() {
		$this->Facet->save($this->data);
		$this->redirect($this->getLastStoredLink());
	}
}

?>