/*global define*/
'use strict';

define(['siav-module', 'constantes'], function (app) {
    app.service('asignacionServices', ['$http', 'CONSTANTES', function ($http, CONSTANTES) {
    	 var contrato = {
    			 consultar : consultar,
    			 actualizar : actualizar
    	 };
    	 
    	 function consultar(){
    		 var request = $http.get(CONSTANTES.SRV.ASIGNACION_CONSULTAR, {isArray : true});
    		 return (request.then(function(response) {
 	    		return response.data;
	    	}));
    	 }
    	 
    	 function actualizar(asignacion){
    		 var request = $http.post(CONSTANTES.SRV.ASIGNACION_ACTUALIZAR, asignacion);
    		 return (request.then(function(response) {
 	    		return response.data;
	    	}));
    	 }
   	 
    	 return contrato;
    }]);
});