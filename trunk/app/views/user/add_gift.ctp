<script type="text/javascript" src="/googift/fckeditor/fckeditor.js"></script>
<h1 align="center">添加礼品</h1>
<div><?php echo $session->flash();?></div>
<form method="post" action="/googift/gifts/add"  onsubmit="return s()">
<script type="text/javascript">

function s() {
	return checkEmpty(document.getElementById('name'), '名称') 
		&& checkEmpty(document.getElementById('price'), '价格') && checkPositiveDouble(document.getElementById('price'), '价格')
		&& checkEmpty(document.getElementById('character'), '特征');
}

</script>
<table width="100%">
	<tr>
		<td>名称：</td>
		<td><input id="name" type="text" name="data[Gift][name]" /></td>
	</tr>
	<tr>
		<td>价格：</td>
		<td><input id="price" type="text" name="data[Gift][price]" /></td>
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
		<td valign="top">特征：</td>
		<td valign="middle"><textarea id="character" name="displayCharat" rows="5" cols="50" wrap="off" style="overflow: scroll" readonly="readonly"></textarea>
			<input type="button" value="添加特征" onclick="show()" />
			<input name="data[Gift][keywords]" id="hiddenKeyword" type="hidden"></input>
		</td>
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
		<td colspan="2"><input type="submit" value="添加礼品"/></td>
	</tr>
</table>
</form>

<script type="text/javascript">
function show()
{
	document.all.markDiv.style.display="block";
	document.all.formWin.style.display="block";
	document.all.markDiv.style.width=Math.max(document.body.scrollWidth, window.screen.availWidth);
	document.all.markDiv.style.height=Math.max(document.body.scrollHeight, window.screen.availHeight);
	document.all.formWin.style.width = 600;
	document.all.formWin.style.left=(window.screen.width - 600) / 2;
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
	alert("close");
	document.all.markDiv.style.display="none";
	document.all.formWin.style.display="none";
    document.all.select.style.display = "block";
}

function addCharact()
{
	var form = document.getElementById("formWinForm");
	var displayValue = '';
	var value = '';
	for(var i=0;i<form.length;i++) {
		if(form.elements[i].name == '') continue;
		var values = getValueOfMultiSelect(form.elements[i]);
		var valueText = '';
		for(var j=0;j<values.length;j++) {
			valueText = valueText + values[j] + '|';
		}
		valueText = valueText.substring(0, valueText.length - 1);
		if(valueText != '') {
			value = value + form.elements[i].id + '{' + valueText + '},';
			displayValue = displayValue + form.elements[i].name + '{' + valueText + '},';
		}
	}
	value = value.substring(0, value.length - 1);
	
	displayValue = displayValue.substring(0, displayValue.length - 1);
	var displayTextValue = document.all.displayCharat.value;
	displayTextValue = displayTextValue + displayValue + "\n";
	document.all.displayCharat.value = displayTextValue;

	var hiddenTextField = document.getElementById("hiddenKeyword");
	var hiddenTextValue = hiddenTextField.value;
	if(hiddenTextValue != '') hiddenTextValue = hiddenTextValue + ";";
	hiddenTextValue = hiddenTextValue + value;
	hiddenTextField.value = hiddenTextValue;
	
	document.all.markDiv.style.display="none";
	document.all.formWin.style.display="none";
    document.all.select.style.display = "block";
}

function getValueOfMultiSelect(selectObj){
	var resultArray = new Array();
	for(var i=0;i<selectObj.options.length;i++){
		if(selectObj.options[i].selected) resultArray.push(selectObj.options[i].value);
	}
	return resultArray;
}
</script>
<div id='markDiv'
	style='position: absolute; display: none; background: #FFF; filter: alpha(opacity = 50); z-index: 2; left: 0; top: 0;'>
</div>
<div id="formWin"
	style='position: absolute; display: none; background: yellow; z-index: 3; top: 100px; border: thin gray solid;'>
<form id="formWinForm">
<table>
<?php foreach ($facetBaseArray as $aFacet) {
    echo '<tr>';
    echo '<td valign="top">' . $aFacet['facet']['label'] . ':</td><td>';
    if($aFacet['facet']['is_categorical'] === '1') {
        echo getCategoricalInput($aFacet);
    } else {
        echo getContinuousInput($aFacet);
    }
    echo '</td></tr>';
}?>
	<tr>
		<td><a href="javascript:addCharact()">添加</a></td>
		<td><a href="javascript:close()">关闭</a></td>
	</tr>
</table>
</form>

</div>

<?php
function getCategoricalInput($catFacet) {
    $options = array();
    foreach ($catFacet['keywords'] as $priKey => $keywordArray) {
        $options[$priKey] = $priKey;
    }
    $optionHtml = '';
    foreach ($options as $value => $text) {
        $optionHtml = $optionHtml . '<option value="' . $value . '">' . $text . '</option>';
    }
    return '<select id="' . $catFacet['facet']['name'] . '" size="'.giveAProperSize(sizeof($options)).'" name="' . $catFacet['facet']['label'] . '" multiple="multiple">' . $optionHtml . '</select>';
}

function getContinuousInput($contFacet) {
    $options = array();
    foreach ($contFacet['ranges'] as $ranges) {
        array_push($options, $ranges);
    }
    $optionHtml = '';
    foreach ($options as $value) {
        $optionHtml = $optionHtml . '<option value="' . $value . '">' . $value . '</option>';
    }
    return '<select id="' . $contFacet['facet']['name'] . '" size="'.giveAProperSize(sizeof($options)).'" name="' . $contFacet['facet']['label'] . '" multiple="multiple">' . $optionHtml . '</select>';
}

function giveAProperSize($itemNumber) {
    if($itemNumber < 10) return $itemNumber;
    else return 10;
}

?>