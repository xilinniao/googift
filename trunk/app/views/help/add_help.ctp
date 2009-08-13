<script type="text/javascript" src="/googift/fckeditor/fckeditor.js"></script>
<h1>添加使用帮助</h1>
<form action="add" method="post">
	<div>标题：<input type="text" name="data[Help][title]"/></div>
	<div>类别：<select id="select" name="data[Help][category]">
<?php
foreach ($categories as $category) {
	echo '<option value="' . $category['Help']['category'] . '">' . $category['Help']['category'] . '</option>';
}
?>
</select>
<input type="button" value="添加" onclick="addCategory()"/>
<script type="text/javascript">
<!--
var sBasePath = '/googift/fckeditor/'

var oFCKeditor = new FCKeditor( 'data[Help][content]' ) ;
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

<script type="text/javascript">
function addCategory() {
	var category=prompt("请输入新的类别","");
        if(category)
        {
        	var option = new Option();
        	option.value = category;
        	option.text = category;
        	document.getElementById("select").add(option);
         }
}
</script>