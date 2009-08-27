<?php
class UsersController extends AppController {
	var $name = 'User';
	var $helpers = array('ajax');
	var $components = array('Facet');

	function beforeFilter() {  
        parent::beforeFilter();
        $this->Auth->allow( 'register','login', 'logout' ); 
    }

    function isAuthorized() {
    	if ($this->action == 'index')
    	{
    		if ($this->Auth->user('role') == 'admin') {
				return true;
			}	
			return false;
    	}
		return true;	
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

	/*
	  code inside this function will execute on
 	  autoRedirect was set to false (i.e. in a beforeFilter).
 	*/
    function login() {
    	//this is for auth bug, which will cache latest url info.
    	$this->Session->del('Auth.redirect'); 
    	
        if ($this->Auth->user()){
            if (!empty($this->data)) {
                if (empty($this->data['User']['remember_me'])) {
                    $this->Cookie->del(USER_LOGIN_KEY);
                }
                else {
                    $cookie = array();
                    $cookie['username'] = $this->data['User']['username'];
                    $cookie['password'] = $this->data['User']['password'];
                    $this->Cookie->write(USER_LOGIN_KEY, $cookie, true, '+2 weeks');
                }
                unset($this->data['User']['remember_me']);
            }
            $this->redirect('/');
        }
        if (empty($this->data)) {
            // Delete invalid Cookie
            $this->Cookie->del(USER_LOGIN_KEY);
            $this->addToNavigatorItem(1, '用户登录', '/users/login');
        }
    }
    
	function logout() {
        if($this->Cookie->read(USER_LOGIN_KEY)){
            $this->Cookie->del(USER_LOGIN_KEY);
        }
        if ($this->Session->check(USER_LOGIN_KEY)) {
            $this->Session->del(USER_LOGIN_KEY);
		}
		$this->redirect('/');
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
		$this->set('facetBaseArray', $this->Facet->getBaseFacetArray());
	}

	function delete() {
		$this->User->del($this->params['url']['id']);
		$this->redirect('index');
	}

}
?>
