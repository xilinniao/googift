<h1>用户登录</h1>
<?php
$session->flash();
echo $form->create('User', array ('action' => 'login'));
echo $form->input('username', array ('label' => '用户名：'));
echo $form->input('password', array ('label' => '密&nbsp;&nbsp;&nbsp;码：'));
echo $form->end('Login');
?>
