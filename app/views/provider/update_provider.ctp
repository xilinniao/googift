<script type="text/javascript" src="/googift/fckeditor/fckeditor.js"></script>
<h1>修改供货商信息</h1>
<?php $session->flash(); ?>
<?php 
echo $form->create('Provider', array('action'=>'update')) . 
$form->input('id', array('type'=>'hidden', 'value'=>$provider['id'])) .
$form->input('name', array('label'=>'名称：', 'value'=>$provider['name'])).
$form->input('full_name', array('label'=>'全名：', 'value'=>$provider['full_name'])).
$form->input('phone', array('label'=>'电话：', 'value'=>$provider['phone'])).
$form->input('address', array('label'=>'地址：', 'size'=>'100', 'value'=>$provider['address']));
?>
<script type="text/javascript">
<!--
var sBasePath = '/googift/fckeditor/'

var oFCKeditor = new FCKeditor( 'data[Provider][introduction]' ) ;
oFCKeditor.BasePath	= sBasePath ;
oFCKeditor.Height	= 300 ;
oFCKeditor.Value = "<?php echo $provider['introduction']; ?>";
oFCKeditor.Create() ;
//-->
</script>

<?php echo $form->end('提交修改'); ?>
