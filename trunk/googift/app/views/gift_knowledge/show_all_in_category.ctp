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
