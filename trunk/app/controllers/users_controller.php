<?php
class UsersController extends AppController {
	var $name = 'User';
	var $helpers = array('ajax');
	var $components = array('Auth');
	
		
	function beforeFilter(){ 
    	$this->Auth->allow('register'); 
    }

	function index() {
	}

	function register() {
		//如果有注册数据,则验证
		if (!empty($this->data)) {
			if(isset($this->data['User']['password2'])){
				$this->data['User']['password2hashed'] = 
    				$this->Auth->password($this->data['User']['password2']);
			} 
			$this->User->create();
			if ($this->User->save($this->data)) {
				$this->Session->setFlash('Congratulations! You have signed up!');  
				$this->redirect('login');
			}
		}	
		//如果没有注册数据,则直接显示注册页面
		else{
			$this->addToNavigatorItem(1, '用户注册', '/users/register');
		}
	}

	function login() {
		//如果有登录数据,则验证
		if (!empty($this->data)) {
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
					$this->redirect('login');
				}
			}
		}
	    //如果没有登录数据,则直接显示登录页面
		else{
			$this->addToNavigatorItem(1, '用户登录', '/users/login');
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
