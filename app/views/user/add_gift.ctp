<script type="text/javascript" src="/googift/fckeditor/fckeditor.js"></script>
<h1 align="center">添加礼品</h1>
<div><?php echo $session->flash();?></div>
<form method="post" action="/googift/gifts/add">
<table width="100%">
	<tr>
		<td>名称：</td>
		<td><input type="text" name="data[Gift][name]" /></td>
	</tr>
	<tr>
		<td>价格：</td>
		<td><input type="text" name="data[Gift][price]" /></td>
	</tr>
	<tr>
		<td>供应商：</td>
		<td><select id="select" name="data[Gift][provider_id]"
			style="visibility: visible">
			<?php foreach ($providers as $p) {?>
			<option value="<?php echo $p['id'];?>"><?php echo $p['name'];?></option>
			<?php } ?>
		</select></td>
	</tr>
	<tr>
		<td>图片：</td>
		<td><input name="file" type="file" value="浏览..." /></td>
	</tr>
	<tr>
		<td colspan="2"><script type="text/javascript">
	<!--
	var sBasePath = '/googift/fckeditor/';

	var oFCKeditor = new FCKeditor( 'data[Gift][description]' ) ;
	oFCKeditor.BasePath	= sBasePath ;
	oFCKeditor.Height	= 300 ;
	oFCKeditor.Create() ;
	//-->
	</script></td>
	</tr>
	<tr>
		<td>特征：</td>
		<td><textarea disabled="disabled"></textarea><input type="button"
			value="添加特征" onclick="show()" /></td>
	</tr>
	<tr>
		<td colspan="2"><input type="submit" value="添加礼品" /></td>
	</tr>
</table>
</form>

<script type="text/javascript">
function show()
{
	document.all.markDiv.style.display="block";
	document.all.markDiv.style.width=Math.max(document.body.scrollWidth, window.screen.availWidth);
	document.all.markDiv.style.height=Math.max(document.body.scrollHeight, window.screen.availHeight);
    document.all.select.style.display = "none";

//    页可见区域宽： document.body.clientWidth
//    网页可见区域高： document.body.clientHeight
//    网页可见区域宽： document.body.offsetWidth (包括边线的宽)
//    网页可见区域高： document.body.offsetHeight (包括边线的高)
//    网页正文全文宽： document.body.scrollWidth
//    网页正文全文高： document.body.scrollHeight
//    网页被卷去的高： document.body.scrollTop
//    网页被卷去的左： document.body.scrollLeft
//    网页正文部分上： window.screenTop
//    网页正文部分左： window.screenLeft
//    屏幕分辨率的高： window.screen.height
//    屏幕分辨率的宽： window.screen.width
//    屏幕可用工作区高度： window.screen.availHeight
//    屏幕可用工作区宽度： window.screen.availWidth
}

function close()
{
	document.all.markDiv.style.display="none";
    document.all.select.style.display = "block";
}
</script>
<div id='markDiv' style='position:absolute;display:none;background:#000;filter:alpha(opacity=30);z-index:2;left:0;top:0;width:1000px;height:2000px;'>
	<a href="javascript:close()">关闭该页</a>
</div>
