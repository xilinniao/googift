<%@page language="java" pageEncoding="UTF-8" %>
<%@page import="cn.googift.search.SearchResult, cn.googift.search.Searcher" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Map" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
<link rel="stylesheet" type="text/css" href="css/search_result.css" /><title>Googift - 搜索结果</title>
<%!
    int page = 1;
    Searcher searcher = null;
    int totalHits;
    String q;
%>
</head>
<body>
<%
    System.out.println("searcher = " + searcher);
    Object qStr = request.getAttribute("q");
    if(qStr != null) {
        this.q = (String)qStr;
    }
    Object attribute = request.getAttribute("searcher");
    if(null != attribute) searcher = (Searcher) attribute;
    Object totalHitsObj = request.getAttribute("totalHits");
    if(null != totalHitsObj) this.totalHits = (Integer)totalHitsObj;

    String pageParam = request.getParameter("page");
    if(null != pageParam) this.page = Integer.parseInt(pageParam);
    List<SearchResult> results = searcher.getPage(this.page);
%>
<div id="head">
	<div id="logo">
		<a href="./"><img src="img/logo.png"/></a>
	</div>

	<div id="searchMain">
		<div id="searchForm">
			<form method="get" action="./query">
				<input class="textInput" name="q" type="text" value="<%=this.q%>" size="50"/>
				<input class="buttonInput" type="submit" value="googift搜索"/>
			</form>
		</div>
		<div id="sideMenu">
			<ul>
				<li><a href="advanced.htm">高级搜索</a></li>
			</ul>
		</div>
	</div>
</div>

<div id="divider">
	<div id="searchSummary">
		搜索&nbsp;“<%=this.q%>”&nbsp;获得约&nbsp; <%= this.totalHits %> &nbsp;条查询结果
	</div>
</div>

<% for(SearchResult r : results) { %>
<table class="giftItem" border="0">
    <tr><td class="giftTitle"><a href="<%= r.getProduct().getUrl()%>" target="_blank"><%= r.getProduct().getName()%></a></td></tr>
    <tr><td class="giftPrice"><b>价格：</b><%= r.getProduct().getDiscountPrice()==null?"空缺":r.getProduct().getDiscountPrice()%></td></tr>
    <tr><td class="giftTitle">
    <%
    Map<String, String> picLinks = r.getProduct().getPicLinks();
    if(null != picLinks && picLinks.size() > 0) {
    for(Map.Entry<String, String> p : picLinks.entrySet()) {
    %>
    <span class="giftPic"><a href="<%=p.getValue()%>" target="_blank"><img src="<%=p.getKey()%>" height="100px" border="1" alt="商品图片" onerror = "this.src='img/noValidPic.jpg'"/></a> </span>

    <% } } %>
    </td></tr>
    <tr><td class="giftSnippet"><%= r.getSnippet() %></td></tr>
    <tr><td class="giftUrl"><%= r.getProduct().getUrl()%></td></tr>
</table>
<% } %>

<br>

<div id="pageLinks">
    <%
        int startPage = this.page / 10 + 1;
        int endPage = Math.min(this.totalHits / 10 + (this.totalHits % 10 == 0 ? 0 : 1), this.page / 10 + 10);
    %>
    <table>
        <tr>
            <td><% if(this.page > startPage) {%><a href="result.jsp?page=<%=(this.page-1)%>"><%}%>前一页<% if(this.page > startPage) {%></a><%}%> </td>
            <%
                for(int i=startPage;i<=endPage;i++) {
            %>
            <td><%if(i != this.page) {%><a href="result.jsp?page=<%=i%>"><%=i%></a><%}else {out.print(i);}%> </td>
            <%
                }
             %>
            <td><%if(this.page < endPage) {%><a href="result.jsp?page=<%=(this.page+1)%>"><%}%>后一页<%if(this.page < endPage) {%></a><%}%></td>
        </tr>
    </table>
</div>
</body>
</html>