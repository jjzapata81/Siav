/*global define*/
'use strict';

define(['siav-module', 'constantes'], function (app) {
    app.service('causasServices', ['$http', '$q', 'CONSTANTES', function ($http, $q, CONSTANTES) {
    	 var contrato = {
    			 consultar : consultar,
    			 consultarDescripciones : consultarDescripciones,
    			 crear : crear,
    			 cambiarEstado : cambiarEstado
    	 };
    	 
    	 function crear(causa){
    		 var request = $http.post(CONSTANTES.SRV.CAUSA_NO_LECTURA_CREAR, causa);
    		 return (request.then(function(response) {
 	    		return response.data;
	    	}));
    	 }
    	 
    	 function cambiarEstado(causa){
    		 var request = $http.put(CONSTANTES.SRV.CAUSA_NO_LECTURA_ACTIVAR, causa);
    		 return (request.then(function(response) {
 	    		return response.data;
	    	}));
    	 }
    	 
    	 function consultar(){
    		 var request = $http.get(CONSTANTES.SRV.CAUSA_NO_LECTURA_CONSULTAR, {isArray : true});
    		 return (request.then(function(response) {
 	    		return response.data;
	    	}));
    	 }
    	 
    	 function consultarDescripciones(){
    		 var request = $http.get(CONSTANTES.SRV.CAUSA_NO_LECTURA_CONSULTAR_DESCRIPCIONES, {isArray : true});
    		 return (request.then(function(response) {
 	    		return response.data;
	    	}));
    	 }
   	 
    	 return contrato;
    }]);
});