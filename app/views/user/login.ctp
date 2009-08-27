<h1>用户登录</h1>
<?php
echo '<font color="red"><b>';
$session->flash();
$session->flash('auth');
echo '</b></font>';
echo $form->create('User', array ('action' => 'login'));
echo $form->input('username', array ('label' => '用户名：'));
echo $form->input('password', array ('label' => '密&nbsp;&nbsp;&nbsp;码：'));
echo $form->checkbox('remember_me');
echo $form->label('2周内不用再登录');
echo $form->end('Login');
?>
