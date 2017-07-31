/*global define*/
'use strict';

define(['siav-module'], function (app) {
    app.service('tarifasServices', ['$http', '$q', function ($http, $q) {
    	 var contrato = {
    			 consultar : consultar,
    			 consultarTarifaCredito : consultarTarifaCredito,
    			 consultarPorCodigo : consultarPorCodigo,
    			 crear : crear,
    			 editar : editar,
    			 consultarDescripciones : consultarDescripciones
    	 };
    	 
    	 function crear(tarifa){
    		 var request = $http.post("rest/tarifas/crear", tarifa);
    		 return (request.then(function(response) {
 	    		return response.data;
	    	}));
    	 }
    	 
    	 function editar(tarifa){
    		 var request = $http.post("rest/tarifas/editar", tarifa);
    		 return (request.then(function(response) {
 	    		return response.data;
	    	}));
    	 }
    	 
    	 function consultar(){
    		 var request = $http.get("rest/tarifas/consultar", {isArray : true});
    		 return (request.then(function(response) {
 	    		return response.data;
	    	}));
    	 }
    	 
    	 function consultarTarifaCredito(){
    		 var request = $http.get("rest/tarifas/consultar/credito", {isArray : true});
    		 return (request.then(function(response) {
 	    		return response.data;
	    	}));
    	 }
    	 
    	 function consultarDescripciones(){
    		 var request = $http.get("rest/tarifas/consultar/descripciones", {isArray : true});
    		 return (request.then(function(response) {
 	    		return response.data;
	    	}));
    	 }
    	 
    	 function consultarPorCodigo(codigo){
    		 var request = $http.get("rest/tarifas/buscar/" + codigo, {isArray : false});
    		 return (request.then(function(response) {
 	    		return response.data;
	    	}));
    	 }
   	 
    	 return contrato;
    }]);
});