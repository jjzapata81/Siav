/*global define*/
'use strict';

define(['siav-module'], function (app) {
    app.service('causasServices', ['$http', '$q', function ($http, $q) {
    	 var contrato = {
    			 consultar : consultar,
    			 consultarDescripciones : consultarDescripciones,
    			 crear : crear,
    			 cambiarEstado : cambiarEstado
    	 };
    	 
    	 function crear(causa){
    		 var request = $http.post("rest/nolectura/crear", causa);
    		 return (request.then(function(response) {
 	    		return response.data;
	    	}));
    	 }
    	 
    	 function cambiarEstado(causa){
    		 var request = $http.put("rest/nolectura/activar", causa);
    		 return (request.then(function(response) {
 	    		return response.data;
	    	}));
    	 }
    	 
    	 function consultar(){
    		 var request = $http.get("rest/nolectura/consultar", {isArray : true});
    		 return (request.then(function(response) {
 	    		return response.data;
	    	}));
    	 }
    	 
    	 function consultarDescripciones(){
    		 var request = $http.get("rest/nolectura/consultar/descripciones", {isArray : true});
    		 return (request.then(function(response) {
 	    		return response.data;
	    	}));
    	 }
   	 
    	 return contrato;
    }]);
});