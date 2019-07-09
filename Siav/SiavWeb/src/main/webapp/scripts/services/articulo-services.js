/*global define*/
'use strict';

define(['siav-module', 'constantes'], function (app) {
    app.service('articuloServices', ['$http', '$q', 'CONSTANTES', function ($http, $q, CONSTANTES) {
    	 var contrato = {
    			 consultar : consultar,
    			 consultarNombres : consultarNombres,
    			 consultarSalidas : consultarSalidas,
    			 consultarEntradas : consultarEntradas,
    			 crear : crear,
    			 actualizar : actualizar
    	 };
    	 
    	 function crear(proveedor){
    		 var request = $http.post(CONSTANTES.SRV.ARTICULO_CREAR, proveedor);
    		 return (request.then(function(response) {
 	    		return response.data;
	    	}));
    	 }
    	 
    	 function actualizar(proveedor){
    		 var request = $http.post(CONSTANTES.SRV.ARTICULO_ACTUALIZAR, proveedor);
    		 return (request.then(function(response) {
 	    		return response.data;
	    	}));
    	 }
    	 
    	 function consultar(){
    		 var request = $http.get(CONSTANTES.SRV.ARTICULO_CONSULTAR, {isArray : true});
    		 return (request.then(function(response) {
 	    		return response.data;
	    	}));
    	 }
    	 
    	 function consultarEntradas(){
    		 var request = $http.get(CONSTANTES.SRV.ARTICULO_CONSULTAR_ENTRADAS, {isArray : true});
    		 return (request.then(function(response) {
 	    		return response.data;
	    	}));
    	 }
    	 
    	 function consultarNombres(){
    		 var request = $http.get(CONSTANTES.SRV.ARTICULO_CONSULTAR_NOMBRES, {isArray : true});
    		 return (request.then(function(response) {
 	    		return response.data;
	    	}));
    	 }
    	 
    	 function consultarSalidas(){
    		 var request = $http.get(CONSTANTES.SRV.ARTICULO_CONSULTAR_SALIDAS, {isArray : true});
    		 return (request.then(function(response) {
 	    		return response.data;
	    	}));
    	 }
   	 
    	 return contrato;
    }]);
});