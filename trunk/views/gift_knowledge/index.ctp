<?php 
$i = 0; 
foreach ( $item_map as $category => $item ) {
   $i++;
   if($i % 2 === 1) { ?>
   	<div class="section-left">
	<div class="section">
		<div class="title"><?php echo $html->link($category, 'showAllInCategory?category=' . $category); ?></div>
		<div class="items">
			<ul>
			<?php 
			for($i=0;$i<sizeof($item) && $i<6; $i++) { 
			?>
				<li><?php echo $html->link($item[$i]['title'], 'showOneItem?category='.$category.'&id=' . $item[$i]['id']); ?></li>
			<?php } ?>
			</ul>
		</div>
	</div>
</div>
<?php } 
else { ?>
   	<div class="section-right">
	<div class="section">
		<div class="title"><?php echo $html->link($category, 'showAllInCategory?category=' . $category); ?></div>
		<div class="items">
			<ul>
			<?php 
			for($i=0;$i<sizeof($item) && $i<6; $i++) { 
			?>
				<li><?php echo $html->link($item[$i]['title'], 'showOneItem?category='.$category.'&id=' . $item[$i]['id']); ?></li>
			<?php } ?>
			</ul>
		</div>
	</div>
</div>
<?php }
} ?>