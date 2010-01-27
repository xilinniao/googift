<%@page language="java" pageEncoding="UTF-8" %>
<%@page import="cn.googift.search.SearchResult, java.util.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
<link rel="stylesheet" type="text/css" href="/googift/css/search_result.css" /><title>Googift - 搜索结果</title>
</head>
<body>
<div id="head">
	<div id="logo">
		<a href="/googift"><img src="/googift/img/logo.png"/></a>
	</div>

	<div id="searchMain">
		<div id="searchForm">
			<form method="get" action="/googift/query">
				<input class="textInput" name="q" type="text" value="<%=request.getAttribute("q")%>" size="50"/>
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
    List<SearchResult> results = (List<SearchResult>) request.getAttribute("results");
%>
<div id="divider">
	<div id="searchSummary">
		搜索&nbsp;“<%=request.getAttribute("q")%>”&nbsp;获得约&nbsp; <%= request.getAttribute("totalHits") %> &nbsp;条查询结果
	</div>
</div>

<% for(SearchResult r : results) { %>
<table class="giftItem" border="0">
    <tr><td class="giftTitle"><a href="<%= r.getProduct().getUrl()%>" target="_blank"><%= r.getProduct().getName()%></a></td></tr>
    <tr><td class="giftPrice"><b>价格：</b><%= r.getProduct().getDiscountPrice()%></td></tr>
    <tr><td class="giftTitle">
    <%
    List<String> picLinks = r.getProduct().getPicLinks();
    if(null != picLinks && picLinks.size() > 0) {
    for(String l : picLinks) {
    %>
    <span class="giftPic"><a href="<%=l%>" target="_blank"><img src="<%=l%>" height="100px" width="120px" border="1"/></a> </span>

    <% } } %>
    </td></tr>
    <tr><td class="giftSnippet"><%= r.getSnippet() %></td></tr>
    <tr><td class="giftUrl"><%= r.getProduct().getUrl()%></td></tr>
</table>
<% } %>

<br>

</body>
</html>