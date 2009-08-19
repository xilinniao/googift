<?php
class VectorGeneratorComponent extends Object {
    var $components = array('Keyword', 'Vector', 'Facet'); 
    
    /**
     * age{>12|1~5},gender{男};age(45~65),acceptor(母亲) to (1030:0.7),(1040,0.7),(2100,0.8);(1110:0.7),(1120,0.7),(2130,0.8)
     * @param $multiKeywordString
     * @return unknown_type
     */
    function multiKeywordStringToMultiVectorString($multiKeywordString) {
        if(!$multiKeywordString || sizeof($multiKeywordString) === 0) return '';
        $keywordStringArray = $this->Keyword->split($multiKeywordString);
        $vectorStringArray = array();
        foreach ($keywordStringArray as $keywordString) {
        	$keywordArray = $this->Keyword->deserialize($keywordString);
        	array_push($vectorStringArray, $this->Vector->serialize($this->Facet->arrayToVector($keywordArray)));
        }
        echo $this->Vector->merge($vectorStringArray);
        return $this->Vector->merge($vectorStringArray);
    }

}

?>