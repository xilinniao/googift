<?php
echo '<option value="">-请选择-</option>';
foreach ($pKeywords as $pk) {
	echo '<option value="' . $pk['Keyword']['id'] . '">' . $pk['Keyword']['content'] . '</option>';
}
?>