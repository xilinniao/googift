<?php
class UsersController extends AppController {
	var $name = 'User';
	
	function index() {

	}
	
	function registerPage(){}
	
	function register() {
		$hasThisName = $this->User->find('count', array('conditions'=>array('username' => $this->data['User']['username'])));
		if($hasThisName > 0) {
			$this->Session->setFlash('该用户名已存在！');
			$this->redirect('registerPage');
		} else {
			if(!empty($this->data['User'])) {
				$this->data['User']['password'] = md5($this->data['User']['password']);
				$result = $this->User->save($this->data);
			}
			$this->redirect('loginPage');
		}
	}	
	
	function loginPage(){}
	
	function login() {
		if (!empty ($this->data['User'])) {
			//$this->user->name=$this->data['User']['name'];
			$user = $this->User->find('first', array('conditions' => array('username' => $this->data['User']['username'])));
			$user = $user['User'];
//			print_r($user);
			if ($user['password'] == md5($this->data['User']['password'])) {
				$this->Session->write(USER_LOGIN_KEY, $this->data['User']['username']);
				$this->redirect('/main');
			} else {
				if (!$user['id']) {
					$this->Session->setFlash('用户名错误！');
				} else {
					$this->Session->setFlash('用户名与密码不匹配！');
				}
				$this->redirect('loginPage');
			}
		}
	}
	
	function logout() {
		echo USER_LOGIN_KEY;
		if ($this->Session->check(USER_LOGIN_KEY)) {
			$this->Session->del(USER_LOGIN_KEY);
		}
		$this->redirect('/main');
	}
	
	function delete() {
		$this->User->del($this->params['url']['id']);
		$this->redirect('index');
	}
	
	function reset() {
		$name = 'aaa';
		$pass = 'aaa';
		$user = $this->user->findByName($name);
		if ($user['User']['id']) {
			$this->user->set($user);
		} else {
			$this->user->set('name', $name);
			$this->user->set('password', md5($pass));
		}
		$ret = $this->user->save();
		if ($ret) {
			$this->flash('update ok!!', '/users');
		} else {
			$this->flash('update ok!!', '/users');
		}
	}
}
?>
