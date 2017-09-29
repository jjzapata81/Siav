/*global define*/
'use strict';

define(['siav-module', 'constantes'], function (app) {
    app.service('ciclosServices', ['$http', '$q', 'CONSTANTES', function ($http, $q, CONSTANTES) {
    	 var contrato = {
    			 consultar : consultar,
    			 consultarEstado : consultarEstado,
    			 consultarTodo : consultarTodo,
    			 editar : editar,
    			 cerrar : cerrar
    	 };
    	 
    	 function editar(ciclo){
    		 var request = $http.post(CONSTANTES.SRV.CICLO_EDITAR, ciclo);
    		 return (request.then(function(response) {
 	    		return response.data;
	    	}));
    	 }
    	 
    	 function cerrar(ciclo){
    		 var request = $http.post(CONSTANTES.SRV.CICLO_CERRAR + ciclo);
    		 return (request.then(function(response) {
 	    		return response.data;
	    	})); 
    	 }
    	 
    	 function consultar(){
    		 var request = $http.get(CONSTANTES.SRV.CICLO_CONSULTAR, {isArray : true});
    		 return (request.then(function(response) {
 	    		return response.data;
	    	}));
    	 }
    	 
    	 function consultarEstado(estado){
    		 var request = $http.get(CONSTANTES.SRV.CICLO_CONSULTAR + estado, {isArray : false});
    		 return (request.then(function(response) {
 	    		return response.data;
	    	}));
    	 }
    	 
    	 function consultarTodo(){
    		 var request = $http.get(CONSTANTES.SRV.CICLO_CONSULTAR_TODO, {isArray : true});
    		 return (request.then(function(response) {
 	    		return response.data;
	    	}));
    	 }
   	 
    	 return contrato;
    }]);
});