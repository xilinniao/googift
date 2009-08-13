<?php
class User extends AppModel {
	var $name = 'User';
	
	var $hasMany = array('Provider' =>   
                         array('className'     => 'Provider',   
                               'conditions'    => '',
                               'order'         => 'Provider.modified ASC',   
                               'limit'         => '',   
                               'foreignKey'    => 'user_id',   
                               'dependent'     => true,   
                               'exclusive'     => false,   
                               'finderQuery'   => ''  
                         )   
                  );
}
?>
