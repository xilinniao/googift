<div id="head">
	<div id="logo">
		<a href="/googift/files"><?php echo $html->image('logo.png'); ?></a>
	</div>

	<div id="searchMain">
		<div id="searchForm">
			<form method="post" action="/googift/gifts/">
				<input class="textInput" name="data[Gift][keywords]" type="text"/>
				<input class="buttonInput" type="submit" value="googift搜索"/>
			</form>
		</div>
		<div id="sideMenu">
			<ul>
				<li><a href="">高级搜索</a></li>
				<li><a href="/googift/helps">使用指南</a></li>
			</ul>
		</div>
	</div>
</div>

<div id="divider">
	<div id="searchSummary">
		<?php echo '搜索&nbsp;' . $searchKey . '&nbsp;获得&nbsp;' . sizeof($gifts) . '&nbsp;条查询结果'; ?>
	</div>
</div>

<?php
foreach($gifts as $gift){
?>
<div class="giftItem">
	<div class="giftPic"><?php echo $html->image('/img/giftImage/'.$gift['Gift']['image_url'], array('class'=>'giftPic', 'onerror'=>'this.src=\'/googift/img/noValidPic.jpg\';')); ?></div>
	<div class="giftTitle"><?php echo $html->link($gift['Gift']['name'], '/gifts/showOneGift?id='.$gift['Gift']['id']); ?></div>
	<div class="giftDescription"><?php echo $gift['Gift']['description']; ?></div>
	<div class="giftPrice">价格：<?php echo $gift['Gift']['price']; ?></div>
</div>
<?php
}
//debug($gifts);
?>
</table>
<br>
