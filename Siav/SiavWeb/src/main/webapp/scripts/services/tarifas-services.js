/*global define*/
'use strict';

define(['siav-module', 'constantes'], function (app) {
    app.service('tarifasServices', ['$http', '$q', 'CONSTANTES', function ($http, $q, CONSTANTES) {
    	 var contrato = {
    			 consultar : consultar,
    			 consultarTarifaCredito : consultarTarifaCredito,
    			 consultarPorCodigo : consultarPorCodigo,
    			 crear : crear,
    			 editar : editar,
    			 consultarDescripciones : consultarDescripciones
    	 };
    	 
    	 function crear(tarifa){
    		 var request = $http.post(CONSTANTES.SRV.TARIFA_CREAR, tarifa);
    		 return (request.then(function(response) {
 	    		return response.data;
	    	}));
    	 }
    	 
    	 function editar(tarifa){
    		 var request = $http.post(CONSTANTES.SRV.TARIFA_EDITAR, tarifa);
    		 return (request.then(function(response) {
 	    		return response.data;
	    	}));
    	 }
    	 
    	 function consultar(){
    		 var request = $http.get(CONSTANTES.SRV.TARIFA_CONSULTAR, {isArray : true});
    		 return (request.then(function(response) {
 	    		return response.data;
	    	}));
    	 }
    	 
    	 function consultarTarifaCredito(){
    		 var request = $http.get(CONSTANTES.SRV.TARIFA_CONSULTAR_CREDITO, {isArray : true});
    		 return (request.then(function(response) {
 	    		return response.data;
	    	}));
    	 }
    	 
    	 function consultarDescripciones(){
    		 var request = $http.get(CONSTANTES.SRV.TARIFA_CONSULTAR_DESCRIPCION, {isArray : true});
    		 return (request.then(function(response) {
 	    		return response.data;
	    	}));
    	 }
    	 
    	 function consultarPorCodigo(codigo){
    		 var request = $http.get(CONSTANTES.SRV.TARIFA_BUSCAR + codigo, {isArray : false});
    		 return (request.then(function(response) {
 	    		return response.data;
	    	}));
    	 }
   	 
    	 return contrato;
    }]);
});