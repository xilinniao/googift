<script type="text/javascript">
function roleSelectChanges() {
    var roleSelect = document.getElementById("roleSelect");
    var gender = document.getElementById("genderInput");
    var birth = document.getElementById("birthInput");
    var email = document.getElementById("emailInput");
    alert(roleSelect.selectedIndex);
    if(roleSelect.selectedIndex == 1) {
        gender.setAttribute("style", "display:none")
        birth.setAttribute("style", "display:none")
        email.setAttribute("style", "display:none")
    } else {
        gender.setAttribute("style", "display:inline")
        birth.setAttribute("style", "display:inline")
        email.setAttribute("style", "display:inline")
    }
}
</script>
<?php echo $form->create('User', array ('action' => 'register', 'onsubmit'=>'checkTable()')); ?>
<table align="center">
<tr><td colspan="2"><font size="14"><b>用户注册</b></font></td></tr>
<tr><td colspan="2"><font color="red"><b><?php $session->flash('register'); ?></b></font></td></tr>
<tr><td>用户名：</td><td><input type="text" name="data[User][username]" maxlength="50"><font color="red"><b>*</b></font></td></tr>
<tr><td>密码：</td><td><input type="password" name="data[User][password]" maxlength="50"><font color="red"><b>*</b></font></td></tr>
<tr><td>确认密码：</td><td><input type="password" name="data[User][password1]" maxlength="50"><font color="red"><b>*</b></font></td></tr>
<tr><td>身份：</td><td><select id="roleSelect" name="data[User][role]" onchange="roleSelectChanges()"><option value="personal">个人用户</option><option value="enterprise">企业用户</option></select><font color="red"><b>*</b></font></td></tr>
<tr id="genderInput"><td>性别：</td><td><select id="genderSelect" name="data[User][gender]"><option value="" selected="selected">-请选择-</option><option value="男">男</option><option value="女">女</option></select><font color="red"><b>*</b></font></td></tr>
<tr id="birthInput"><td>出生日期：</td><td><?php echo $form->year('birthYear', 1920, 2005) . '-' . $form->month('birthMonth') . '-' . $form->day('birthDay'); ?></td></tr>
<tr id="emailInput"><td>电子邮件：</td><td><input type="text" name="data[User][email]" maxlength="50"><font color="red"><b>*</b></font></td></tr>
<tr><td colspan="2"><?php echo $form->end('注册'); ?></td></tr>
</table>
