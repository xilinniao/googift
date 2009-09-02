<?php
/* SVN FILE: $Id: app_controller.php 7945 2008-12-19 02:16:01Z gwoo $ */
/**
 * Short description for file.
 *
 * This file is application-wide controller file. You can put all
 * application-wide controller-related methods here.
 *
 * PHP versions 4 and 5
 *
 * CakePHP(tm) :  Rapid Development Framework (http://www.cakephp.org)
 * Copyright 2005-2008, Cake Software Foundation, Inc. (http://www.cakefoundation.org)
 *
 * Licensed under The MIT License
 * Redistributions of files must retain the above copyright notice.
 *
 * @filesource
 * @copyright     Copyright 2005-2008, Cake Software Foundation, Inc. (http://www.cakefoundation.org)
 * @link          http://www.cakefoundation.org/projects/info/cakephp CakePHP(tm) Project
 * @package       cake
 * @subpackage    cake.cake.libs.controller
 * @since         CakePHP(tm) v 0.2.9
 * @version       $Revision: 7945 $
 * @modifiedby    $LastChangedBy: gwoo $
 * @lastmodified  $Date: 2008-12-18 18:16:01 -0800 (Thu, 18 Dec 2008) $
 * @license       http://www.opensource.org/licenses/mit-license.php The MIT License
 */
/**
 * This is a placeholder class.
 * Create the same file in app/app_controller.php
 *
 * Add your application-wide methods in the class below, your controllers
 * will inherit them.
 *
 * @package       cake
 * @subpackage    cake.cake.libs.controller
 */
class AppController extends Controller {
	var $helpers = array('ajax', 'javascript');

	var $components = array('Auth', 'Cookie', 'RequestHandler');

        function beforeFilter(){
        	
           $this->Auth->allow('display');
           	
       	   $this->Auth->loginError = "登录失败,错误的用户名或密码!";
           $this->Auth->authError  = "对不起，您没有权限访问该页面！";
           
           $this->Cookie->name = COOKIE_NAME;
           $this->Auth->sessionKey = USER_LOGIN_KEY;
           
           $this->Auth->autoRedirect = false;
           if (isset($this->Auth)) {
            	$this->Auth->authorize = 'controller';
           }
           //$this->Auth->authorize = 'controller';
           
           //$this->Auth->userScope = array('User.confirmed' => '1');

           $this->Auth->loginAction = array('controller' => 'users', 'action' => 'login');
           //$this->Auth->loginRedirect = array('/');
           $this->Auth->logoutRedirect  = array('/');                    
           
           if(!$this->Auth->user('id')) {
               $cookie = $this->Cookie->read(USER_LOGIN_KEY);
                if (!is_null($cookie)) {
                    if (!$this->Auth->login($cookie))
                    {
                    	$this->Cookie->del(USER_LOGIN_KEY);
                    }
                }
           }
      }
      
      function isAuthorized() {    
         return true;    
      }  
      
      function addToNavigatorItem($index, $text, $link) {
		$nav = $this->Session->read(NAVIGATION_PATH_KEY);
		if(!$nav) $nav = array();
		if($index === null) {
			$index = sizeof($nav);
		}
		$nav[$index] = array('text'=>$text, 'link'=>$link);
		for($i = sizeof($nav) - 1; $i>$index; $i--) {
			unset($nav[$i]);
		}
		$this->Session->write(NAVIGATION_PATH_KEY, $nav);
	}

	function getLastStoredLink() {
		$nav = $this->Session->read(NAVIGATION_PATH_KEY);
		if(!$nav || sizeof($nav) == 0) return '/main';
		return $nav[sizeof($nav)-1]['link'];
	}
	
	function getLoginUserFromSession() {
		return $this->Session->read(USER_LOGIN_KEY);
	}
	
	function isAdmin()
	{
		if ($this->Auth->user('role') == 'admin') {
			return true;
		}
		return false;	
	}

	function isEnterPriseUser()
	{
		if ($this->Auth->user('role') == 'enterprise') {
			return true;
		}
		return false;
	}
}
?>