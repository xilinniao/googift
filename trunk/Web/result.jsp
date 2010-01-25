<%@page language="java" pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
<link rel="stylesheet" type="text/css" href="/googift/css/search_result.css" /><title>Googift - 搜索结果</title>
</head>
<body>
<div id="head">
	<div id="logo">
		<a href="/"><img src="/googift/img/logo.png"/></a>
	</div>

	<div id="searchMain">
		<div id="searchForm">
			<form method="post" action="/query">
				<input class="textInput" name="q" type="text"/>
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
		搜索&nbsp;&nbsp;获得约&nbsp; 20 &nbsp;条查询结果
	</div>
</div>

<div class="giftItem">
	<div class="giftPic">

	</div>
	<div class="giftTitle"></div>
	<div class="giftPrice">价格：</div>
</div>

<div>

</div>
<br>

</body>
</html>