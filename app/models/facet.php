<?php
class Facet extends AppModel {
	var $name = "Facet";
	
	var $hasMany = array('Keyword' =>   
                         array('className'     => 'Keyword',   
                               'conditions'    => array('Keyword.is_primary'=>'1'),
                               'order'         => 'Keyword.id DESC',   
                               'limit'         => '',   
                               'foreignKey'    => 'facet_id',   
                               'dependent'     => true,   
                               'exclusive'     => false,   
                               'finderQuery'   => ''  
                         )   
                  );
}