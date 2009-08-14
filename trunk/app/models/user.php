<?php
class User extends AppModel {
	var $name = 'User';
	
	var $validate = array(
						'username' => array(
							'notempty' => array(
		   						'rule' => array('minLenght', 1),
	   					    	'required' => true,
								'allowEmpty' => false,
								'message' => '请输入用户名!'
							),
							'unique' => array(
								'rule' => array('checkUnique', 'username'),
								'message' => '该用户名已经存在,请换一个!'
							)		
					    ),
						'password' => array(
							'notempty' => array(
								'rule' => array('minLenght', 1),
								'required' => true,
								'allowEmpty' => false,
								'message' => '请输入密码!' 	  
							),
							'passwordSimilar' => array(
								'rule' => 'checkPasswords',
								'message' => '两次输入的密码不匹配!'
							)		
						),
						'email' => array( 
							'rule' => 'email',
							'required' => true,
							'allowEmpty' => false,
							'message' => '请输入有效的Email地址！'
						)
					);			
			
	var $hasMany = array('Provider' =>   
                         array('className'     => 'Provider',   
                               'conditions'    => '',
                               'order'         => 'Provider.modified DESC',   
                               'limit'         => '',   
                               'foreignKey'    => '',   
                               'dependent'     => true,   
                               'exclusive'     => false,   
                               'finderQuery'   => ''  
                         )   
                  );
          
	function checkUnique($data, $fieldName) 
	{ 
        $valid = false; 
        if(isset($fieldName) && $this->hasField($fieldName)) 
        { 
        	$valid = $this->isUnique(array($fieldName => $data)); 
        } 
        return $valid; 
   }           
   
   function checkPasswords($data) 
   {
   		if($data['password'] == $this->data['User']['password2hashed'])
   			return true;
		return false;   					
   }	         
}
?>
