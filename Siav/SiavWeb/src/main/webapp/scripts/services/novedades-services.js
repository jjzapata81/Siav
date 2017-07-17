/*global define*/
'use strict';

define(['siav-module', 'constantes'], function (app) {
    app.service('novedadesServices', ['$http', '$q', 'CONSTANTES', function ($http, $q, CONSTANTES) {
    	 var contrato = {
    			 guardar : guardar,
    			 consultar : consultar,
    			 eliminar : eliminar,
    			 consultarNotaCredito : consultarNotaCredito,
    			 guardarNotaCredito : guardarNotaCredito
    	 };
    	 
    	 function guardar(novedad){
    		 var request = $http.put(CONSTANTES.SRV.NOVEDAD_GUARDAR, novedad);
    		 return (request.then(function(response) {
 	    		return response.data;
	    	}));
    	 }
    	 
    	 function consultar(numeroInstalacion){
    		 var request = $http.get(CONSTANTES.SRV.NOVEDAD_CONSULTAR + numeroInstalacion, {isArray : true});
    		 return (request.then(function(response) {
 	    		return response.data;
	    	}));
    	 }
    	 
    	 function eliminar(novedad){
    		 var request = $http.put(CONSTANTES.SRV.NOVEDAD_ELIMINAR, novedad);
    		 return (request.then(function(response) {
 	    		return response.data;
	    	}));
    	 }
    	 
    	 function consultarNotaCredito(numeroInstalacion){
    		 var request = $http.get(CONSTANTES.SRV.NOVEDAD_CONSULTAR_NOTA_CREDITO + numeroInstalacion, {isArray : true});
    		 return (request.then(function(response) {
 	    		return response.data;
	    	}));
    	 }
    	 
    	 function guardarNotaCredito(request){
    		 var request = $http.put(CONSTANTES.SRV.NOVEDAD_GUARDAR_NOTA_CREDITO, request, {isArray : false});
    		 return (request.then(function(response) {
 	    		return response.data;
	    	}));
    	 }
   	 
    	 return contrato;
    }]);
});