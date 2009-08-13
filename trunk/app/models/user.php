<?php
class User extends AppModel {
	var $name = 'User';
	
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
}
?>
