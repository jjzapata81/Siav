/*global define*/
'use strict';

define(['siav-module', 'constantes'], function (app) {
    app.service('facturaServices', ['$http', 'CONSTANTES', function ($http, CONSTANTES) {
    	 var contrato = {
    			 prefacturar : prefacturar,
    			 consultarFacturas : consultarFacturas
    	 };
    	 
    	 function prefacturar(){
    		 var request = $http.get(CONSTANTES.SRV.FACTURACION_INICIAR, {isArray : false});
    		 return (request.then(function(response) {
 	    		return response.data;
	    	}));
    	 }
    	 
    	 function consultarFacturas(){
    		 var request = $http.get(CONSTANTES.SRV.FACTURACION_CONSULTAR, {isArray : false});
    		 return (request.then(function(response) {
 	    		return response.data;
	    	}));
    	 }
   	 
    	 return contrato;
    }]);
});