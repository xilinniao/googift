<?php
class Keyword extends AppModel {
	var $name = 'Keyword';
	
	var $belongsTo = array('Facet' =>   
                           array('className'  => 'Facet',   
                                 'conditions' => array(),   
                                 'order'      => '',   
                                 'foreignKey' => ''  
                           )   
                     );   
}