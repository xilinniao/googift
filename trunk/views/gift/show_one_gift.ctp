<?php
$navigator_array = array (
	'首页'=>'/files',
	'搜索结果'=>'javascript:history.back()'
);
$current = $gift['Gift']['name'];
	require('views/layouts/navigator.ctp');
	
?>
<div class="ariticleTitle"><?php echo $gift['Gift']['name']; ?></div>
<div class="centerAligned bold">价格：<?php echo $gift['Gift']['price']; ?></div>
<div class="centerAligned">供应商：<?php echo $html->link($gift['Provider']['full_name'], '/Providers/showOneProvider?id='.$gift['Provider']['id']); ?></div>
<div class="centerAligned"><?php echo $html->image('/img/giftImage/'.$gift['Gift']['image_url'], array('class'=>'giftPic', 'onerror'=>'this.src=\'/googift/img/noValidPic.jpg\';')); ?></div>
<div class="ariticleContent"><?php echo $gift['Gift']['description']; ?></div>