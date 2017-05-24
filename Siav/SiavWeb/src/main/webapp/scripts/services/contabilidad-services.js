/*global define*/
'use strict';

define(['siav-module'], function (app) {
    app.service('contabilidadServices', ['$http', function ($http) {
    	 var contrato = {
    			 consultar : consultar,
    			 guardar : guardar
    	 };
    	 
    	 function consultar(){
    		 var request = $http.get("rest/contabilidad/consultar", {isArray : false});
    		 return (request.then(function(response) {
 	    		return response.data;
	    	}));
    	 }
    	 
    	 function guardar(contabilidad){
    		 var request = $http.post("rest/contabilidad/guardar", contabilidad);
    		 return (request.then(function(response){
    			 return response.data;
    		 }));
    	 }
   	 
    	 return contrato;
    }]);
});