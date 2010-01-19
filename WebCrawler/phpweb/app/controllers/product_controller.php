<?php
class ProductController extends AppController {
	var $name = "Product";
	var $helpers = array('Paginator');
	var $paginate = array('limit' => 10);
	
//	var $components = array('Searchgift', 'VectorGenerator');

	function test() {
		$allFacets = $this->Searchgift->getFacetArray();
		print_r($allFacets);
	}
	var $searchKey;
	function index(){
		$keyword_str = $this->data['Product']['keywords'];
		if(NULL === $keyword_str) $keyword_str = $this->params['named']['q'];
		
		$allGift = $this->paginate('Product', array('name LIKE'=> '%' . $keyword_str . '%'));
//		$matchedGift = $this->Searchgift->search($allGift, $keyword_str);
		$this->set('gifts', $allGift);
		$this->set('searchKey', $keyword_str);
		$this->layout = "searchResult";
	}

}
?>
