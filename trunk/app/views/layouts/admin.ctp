<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<?php echo $html->charset(); ?>
	<title>
		<?php echo $title_for_layout; ?>
	</title>
	<?php echo $html->css('admin'); ?>
</head>
<body>
	<div id="header"><?php include('header.ctp') ?></div>
	<div id="content">
		<?php $session->flash(); ?>
		<?php echo $content_for_layout; ?>
	</div>
	<?php echo $cakeDebug; ?>
	<div id="footer"><?php include('footer.ctp') ?></div>
</body>
</html>