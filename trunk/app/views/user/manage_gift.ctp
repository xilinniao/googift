<div id="leftMenu"><form id="form" method="post" action="/providers/showGiftsOfProvider">
<ul>
<li><?php echo $html->link('添加商品', 'addGift');?></li>
<li>按供应商过滤：<select id="providerSelect" name="providerSelect">
<option value="">-请选择供应商-</option>
	<?php foreach ($providers as $p){?>
	<option value="<?php echo $p['id']; ?>"><?php echo $p['name']; ?></option>
	<?php }?>
	</select>
</li>
</ul>
<p><?php
//设置 AJAX 选项
$options = array(
        'frequency'   => 0.2,
        'url'  => '/providers/showGiftsOfProvider',
        'update' => 'view'

);
//使用 AjaxHelper 创建 AJAX 动作链接
echo $ajax->observeField('providerSelect', $options);
?></p>
</form>
</div>
<div id="view"></div>

