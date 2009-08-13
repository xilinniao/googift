<script type="text/javascript" src="/googift/fckeditor/fckeditor.js"></script>
<h1>添加供货商</h1>
<?php $session->flash(); ?>
<?php 
echo $form->create('Provider', '/providers/add'). 
$form->input('name', array('label'=>'名称：')).
$form->input('full_name', array('label'=>'全名：')).
$form->input('phone', array('label'=>'电话：')).
$form->input('address', array('label'=>'地址：', 'size'=>'100'));
?>
<script type="text/javascript">
<!--
var sBasePath = '/googift/fckeditor/'

var oFCKeditor = new FCKeditor( 'data[Provider][introduction]' ) ;
oFCKeditor.BasePath	= sBasePath ;
oFCKeditor.Height	= 300 ;
oFCKeditor.Create() ;
//-->
</script>

<?php echo $form->end('添加供应商'); ?>
