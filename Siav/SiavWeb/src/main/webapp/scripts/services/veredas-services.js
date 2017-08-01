/*global define*/
'use strict';

define(['siav-module', 'constantes'], function (app) {
    app.service('veredasServices', ['$http', '$q', 'CONSTANTES', function ($http, $q, CONSTANTES) {
    	 var contrato = {
    			 consultar : consultar,
    			 consultarTodo : consultarTodo,
    			 consultarNombres : consultarNombres,
    			 crear : crear
    	 };
    	 
    	 function crear(vereda){
    		 var request = $http.post(CONSTANTES.SRV.VEREDA_CREAR, vereda);
    		 return (request.then(function(response) {
 	    		return response.data;
	    	}));
    	 }
    	 
    	 function consultar(){
    		 var request = $http.get(CONSTANTES.SRV.VEREDA_CONSULTAR, {isArray : true});
    		 return (request.then(function(response) {
 	    		return response.data;
	    	}));
    	 }
    	 
    	 function consultarTodo(){
    		 var request = $http.get(CONSTANTES.SRV.VEREDA_CONSULTAR_TODO, {isArray : true});
    		 return (request.then(function(response) {
 	    		return response.data;
	    	}));
    	 }
    	 
    	 function consultarNombres(){
    		 var request = $http.get(CONSTANTES.SRV.VEREDA_CONSULTAR_NOMBRES, {isArray : true});
    		 return (request.then(function(response) {
 	    		return response.data;
	    	}));
    	 }
   	 
    	 return contrato;
    }]);
});