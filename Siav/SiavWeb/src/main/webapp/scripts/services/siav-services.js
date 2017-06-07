/*global define*/
'use strict';

define(['siav-module', 'constantes'], function (app) {
    app.service('siavServices', ['$http', 'CONSTANTES', function ($http, CONSTANTES) {
    	 var contrato = {
    			 consultarIp : consultarIp,
    			 consultarEmmpresa : consultarEmmpresa
    	 };
    	 
    	 function consultarIp(){
    		 var request = $http.get(CONSTANTES.SRV.SISTEMA_CONSULTAR_EMPRESA, {isArray : false});
    		 return (request.then(function(response) {
 	    		return response.data;
	    	}));
    	 }
    	 
    	 function consultarEmmpresa(){
    		 var request = $http.get(CONSTANTES.SRV.SISTEMA_CONSULTAR_EMPRESA, {isArray : false});
    		 return (request.then(function(response) {
 	    		return response.data;
	    	}));
    	 }
   	 
    	 return contrato;
    }]);
});