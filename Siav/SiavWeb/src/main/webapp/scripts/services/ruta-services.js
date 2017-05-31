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
    		 var request = $http.get(CONSTANTES.SRV.CONSULTAR_ORDEN_RUTAS, {isArray : true});
    		 return (request.then(function(response) {
 	    		return response.data;
	    	}));
    	 }
    	 
    	 function consultarPorNumero(instalacion){
    		 var request = $http.get(CONSTANTES.SRV.CONSULTAR_ORDEN_RUTAS + "/" + instalacion, {isArray : false});
    		 return (request.then(function(response) {
 	    		return response.data;
	    	}));
    	 }

    	 function guardar(request){
    		 var request = $http.post(CONSTANTES.SRV.GUARDAR_ORDEN_RUTAS, request, {isArray : false});
    		 return (request.then(function(response) {
 	    		return response.data;
	    	}));
    	 }
   	 
    	 return contrato;
    }]);
});