<script type="text/javascript" src="/googift/fckeditor/fckeditor.js"></script>
<h1>添加供货商</h1>
<form action="add" method="post">
	<div>名称：<input type="text" name="data[Provider][name]"/></div>
	<div>全名：<input type="text" name="data[Provider][full_name]"/></div>
	<div>电话：<input type="text" name="data[Provider][phone]"/></div>
	<div>地址：<input type="text" name="data[Provider][address]" size="100"/></div>
<script type="text/javascript">
<!--
var sBasePath = '/googift/fckeditor/'

var oFCKeditor = new FCKeditor( 'data[Provider][introduction]' ) ;
oFCKeditor.BasePath	= sBasePath ;
oFCKeditor.Height	= 300 ;
oFCKeditor.Create() ;
//-->
</script>
<input type="submit" value="提交"/>

<?php
if (array_key_exists('result', $html->params['url'])) {
	echo '提交' . $html->params['url']['result'] . '!';
}
?>
</form>
