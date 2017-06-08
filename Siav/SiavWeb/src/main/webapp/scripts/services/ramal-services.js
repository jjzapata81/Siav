/*global define*/
'use strict';

define(['siav-module', 'constantes'], function (app) {
    app.service('ramalServices', ['$http', '$q', 'CONSTANTES', function ($http, $q, CONSTANTES) {
    	 var contrato = {
    			 consultar : consultar,
    			 consultarTodo : consultarTodo,
    			 guardar : guardar
    	 };
    	 
    	 function consultar(){
    		 var request = $http.get(CONSTANTES.SRV.RAMAL_CONSULTAR_TODO, {isArray : true});
    		 return (request.then(function(response) {
 	    		return response.data;
	    	}));
    	 }
    	 
    	 function consultarTodo(){
    		 var request = $http.get(CONSTANTES.SRV.RAMAL_CONSULTAR, {isArray : true});
    		 return (request.then(function(response) {
 	    		return response.data;
	    	}));
    	 }
    	 
    	 function guardar(request, accion){
    		 var request = $http.post(CONSTANTES.SRV.RAMAL_GUARDAR + accion, request);
    		 return (request.then(function(response) {
 	    		return response.data;
	    	}));
    	 }
   	 
    	 return contrato;
    }]);
});