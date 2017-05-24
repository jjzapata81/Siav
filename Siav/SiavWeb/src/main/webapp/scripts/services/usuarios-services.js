/*global define*/
'use strict';

define(['siav-module'], function (app) {
    app.service('usuariosServices', ['$http', '$q', function ($http, $q) {
    	 var contrato = {
    			 guardar : guardar,
    			 buscar : buscar
    	 };
    	 
    	 function buscar(cedula){
    		 var request = $http.get("rest/usuario/buscar/" + cedula, {isArray : false});
    		 return (request.then(function(response) {
 	    		return response.data;
	    	}));
    	 }
    	 
    	 function guardar(usuario, esActualizacion){
    		 var pathService = esActualizacion ? "rest/usuario/actualizar" : "rest/usuario/guardar";
    		 var request = $http.post(pathService, usuario);
    		 return (request.then(function(response){
    			 return response.data;
    		 }));
    	 }
   	 
    	 return contrato;
    }]);
});