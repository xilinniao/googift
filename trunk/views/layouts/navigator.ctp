<div class="navigator">
    <?php
    $navigator_array = $session->read(NAVIGATION_PATH_KEY);
    for ( $index = 0; $index < sizeof($navigator_array) - 1; $index++ ) {
        $item = $navigator_array[$index];
        if($item) {
            echo $html->link($item['text'],$item['link']) . " > ";
        }
    }
    echo $navigator_array[sizeof($navigator_array) - 1]['text'];
    ?>
</div>
