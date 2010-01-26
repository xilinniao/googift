<%@ page import="org.apache.openjpa.persistence.criteria.Expressions" %>
<%@page language="java" pageEncoding="UTF-8" %>
<%@page import="cn.googift.crawler.data.*, java.util.*" %>
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
<%
    List<Product> results = (List<Product>) request.getAttribute("results");
%>
<div id="divider">
	<div id="searchSummary">
		搜索&nbsp;&nbsp;获得约&nbsp; <%= results.size() %> &nbsp;条查询结果
	</div>
</div>
<% for(Product p : results) { %>
<div class="giftItem">
	<div class="giftPic">

	</div>
	<div class="giftTitle"><%= p.getName()%></div>
	<div class="giftPrice">价格：<%= p.getDiscountPrice()%></div>
    <div><%= p.getDescription()%></div>
</div>

<div>

</div>
<br>

</body>
</html>