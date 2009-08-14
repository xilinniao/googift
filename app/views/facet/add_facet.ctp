<?php
	echo '<div class="title">添加Facet</div>';
	echo $form->create('Facet') . 
		$form->input('name', array('label'=>'名称：')).
		$form->input('isCategorical', array('label'=>'分类：', 'options'=>array('1'=>'是', '0'=>'否'))).
		$form->end('添加Facet');
		
?>