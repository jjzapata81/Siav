/*global define*/
'use strict';

define(['siav-module'], function (app) {
    app.service('creditosServices', ['$http', '$q', function ($http, $q) {
    	 var contrato = {
    			 buscar : buscar,
    			 guardar : guardar,
    			 refinanciar : refinanciar
    	 };
    	 
    	 function buscar(instalacion){
    		 var request = $http.get("rest/creditos/buscar/" + instalacion);
    		 return (request.then(function(response) {
 	    		return response.data;
	    	}));
    	 }
    	 
    	 function guardar(credito){
    		 var request = $http.put("rest/creditos/guardar", credito);
    		 return (request.then(function(response) {
 	    		return response.data;
	    	}));
    	 }
    	 
    	 function refinanciar(credito){
    		 var request = $http.put("rest/creditos/refinanciar", credito);
    		 return (request.then(function(response) {
 	    		return response.data;
	    	}));
    	 }
   	 
    	 return contrato;
    }]);
});