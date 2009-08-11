<?php
$navigator_array = array (
	'首页'=>'/files',
	'供货商列表'=>'/providers/'
);
$current = $provider['Provider']['name'];
	require('views/layouts/navigator.ctp');
	
?>
<div class="ariticleTitle"><?php echo $provider['Provider']['full_name']; ?></div>
<div class="centerAligned">电话：<?php echo $provider['Provider']['phone']; ?></div>
<div class="centerAligned">地址：<?php echo $provider['Provider']['address']; ?></div>
<div class="ariticleContent"><?php echo $provider['Provider']['introduction']; ?></div>
