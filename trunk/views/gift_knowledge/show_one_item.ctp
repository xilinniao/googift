<?php
$navigator_array = array (
	'首页'=>'/files',
	'提高礼商'=>'/gift_knowledge',
	$category_name => 'showAllInCategory?category='.$category
);

$gk = $item['GiftKnowledge'];
$current = $gk['title'];
	require('views/layouts/navigator.ctp');
?>

<?php 
	
?>

<div class="ariticleTitle"><?php echo $gk['title']; ?></div>
<div class="ariticleContent"><?php echo $gk['content']; ?></div>