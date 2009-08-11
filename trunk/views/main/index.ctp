<div id="logo">
	<?php echo $html->image('/img/logo.png'); ?>
</div>

<div id="main">
	<div id="formText">在此输入关键词组合（例如：生日 妻子 29岁）</div>
	<div id="form">
		<form method="post" action="/googift/gifts/">
			<input id="textInput" name="data[Gift][keywords]" type="text"/>
			<input type="submit" value="googift搜索"/>
		</form>
	</div>
	<div id="sideMenu">
		<ul>
			<li><a href="">高级搜索</a></li>
			<li><a href="/googift/helps">使用指南</a></li>
		</ul>
	</div>
</div>
<div id="bottomMenu">
	<ul>
		<li><a href="">节日</a></li>
		<li><a href="">对象</a></li>
		<li><a href="">年龄</a></li>
	</ul>
</div>
<div id="footer">

</div>