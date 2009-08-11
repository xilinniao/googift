<?php
class AdvicesController extends AppController {
    var $name = 'Advice';

    function index() {
        $this->addToNavigatorItem(1, '建言献策', '/advices');
        $this->set('adviceItems', $this->Advice->find('all', array('order' => 'id ASC')));
    }

    function add() {
        if(!empty($this->data) && $this->Advice->save($this->data));
        $this->redirect('index');
    }

    function delete() {
        $id = $this->params['url']['id'];
        if($id && $this->Advice->del($id)) {
            $this->set('result', '成功');
        } else
            $this->set('result', '失败, id:'.$id);
    }
}
?>
