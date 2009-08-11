<?php
$navigator_array = array (
	'首页'=>'/files'
);
$current = '提高礼商';
	require('views/layouts/navigator.ctp');
	
?>

<div class="section-left">
	<div class="section">
		<div class="title"><?php echo $html->link('礼仪之邦', 'showAllInCategory?category=GiftChina'); ?></div>
		<div class="items">
			<ul>
			<?php 
			for($i=0;$i<sizeof($item_map['GiftChina']) && $i<6; $i++) { 
			?>
				<li><?php echo $html->link($item_map['GiftChina'][$i]['title'], 'showOneItem?category=GiftChina&id=' . $item_map['GiftChina'][$i]['id']); ?></li>
			<?php } ?>
			</ul>
		</div>
	</div>
</div>
	
<div class="section-right">
	<div class="section">
		<div class="title">礼仪之邦</div>
		<div class="items">
			<ul>
			<?php 
			for($i=0;$i<sizeof($item_map['GiftChina']) && $i<6; $i++) { 
			?>
				<li><?php echo $html->link($item_map['GiftChina'][$i]['title'], 'showOneItem?category=GiftChina&id=' . $item_map['GiftChina'][$i]['id']); ?></li>
			<?php } ?>
			</ul>
		</div>
	</div>
</div>
