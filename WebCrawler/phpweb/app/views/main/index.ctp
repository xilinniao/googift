<div id="logo">
	<?php echo $html->image('/img/logo.png'); ?>
</div>

<div id="main">
	<div id="formText">在此输入关键词组合（例如：生日 妻子 29岁）</div>
	<div id="form">
		<form method="post" action="<?php echo $html->url(array('controller'=>'product','action'=>''))?>">
			<input id="textInput" name="data[Product][keywords]" type="text"/>
			<input type="submit" value="googift搜索"/>
		</form>
	</div>
	<div id="sideMenu">
		<ul>
			<li><a href="">高级搜索</a></li>
		</ul>
	</div>
</div>
<div id="footer">

</div>