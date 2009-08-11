<div class="navigator">
<?php
foreach ( $navigator_array as $text => $link ) {
	echo $html->link($text, $link) . " > ";
}
echo $current;
?>
</div>
