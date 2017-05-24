/*global define*/
'use strict';

define(['siav-module', 'constantes'], function (app) {
    app.service('ciclosServices', ['$http', '$q', 'CONSTANTES', function ($http, $q, CONSTANTES) {
    	 var contrato = {
    			 consultar : consultar,
    			 consultarTodo : consultarTodo,
    			 editar : editar,
    			 cerrar : cerrar
    	 };
    	 
    	 function editar(ciclo){
    		 var request = $http.post(CONSTANTES.SRV.EDITAR_CICLOS, ciclo);
    		 return (request.then(function(response) {
 	    		return response.data;
	    	}));
    	 }
    	 
    	 function cerrar(ciclo){
    		 var request = $http.post(CONSTANTES.SRV.CERRAR_CICLO + ciclo);
    		 return (request.then(function(response) {
 	    		return response.data;
	    	})); 
    	 }
    	 
    	 function consultar(){
    		 var request = $http.get(CONSTANTES.SRV.CONSULTAR_CICLOS, {isArray : true});
    		 return (request.then(function(response) {
 	    		return response.data;
	    	}));
    	 }
    	 
    	 function consultarTodo(){
    		 var request = $http.get(CONSTANTES.SRV.CONSULTAR_CICLOS_TODO, {isArray : true});
    		 return (request.then(function(response) {
 	    		return response.data;
	    	}));
    	 }
   	 
    	 return contrato;
    }]);
});