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
		<td><select id="select" name="data[Gift][provider_id]" style="visibility:visible">
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
		<td><textarea disabled="disabled"></textarea><input	type="button" value="添加特征" onclick="show()" /></td>
	</tr>
	<tr>
		<td colspan="2"><input type="submit" value="添加礼品" /></td>
	</tr>
</table>
</form>


<script type="text/javascript">
function show()
{
	var h=document.documentElement.scrollHeight; 
    var w=document.documentElement.scrollWidth; 
	var div=document.createElement("<div style='position:absolute;visibility:visible;background:#000;filter:alpha(opacity=30);z-index:2;left:0;top:0;width:"+w+"px;height:"+h+"px;'></div>"); 
    div.appendChild(document.createTextNode("xxxxxxxxxxxxxxxxxxxx")); 
    document.body.appendChild(div); 
    invisibleSelects();
}

function invisibleSelects() {
	document.getElementsById("select").style.visibility = "hidden";
}
function closes()
{
    if(window.confirm("关闭这个层"))
    {
        document.getElementById("divTest").style.visibility="hidden";
        document.all.ly.style.display='none';
    }
}
</script>
<div id="divTest"
	style="position: absolute; z-index: 3; width: 540; height: 170px; background-color: Yellow; display: none; top: 100px; left: 100px;">
<!--<div id="dd"-->
<!--	style="background-color: Red; width: 365px; height: 20px; float: left;"-->
<!--	onmousedown="down()"></div>-->
<!--<div style="background-color: Red; width: 35px; height: 20px;"><a-->
<!--	onclick="closes()">关闭</a></div>-->
</div>


