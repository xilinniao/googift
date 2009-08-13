<script type="text/javascript" src="/googift/fckeditor/fckeditor.js"></script>
<h1 align="center">添加礼品</h1>
<div><?php echo $session->flash();?></div>
<form method="post" action="/googift/gifts/add">
<table width="100%">
  <tr><td>名称：</td><td><input type="text" name="data[Gift][name]" /></td></tr>
  <tr><td>价格：</td><td><input type="text" name="data[Gift][price]" /></td></tr>
  <tr><td>供应商：</td><td><select name="data[Gift][provider_id]"><?php foreach ($providers as $p) {?><option value="<?php echo $p['id'];?>"><?php echo $p['name'];?></option><?php } ?></select></td></tr>
  <tr><td>图片：</td><td><input name="file" type="file" value="浏览..." /></td></tr>
  <tr><td colspan="2">
  	<script type="text/javascript">
	<!--
	var sBasePath = '/googift/fckeditor/'

	var oFCKeditor = new FCKeditor( 'data[Gift][description]' ) ;
	oFCKeditor.BasePath	= sBasePath ;
	oFCKeditor.Height	= 300 ;
	oFCKeditor.Create() ;
	//-->
	</script>
	</td></tr>
  <tr><td colspan="2"><input type="submit" value="添加礼品" /></td></tr>
</table>
</form>
