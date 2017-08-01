/*global define*/
'use strict';

define(['siav-module', 'constantes'], function (app) {
    app.service('creditosServices', ['$http', '$q', 'CONSTANTES', function ($http, $q, CONSTANTES) {
    	 var contrato = {
    			 buscar : buscar,
    			 guardar : guardar,
    			 refinanciar : refinanciar
    	 };
    	 
    	 function buscar(instalacion){
    		 var request = $http.get(CONSTANTES.SRV.CREDITO_BUSCAR + instalacion);
    		 return (request.then(function(response) {
 	    		return response.data;
	    	}));
    	 }
    	 
    	 function guardar(credito){
    		 var request = $http.put(CONSTANTES.SRV.CREDITO_GUARDAR, credito);
    		 return (request.then(function(response) {
 	    		return response.data;
	    	}));
    	 }
    	 
    	 function refinanciar(credito){
    		 var request = $http.put(CONSTANTES.SRV.CREDITO_REFINANCIAR, credito);
    		 return (request.then(function(response) {
 	    		return response.data;
	    	}));
    	 }
   	 
    	 return contrato;
    }]);
});