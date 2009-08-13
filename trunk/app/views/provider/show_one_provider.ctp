<div class="ariticleTitle"><?php echo $provider['Provider']['full_name']; ?></div>
<div class="centerAligned">电话：<?php echo $provider['Provider']['phone']; ?></div>
<div class="centerAligned">地址：<?php echo $provider['Provider']['address']; ?></div>
<div class="ariticleContent"><?php echo $provider['Provider']['introduction']; ?></div>
<div><?php echo $html->link('修改资料', '/providers/updateProvider?id=' . $provider['Provider']['id']); ?></div>