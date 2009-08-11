<div id="top">
	<ul>
		<li><?php echo $html->link('提高礼商', '/gift_knowledges/'); ?></li>
		<li><?php echo $html->link('建言献策', '/advices/'); ?></a></li>
		
	</ul>
	<ul style="float:right;">
		<?php 
			$loginUser = $session->read('USER_LOGIN_KEY');
			if($loginUser) {
				echo '<li>欢迎您&nbsp;' . $loginUser . '&nbsp;</li><li>' . $html->link('注销', '/users/logout') . '</li>';
			} else {
				echo '<li>' . $html->link('登录', '/users/loginPage') . '</li><li>' . $html->link('注册', '/users/registerPage') . '</li>';
			}
		?>
	</ul>
</div>

