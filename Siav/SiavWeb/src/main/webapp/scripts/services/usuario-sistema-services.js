/*global define*/
'use strict';

define(['siav-module'], function (app) {
    app.service('usuarioSistemaServices', ['$http', '$q', function ($http, $q) {
    	 var contrato = {
    			 consultar : consultar,
    			 crear : crear,
    			 actualizar : actualizar,
    			 cambioEstado : cambioEstado,
    			 consultarPerfiles : consultarPerfiles
    	 };
    	 
    	 function crear(usuario){
    		 var request = $http.post("rest/seguridad/usuario/crear", usuario);
    		 return (request.then(function(response) {
 	    		return response.data;
	    	}));
    	 }
    	 
    	 function actualizar(usuario){
    		 var request = $http.post("rest/seguridad/usuario/actualizar", usuario);
    		 return (request.then(function(response) {
 	    		return response.data;
	    	}));
    	 }
    	 
    	 function cambioEstado(usuario){
    		 var request = $http.put("rest/seguridad/estado", usuario);
    		 return (request.then(function(response) {
 	    		return response.data;
	    	}));
    	 }
    	 
    	 function consultar(){
    		 var request = $http.get("rest/seguridad/usuario/consultar", {isArray : true});
    		 return (request.then(function(response) {
 	    		return response.data;
	    	}));
    	 }
    	 
    	 function consultarPerfiles(){
    		 var request = $http.get("rest/login/perfiles/consultar", {isArray : true});
    		 return (request.then(function(response) {
 	    		return response.data;
	    	}));
    	 }
   	 
    	 return contrato;
    }]);
});