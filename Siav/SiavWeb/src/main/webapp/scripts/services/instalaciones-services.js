/*global define*/
'use strict';

define(['siav-module'], function (app) {
    app.service('instalacionesServices', ['$http', '$q', function ($http, $q) {
    	 var contrato = {
    			 consultarMaestro : consultarMaestro,
    			 guardar : guardar,
    			 crear : crear,
    			 buscarInstalacion : buscarInstalacion
    	 };
    	 
    	 function consultarMaestro(maestro){
    		 var request = $http.get("rest/listas/maestros/" + maestro, {isArray : true});
    		 return (request.then(function(response) {
 	    		return response.data;
	    	}));
    	 }
    	 
    	 function buscarInstalacion(instalacion){
    		 var request = $http.get("rest/instalacion/buscar/" + instalacion, {isArray : false});
    		 return (request.then(function(response) {
 	    		return response.data;
	    	}));
    	 }
    	 
    	 function guardar(instalacion){
    		 var request = $http.post("rest/instalacion/guardar", instalacion);
    		 return (request.then(function(response){
    			 return response.data;
    		 }));
    	 }
    	 
    	 function crear(instalacion){
    		 var request = $http.post("rest/instalacion/crear", instalacion);
    		 return (request.then(function(response){
    			 return response.data;
    		 }));
    	 }
   	 
    	 return contrato;
    }]);
});