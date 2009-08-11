<script type="text/javascript">
function roleSelectChanges() {
    var roleSelect = document.getElementById("roleSelect");
    var gender = document.getElementById("genderInput");
    var birth = document.getElementById("birthInput");
    var email = document.getElementById("emailInput");
    if(roleSelect.selectedIndex == 1) {
        gender.parentNode.removeChild(gender);
        birth.parentNode.removeChild(birth);
        email.parentNode.removeChild(email);
    }
}

function checkTable() {
    var username = document.getElementById("userName");
    var password = document.getElementById("password");
    var password1 = document.getElementById("password1");

        if(username.value == '') {
            alert('用户名不能为空！');
            username.focus();
            return false;
        }
        if(password.value == '') {
            alert('密码不能为空！');
            password.focus();
            return false;
        }
        if(password.value != password1.value) {
            alert('两次输入的密码不匹配！');
            password.value = '';
            password1.value = '';
            password.focus();
            return false;
        }

        var roleSelect = document.getElementById("roleSelect");
        // personal user
        if(roleSelect.selectedIndex == 0) {
            var genderSelect = document.getElementById("genderSelect");
            if(genderSelect.selectedIndex == 0) {
                alert('请选择性别！');
                genderSelect.focus();
                return false;
            }
            var yearSelect = document.getElementById("UserBirthYearYear");
            if(yearSelect.selectedIndex == 0) {
                alert('请选择出生日期！');
                yearSelect.focus();
                return false;
            }
            var monthSelect = document.getElementById("UserBirthMonthMonth");
            if(monthSelect.selectedIndex == 0) {
                alert('请选择出生日期！');
                monthSelect.focus();
                return false;
            }
            var daySelect = document.getElementById("UserBirthDayDay");
            if(daySelect.selectedIndex == 0) {
                alert('请选择出生日期！');
                daySelect.focus();
                return false;
            }
            var emailInput = document.getElementById("email");
            if(emailInput.value == '') {
                alert('请输入电子邮件！');
                emailInput.focus();
                return false;
            } else {
                var emailPattern = /^([a-z][a-z0-9\_\.]*[a-z0-9])(@)(([a-z0-9][a-z0-9\-]*[a-z0-9][\.])+(com|cn|net|cc|hk|tw|au|uk|de|tv|info|biz))$/;
                if(!emailPattern.exec(emailInput.value)) {
                    alert('电子邮件格式不正确！');
                    emailInput.focus();
                    return false;
                }
            }
        }
        return true;
}
</script>
<?php echo $form->create('User', array ('action' => 'register', 'onsubmit'=>'return checkTable()')); ?>
<table align="center">
<tr><td colspan="2"><font size="14"><b>用户注册</b></font></td></tr>
<tr><td colspan="2"><font color="red"><b><?php $session->flash(); ?></b></font></td></tr>
<tr><td>用户名：</td><td><input id="userName" type="text" name="data[User][username]" maxlength="50"><font color="red"><b>*</b></font></td></tr>
<tr><td>密码：</td><td><input id="password" type="password" name="data[User][password]" maxlength="50"><font color="red"><b>*</b></font></td></tr>
<tr><td>确认密码：</td><td><input id="password1" type="password" name="data[User][password1]" maxlength="50"><font color="red"><b>*</b></font></td></tr>
<tr><td>身份：</td><td><select id="roleSelect" name="data[User][role]" onchange="roleSelectChanges()"><option value="personal">个人用户</option><option value="enterprise">企业用户</option></select><font color="red"><b>*</b></font></td></tr>
<tr id="genderInput"><td>性别：</td><td><select id="genderSelect" name="data[User][gender]"><option value="" selected="selected">-请选择-</option><option value="男">男</option><option value="女">女</option></select><font color="red"><b>*</b></font></td></tr>
<tr id="birthInput"><td>出生日期：</td><td><?php echo $form->year('birthYear', 1920, 2005) . '-' . $form->month('birthMonth') . '-' . $form->day('birthDay'); ?><font color="red"><b>*</b></font></td></tr>
<tr id="emailInput"><td>电子邮件：</td><td><input id="email" type="text" name="data[User][email]" maxlength="50"><font color="red"><b>*</b></font></td></tr>
<tr><td colspan="2"><?php echo $form->end('注册'); ?></td></tr>
</table>
