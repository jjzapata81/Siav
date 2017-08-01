/*global define*/
'use strict';

define(['siav-module', 'constantes'], function (app) {
    app.service('rutaServices', ['$http', '$q', 'CONSTANTES', function ($http, $q, CONSTANTES) {
    	 var contrato = {
    			 consultar : consultar,
    			 consultarPorNumero : consultarPorNumero,
    			 guardar : guardar,
    	 };
    	 
    	 function consultar(){
    		 var request = $http.get(CONSTANTES.SRV.ORDEN_RUTAS_CONSULTAR, {isArray : true});
    		 return (request.then(function(response) {
 	    		return response.data;
	    	}));
    	 }
    	 
    	 function consultarPorNumero(instalacion){
    		 var request = $http.get(CONSTANTES.SRV.ORDEN_RUTAS_CONSULTAR + "/" + instalacion, {isArray : false});
    		 return (request.then(function(response) {
 	    		return response.data;
	    	}));
    	 }

    	 function guardar(request){
    		 var request = $http.post(CONSTANTES.SRV.ORDEN_RUTAS_GUARDAR, request, {isArray : false});
    		 return (request.then(function(response) {
 	    		return response.data;
	    	}));
    	 }
   	 
    	 return contrato;
    }]);
});