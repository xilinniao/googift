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