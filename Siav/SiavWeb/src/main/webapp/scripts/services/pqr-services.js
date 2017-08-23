/*global define*/
'use strict';

define(['siav-module', 'constantes'], function (app) {
    app.service('pqrServices', ['$http', 'CONSTANTES', function ($http, CONSTANTES) {
    	 var contrato = {
    			 consultar : consultar,
    			 consultarDetalle : consultarDetalle,
    			 crear : crear,
    			 actualizar : actualizar
    	 };
    	 
    	 function consultar(){
    		 var request = $http.get(CONSTANTES.SRV.PQR_CONSULTAR, {isArray : true});
    		 return (request.then(function(response) {
 	    		return response.data;
	    	}));
    	 }
    	 
    	 function consultarDetalle(request){
    		 var request = $http.post(CONSTANTES.SRV.PQR_CONSULTAR_DETALLE, request, {isArray : true});
    		 return (request.then(function(response) {
 	    		return response.data;
	    	}));
    	 }
    	 
    	 function crear(pqr){
    		 var request = $http.post(CONSTANTES.SRV.PQR_CREAR, pqr);
    		 return (request.then(function(response) {
 	    		return response.data;
	    	}));
    	 }
    	 
    	 function actualizar(pqr){
    		 var request = $http.post(CONSTANTES.SRV.PQR_ACTUALIZAR, pqr);
    		 return (request.then(function(response) {
 	    		return response.data;
	    	}));
    	 }
   	 
    	 return contrato;
    }]);
});