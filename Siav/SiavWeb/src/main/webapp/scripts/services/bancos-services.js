/*global define*/
'use strict';

define(['siav-module', 'constantes'], function (app) {
    app.service('bancosServices', ['$http', '$q', 'CONSTANTES', function ($http, $q, CONSTANTES) {
    	 var contrato = {
    			 consultar : consultar,
    			 consultarCuentas : consultarCuentas,
    			 crear : crear, 
    			 editar : editar
    	 };
    	 
    	 function crear(banco){
    		 var request = $http.post(CONSTANTES.SRV.BANCO_CREAR, banco);
    		 return (request.then(function(response) {
 	    		return response.data;
	    	}));
    	 }
    	 
    	 function editar(banco){
    		 var request = $http.post(CONSTANTES.SRV.BANCO_EDITAR, banco);
    		 return (request.then(function(response) {
 	    		return response.data;
	    	}));
    	 }
    	 
    	 function consultar(){
    		 var request = $http.get(CONSTANTES.SRV.BANCO_CONSULTAR, {isArray : true});
    		 return (request.then(function(response) {
 	    		return response.data;
	    	}));
    	 }
    	 
    	 function consultarCuentas(){
    		 var request = $http.get(CONSTANTES.SRV.BANCO_CONSULTAR_CUENTAS, {isArray : true});
    		 return (request.then(function(response) {
 	    		return response.data;
	    	}));
    	 }
   	 
    	 return contrato;
    }]);
});