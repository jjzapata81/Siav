/*global define*/
'use strict';

define(['siav-module', 'constantes'], function (app) {
    app.service('proveedorServices', ['$http', '$q', 'CONSTANTES', function ($http, $q, CONSTANTES) {
    	 var contrato = {
    			 consultar : consultar,
    			 consultarNombres : consultarNombres,
    			 crear : crear,
    			 actualizar : actualizar
    	 };
    	 
    	 function crear(proveedor){
    		 var request = $http.post(CONSTANTES.SRV.PROVEEDOR_CONSULTAR_CREAR, proveedor);
    		 return (request.then(function(response) {
 	    		return response.data;
	    	}));
    	 }
    	 
    	 function actualizar(proveedor){
    		 var request = $http.post(CONSTANTES.SRV.PROVEEDOR_CONSULTAR_ACTUALIZAR, proveedor);
    		 return (request.then(function(response) {
 	    		return response.data;
	    	}));
    	 }
    	 
    	 function consultar(){
    		 var request = $http.get(CONSTANTES.SRV.PROVEEDOR_CONSULTAR, {isArray : true});
    		 return (request.then(function(response) {
 	    		return response.data;
	    	}));
    	 }
    	 
    	 function consultarNombres(){
    		 var request = $http.get(CONSTANTES.SRV.PROVEEDOR_CONSULTAR_NOMBRES, {isArray : true});
    		 return (request.then(function(response) {
 	    		return response.data;
	    	}));
    	 }
   	 
    	 return contrato;
    }]);
});