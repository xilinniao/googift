<?php
class GiftKnowledge extends AppModel {
	var $name = "GiftKnowledge";
	
	var $GiftCates = array(
		'GiftChina'=>'礼仪之邦',
		'GiftBasic'=>'礼品常识',
		'CommercialEtiquette'=>'商务礼仪',
		'Others'=>'其它知识'
	);
	
	public function getGiftCates() {
		return $this->GiftCates;
	}
}
?>
