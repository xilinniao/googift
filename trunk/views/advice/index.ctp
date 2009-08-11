<?php
$navigator_array = array (
	'首页'=>'/files'
);
$current = '建言献策';
	require('views/layouts/navigator.ctp');
?>
<table id="forumTable">
	<?php 
	if(sizeof($adviceItems) == 0) {
		echo '还未有用户提交建议，请抢占沙发！';
	}
	
		foreach ( $adviceItems as $item ) {
			if($item['Advice']['content']) {
	?>
     <tr>
     	<td id="forumTableLeftColumn">
     		<div class="floorAutherName"><?php echo $item['Advice']['sender']; ?></div>
     		<div class="floorSendDate"><?php echo $item['Advice']['date']; ?></div>
     	</td>
     	<td id="forumTableRightColumn"><div class="floorContent"><?php echo $item['Advice']['content']; ?></div></td>
     </t>
	<?php
			}
		}
	?>
</table>

<hr>
请在此发表建议（支持HTML标记进行格式化）：
<div class="formContainer">
	<?php 
		echo $form->create('Advice', array('action'=>'add', 'class'=>'form', 'onsubmit'=>'return check()')).
			$form->input('sender', array('label'=>'称呼：')).
			$form->input('content', array('type'=>'textarea', 'label'=>'内容：')) .
			$form->end('提交');
	?>
</div>
<script type="text/javascript">
function check() 
{
	return checkInput('data[Advice][sender]', '称呼') && checkInput('data[Advice][content]','内容');
}
</script>
