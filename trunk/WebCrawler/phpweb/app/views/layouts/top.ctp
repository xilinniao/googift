<div id="top">
	<ul>
		<li><?php echo $html->link('提高礼商', '/gift_knowledges/'); ?></li>
		<li><?php echo $html->link('建言献策', '/advices/'); ?></li>
		
	</ul>
	<ul style="float:right;">
		<?php 
			$loginUser = $session->read(USER_LOGIN_KEY);
			if($loginUser) {
				echo '<li>欢迎您&nbsp;' . $loginUser['username'] . '&nbsp;</li>';
                                if($loginUser['role'] === 'enterprise') {
                                    echo '<li>' . $html->link('管理', '/users/enterpriseManagement') . '</li>';
                                } elseif($loginUser['role'] === 'admin') {
                                	echo '<li>' . $html->link('管理', '/admins') . '</li>';
                                }
                                echo '<li>' . $html->link('注销', '/users/logout') . '</li>';
			} else {
				echo '<li>' . $html->link('登录', '/users/login') . '</li><li>' . $html->link('注册', '/users/register') . '</li>';
			}
		?>
	</ul>
</div>

