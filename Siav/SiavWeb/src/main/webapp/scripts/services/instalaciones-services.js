/*global define*/
'use strict';

define(['siav-module', 'constantes'], function (app) {
    app.service('instalacionesServices', ['$http', '$q', 'CONSTANTES', function ($http, $q, CONSTANTES) {
    	 var contrato = {
    			 consultarMaestro : consultarMaestro,
    			 guardar : guardar,
    			 crear : crear,
    			 activar : activar,
    			 desactivar : desactivar,
    			 buscarInstalacion : buscarInstalacion,
    			 consultaVencido : consultaVencido
    	 };
    	 
    	 function consultarMaestro(maestro){
    		 var request = $http.get(CONSTANTES.SRV.MAESTROS_CONSULTAR + maestro, {isArray : true});
    		 return (request.then(function(response) {
 	    		return response.data;
	    	}));
    	 }
    	 
    	 function buscarInstalacion(instalacion){
    		 var request = $http.get(CONSTANTES.SRV.INSTALACION_BUSCAR + instalacion, {isArray : false});
    		 return (request.then(function(response) {
 	    		return response.data;
	    	}));
    	 }
    	 
    	 function guardar(instalacion){
    		 var request = $http.post(CONSTANTES.SRV.INSTALACION_GUARDAR, instalacion);
    		 return (request.then(function(response){
    			 return response.data;
    		 }));
    	 }
    	 
    	 function crear(instalacion){
    		 var request = $http.post(CONSTANTES.SRV.INSTALACION_CREAR, instalacion);
    		 return (request.then(function(response){
    			 return response.data;
    		 }));
    	 }
    	 
    	 function desactivar(request){
    		 var request = $http.post(CONSTANTES.SRV.INSTALACION_DESACTIVAR, request);
    		 return (request.then(function(response){
    			 return response.data;
    		 }));
    	 }
    	 
    	 function activar(instalacion){
    		 var request = $http.get(CONSTANTES.SRV.INSTALACION_ACTIVAR + instalacion, {isArray : false});
    		 return (request.then(function(response) {
 	    		return response.data;
	    	}));
    	 }
    	 
    	 function consultaVencido(instalacion){
    		 var config = {
    				 headers: {
    					 'siav_usuario': getData("user")
						}
    		 };
    		 var request = $http.get(CONSTANTES.SRV.INSTALACION_CONSULTAR_VENCIDO + instalacion, config, {isArray : false});
    		 return (request.then(function(response) {
 	    		return response.data;
	    	}));
    	 }
   	 
    	 return contrato;
    }]);
});