<title>添加礼品</title>
</head>
<body>
<h1 align="center">添加礼品</h1>
<!--form method="post" action="/googift/gifts/add">
名称：<input name="data[Gift][name]" type="text">
价格：<input name="data[Gift][price]" type="text">
提供商：<input name="data[Gift][provider_id]" type="text">
<input type="submit" value="提交">
</form>
</body-->
<?php
echo $form->create('Gift', array('action'=>'add')).
$form->input('name').
$form->input('price').
$form->input('provider_id').
$form->hidden('image_url', array('value'=>'200906250929237625.jpg')).
$form->end('提交');
?>
