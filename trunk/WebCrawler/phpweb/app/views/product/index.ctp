<?php $paginator->options(array('url'=>array('q'=>$searchKey))); ?>

<div id="head">
	<div id="logo">
		<a href="<?php echo $html->url(array('controller'=>'main','action'=>'index')) ?>"><?php echo $html->image('logo.png'); ?></a>
	</div>

	<div id="searchMain">
		<div id="searchForm">
			<form method="post" action="<?php echo $html->url(array('controller'=>'product','action'=>'index') )?>">
				<input class="textInput" name="data[Product][keywords]" type="text"/>
				<input class="buttonInput" type="submit" value="googift搜索"/>
			</form>
		</div>
		<div id="sideMenu">
			<ul>
				<li><a href="">高级搜索</a></li>
			</ul>
		</div>
	</div>
</div>

<div id="divider">
	<div id="searchSummary">
		<?php echo '搜索&nbsp;' . $searchKey . '&nbsp;获得约&nbsp;' . $paginator->counter(array('format' => '%count%')) . '&nbsp;条查询结果'; ?>
	</div>
</div>

<?php
foreach($gifts as $gift){
?>
<div class="giftItem">
	<div class="giftPic">
	<?php
		$picLinks = $gift['Product']['resouce_link'];
		if(null == $picLinks || strlen($picLinks) == 0) {
			echo $html->image('noValidPic.jpg', array('class'=>'giftPic'));
		} else {
			$linkArray = explode(' ', $gift['Product']['resouce_link']);
			echo '<table border="0" cellpadding="0" cellspacing="0"><tr>';
			foreach ($linkArray as $picLink) {
				echo '<td><a href="' . $picLink . '" target="_blank">';
				echo $html->image($picLink, array('class'=>'giftPic', 'width'=>'150px', 'height'=>'100px'));
				echo '</a></td>';
			}
			echo '</tr></table>';
		}
	?>
	</div>
	<div class="giftTitle"><?php echo $html->link($gift['Product']['name'], $gift['Product']['url']); ?></div>
	<div class="giftPrice">价格：<?php echo $gift['Product']['discount_price']; ?></div>
</div>
<?php
}
//debug($gifts);
?>
<div>
<?php    
	echo $paginator->prev('« 前一页 ', null, null, array('class' => 'disabled')); 
	echo '&nbsp;';
	echo $paginator->numbers();   
	echo '&nbsp;';
	echo $paginator->next(' 后一页  »', null, null, array('class' => 'disabled'));
?> 
</div>
<br>
