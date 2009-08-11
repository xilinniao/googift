<?php
$navigator_array = array (
	'首页'=>'/files',
	'提高礼商'=>'/gift_knowledge'
);
$current = $category_name;
	require('views/layouts/navigator.ctp');
	
?>

<div class="BigRectangle">
	<ul>
		<?php
			foreach ( $item_map as $item ) {
				echo '<li>';
				echo $html->link($item['GiftKnowledge']['title'], 'showOneItem?category='.$category.'&id=' . $item['GiftKnowledge']['id']);
				echo '</li>';
			}
       ?>
	</ul>
</div>
