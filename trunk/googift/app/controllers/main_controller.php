<?php
class MainController extends AppController {
    function index() {
        $this->addToNavigatorItem(0, '首页', '/main');
        $this->layout = 'mainPage';
    }
}
?>
