<?php
/*
  id int(11)
  name varchar(255)
  full_name varchar(255)
  introduction text
  phone varchar(20)
  address varchar(255)
  created datetime
  modified datetime
 */
 class Provider extends AppModel {
 	var $name = "Provider";
 	var $hasMany = array('Gift' =>   
                         array('className'     => 'Gift',   
                               'conditions'    => '',   
                               'order'         => 'Gift.modified DESC',   
                               'limit'         => '',   
                               'foreignKey'    => 'provider_id',   
                               'dependent'     => true,   
                               'exclusive'     => false,   
                               'finderQuery'   => ''  
                         )   
                  );  
    var $belongsTo = array('User' =>   
                           array('className'  => 'User',   
                                 'conditions' => array('User.role'=>'enterprise'),   
                                 'order'      => '',   
                                 'foreignKey' => ''  
                           )   
                     );   
 }
?>
