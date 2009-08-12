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
	//var $beforeFilter = array('checkAccess');
	var $helpers = array('ajax', 'javascript');

	var $components = array('Acl', 'RequestHandler');

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
	
	function checkAccess() {
		// This part not required. It shows one way to
		// integrate this permission with authentication: login/logout
		// We always put the login_name in the session under
		// the key USER_LOGIN_KEY, even for anonymous users.
		// So whether a user is logged in or not depends on
		// whether this value is ANONY_USER or not. You may
		// choose to implement it some other way (e.g., whether it's
		// set or not.)
		if (!$this->Session->valid()) {
			$this->Session->renew();
		}
		if (!$this->Session->check(USER_LOGIN_KEY)) {
			$this->Session->write(USER_LOGIN_KEY,ANONY_USER);
		}

		// here we check the permissions based on
		// username and controller name (which is
		// is the first part of the URL)
		$user = $this->Session->read(USER_LOGIN_KEY);
		$controller = $this->params['controller'];
		$action = $this->params['action'];
		if ($this->Acl->check($user, "/$controller", $action)) {
			return;
		}else {
			// if anonymous, redirect to login
			// otherwise, give permission error
			if( $user == ANONY_USER) {
				$this->redirect("/authentications/login");
			}else {
				$this->redirect("/pages/permission_denied");
			}
		}
	}
}
?>