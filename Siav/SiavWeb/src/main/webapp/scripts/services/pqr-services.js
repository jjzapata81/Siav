/*global define*/
'use strict';

define(['siav-module', 'constantes'], function (app) {
    app.service('pqrServices', ['$http', 'CONSTANTES', function ($http, CONSTANTES) {
    	 var contrato = {
    			 consultar : consultar,
    			 crear : crear
    	 };
    	 
    	 function consultar(){
    		 var request = $http.get(CONSTANTES.SRV.PQR_CONSULTAR, {isArray : true});
    		 return (request.then(function(response) {
 	    		return response.data;
	    	}));
    	 }
    	 
    	 function crear(request){
    		 var request = $http.post(CONSTANTES.SRV.PQR_CREAR, request);
    		 return (request.then(function(response) {
 	    		return response.data;
	    	}));
    	 }
   	 
    	 return contrato;
    }]);
});