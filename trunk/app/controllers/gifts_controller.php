<?php
class GiftsController extends AppController {
	var $name = "Gift";

	var $components = array('Searchgift', 'VectorGenerator');

	function test() {
		$allFacets = $this->Searchgift->getFacetArray();
		print_r($allFacets);
	}
	
	function index(){
		$keyword_str = $this->data['Gift']['keywords'];
		$allGift = $this->Gift->find('all');
		$matchedGift = $this->Searchgift->search($allGift, $keyword_str);
		$this->set('gifts', $matchedGift);
		$this->set('searchKey', $keyword_str);
		$this->layout = "searchResult";
	}

	function add() {
		if (((substr($_FILES["file"]["type"], 0, 5) == "image"))&& ($_FILES["file"]["size"] < 500000))
		{
			$tmpFileName = $this->random(15) . '.' . $this->fileext($_FILES["file"]["name"]);
			move_uploaded_file($_FILES["file"]["tmp_name"], GIFT_IMG_ROOT . $tmpFileName);
			$this->data['Gift']['image_url'] = $tmpFileName;
		}
		else
		{
			echo "Invalid file";
		}

		if(!empty($this->data)){
		    $this->data['Gift']['vector'] = $this->VectorGenerator->multiKeywordStringToMultiVectorString($this->data['Gift']['keywords']);
		    if($this->Gift->save($this->data)) {
			    $this->Session->setFlash('添加成功！');
		    }
		}
		else $this->Session->setFlash('添加失败！');
		$this->redirect('/users/addGift');
	}

	function showOneGift() {
		$gid = $this->params['url']['id'];
		$gift = $this->Gift->find('first', array('conditions' => array('Gift.id' => $gid)));
		$this->addToNavigatorItem(null, $gift['Gift']['name'], '/gifts/showOneGift?id=' . $gid);
		$this->set('gift', $gift);
	}

	function delete() {
		$gid = $this->params['url']['id'];
		if($gid && $this->Gift->del($gid)){
			$this->Session->setFlash('删除成功！');
		} else {
			$this->Session->setFlash('删除失败！');
		}
//		print_r( $this->getLastStoredLink());
		$this->redirect($this->getLastStoredLink());
	}
	
	function batchGenerateVectors() {
	    $allGifts = $this->Gift->find('all', array('fields'=>array('id','keywords')));
	    foreach ($allGifts as $gift) {
	    	$gift['Gift']['vector'] = $this->VectorGenerator->multiKeywordStringToMultiVectorString($gift['Gift']['keywords']);
	    	$this->Gift->save($gift);
	    }
	    $this->Session->setFlash('批量更新向量成功！');
	    $this->redirect($this->getLastStoredLink());
	}

	private function random($length)
	{
		$hash = 'CR-';
		$chars = 'ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789abcdefghijklmnopqrstuvwxyz';
		$max = strlen($chars) - 1;
		mt_srand((double)microtime() * 1000000);
		for($i = 0; $i < $length; $i++)
		{
			$hash .= $chars[mt_rand(0, $max)];
		}
		return $hash;
	}

	private function fileext($filename)
	{
		return substr(strrchr($filename, '.'), 1);
	}

}
?>
