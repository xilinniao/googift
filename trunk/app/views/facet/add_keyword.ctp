<?php
$facetOptions = array();
foreach ($facets as $facet) {
	$facetOptions[$facet['Facet']['id']] = $facet['Facet']['name'];
}
echo '<div class="title">添加关键词</div>';
?>
<form id="form" method="post" action="../keywords/add">
<?php $session->flash();?>
<table>
	<tr>
		<td>内容：</td>
		<td><input name="data[Keyword][content]" /></td>
	</tr>
	<tr>
		<td>Facet：</td>
		<td><select id="KeywordFacetId" name="data[Keyword][facet_id]">
			<option value="">-请选择-</option>
		<?php foreach ($facetOptions as $id => $name) {
			echo '<option value="' . $id . '">' . $name . '</option>';
		}?>
		</select></td>
	</tr>
	<tr>
		<td>是否主词：</td>
		<td><select name="data[Keyword][isPrimary]">
			<option value="">-请选择-</option>
			<option value="1">是</option>
			<option value="0">否</option>
		</select></td>
	</tr>
	<tr>
		<td>主词：</td>
		<td><select id="KeywordPrimary" name="data[Keyword][primary]">
		</select></td>
	</tr>
	<tr><td><input type="submit" value="添加关键词"/></td><td></td></tr>
</table>
</form>



		<?php
		//设置 AJAX 选项
		$options = array(
        'frequency'   => 0.2,
        'url'  => '/keywords/getPrimaryKeywordsOfFacet',
        'update' => 'KeywordPrimary'

		);
		//使用 AjaxHelper 创建 AJAX 动作链接
		echo $ajax->observeField('KeywordFacetId', $options);
		?>