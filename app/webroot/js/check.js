function checkInput(inputName, label) 
{
    var input = document.getElementById(inputName).value;
    if (input=='')
    {
       alert ('请输入' + label + '!!');
       return false;
    }
    return true;
}

function checkEmpty(inputObj, labelReferer) 
{
	var value = inputObj.value;
    if (value=='')
    {
       alert (labelReferer + ' 不能为空！');
       inputObj.focus();
       return false;
    }
    return true;
}

function checkPositiveInteger(inputObj, labelReferer) 
{
	var value = inputObj.value;
	var regex=/^[1-9]{1}[0-9]*$/g;
	if(regex.test(value)){
       return true;
	}
	alert (labelReferer + ' 只能为整数！');
	inputObj.focus();
	return false;
}

function checkPositiveDouble(inputObj, labelReferer) 
{
	var value = inputObj.value;
	var regex=/^[0-9]+\.{0,1}[0-9]*$/;
	if(regex.test(value)){
		return true;
	}
	alert (labelReferer + ' 只能为整数或小数！');
	inputObj.focus();
	return false;
}

function isRegisterUserName(s)  
{  
	var patrn=/^[a-zA-Z]{1}([a-zA-Z0-9]|[._]){4,19}$/;  
	if (!patrn.exec(s)) return false;
	return true;
}  

// 去左空格;
function ltrim(s){
    return s.replace( /^\s*/, "");
}

// 去右空格;
function rtrim(s){
    return s.replace( /\s*$/, "");
}

// 去左右空格;
function trim(s){
    return rtrim(ltrim(s));
}

// 是否为空值;
function IsEmpty(_str){
    var tmp_str = trim(_str);
    return tmp_str.length == 0;
}
// 是否有效的Email;
function IsMail(_str){
    var tmp_str = trim(_str);
    var pattern = /^[_a-z0-9-]+(.[_a-z0-9-]+)*@[a-z0-9-]+(.[a-z0-9-]+)*$/;
    return pattern.test(tmp_str);
}
// 是否有效的数字;
function IsNumber(_str){
    var tmp_str = trim(_str);
    var pattern = /^[0-9]/;
    return pattern.test(tmp_str);
}

