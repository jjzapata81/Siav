/*global define*/
'use strict';

define(['siav-module', 'constantes'], function (app) {
    app.service('bancosServices', ['$http', '$q', 'CONSTANTES', function ($http, $q, CONSTANTES) {
    	 var contrato = {
    			 consultar : consultar,
    			 crear : crear, 
    			 editar : editar
    	 };
    	 
    	 function crear(banco){
    		 var request = $http.post(CONSTANTES.SRV.CREAR_BANCO, banco);
    		 return (request.then(function(response) {
 	    		return response.data;
	    	}));
    	 }
    	 
    	 function editar(banco){
    		 var request = $http.post(CONSTANTES.SRV.EDITAR_BANCO, banco);
    		 return (request.then(function(response) {
 	    		return response.data;
	    	}));
    	 }
    	 
    	 function consultar(){
    		 var request = $http.get(CONSTANTES.SRV.CONSULTAR_BANCO, {isArray : true});
    		 return (request.then(function(response) {
 	    		return response.data;
	    	}));
    	 }
   	 
    	 return contrato;
    }]);
});