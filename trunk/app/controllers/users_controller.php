<?php
class UsersController extends AppController {
	var $name = 'User';
	var $helpers = array('ajax');

	function index() {

	}

	function registerPage() {
		$this->addToNavigatorItem(1, '用户注册', '/users/registerPage');
	}

	function register() {
		$hasThisName = $this->User->find('count', array('conditions'=>array('username' => $this->data['User']['username'])));
		if($hasThisName > 0) {
			$this->Session->setFlash('该用户名已存在！');
			$this->redirect('registerPage');
		} else {
			if(!empty($this->data['User'])) {
				$this->data['User']['password'] = md5($this->data['User']['password']);
				$this->data['User']['birthday'] = $this->data['User']['birthYear']['year'] . '-' . $this->data['User']['birthMonth']['month'] . '-' . $this->data['User']['birthDay']['day'];
				$result = $this->User->save($this->data);
			}
			$this->redirect('loginPage');
		}
	}

	function loginPage() {
		$this->addToNavigatorItem(1, '用户登录', '/users/loginPage');
	}

	function login() {
		if (!empty ($this->data['User'])) {
			//$this->user->name=$this->data['User']['name'];
			$user = $this->User->find('first', array('conditions' => array('username' => $this->data['User']['username'])));
			$user = $user['User'];
			if ($user['password'] == md5($this->data['User']['password'])) {
				$this->Session->write(USER_LOGIN_KEY, $user);
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
		if ($this->Session->check(USER_LOGIN_KEY)) {
			$this->Session->del(USER_LOGIN_KEY);
		}
		$this->redirect('/main');
	}

	function enterpriseManagement() {
		$user = $this->getLoginUserFromSession();
		$this->addToNavigatorItem(1, '供应商管理', '/users/enterpriseManagement');
		$result = $this->User->find('first', array('conditions'=>array('User.id'=>$user['id'])));
		$this->set('providers', $result['Provider']);
	}
	

	function addProvider() {
		$this->addToNavigatorItem(2, '添加供应商', '/users/addProvider');
		$user = $this->getLoginUserFromSession();
		$this->set('user_id', $user['id']);
	}
	

	function manageGift() {
		$user = $this->getLoginUserFromSession();
		$this->addToNavigatorItem(2, '管理商品', '/users/manageGift');
		$result = $this->User->find('first', array('conditions'=>array('id', $user['id'])));
		$this->set('providers', $result['Provider']);
	}
	
	function addGift() {
		$user = $this->getLoginUserFromSession();
		$this->addToNavigatorItem(3, '添加礼品', '/users/addGift');
		$result = $this->User->find('first', array('conditions'=>array('id', $user['id'])));
		$this->set('providers', $result['Provider']);
	}




	function delete() {
		$this->User->del($this->params['url']['id']);
		$this->redirect('index');
	}
}
?>
