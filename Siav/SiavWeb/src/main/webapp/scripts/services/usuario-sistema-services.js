/*global define*/
'use strict';

define(['siav-module', 'constantes'], function (app) {
    app.service('usuarioSistemaServices', ['$http', '$q', 'CONSTANTES', function ($http, $q, CONSTANTES) {
    	 var contrato = {
    			 consultar : consultar,
    			 crear : crear,
    			 actualizar : actualizar,
    			 cambioEstado : cambioEstado,
    			 consultarPerfiles : consultarPerfiles
    	 };
    	 
    	 function crear(usuario){
    		 var request = $http.post(CONSTANTES.SRV.SEGURIDAD_USUARIO_CREAR, usuario);
    		 return (request.then(function(response) {
 	    		return response.data;
	    	}));
    	 }
    	 
    	 function actualizar(usuario){
    		 var request = $http.post(CONSTANTES.SRV.SEGURIDAD_USUARIO_ACTUALIZAR, usuario);
    		 return (request.then(function(response) {
 	    		return response.data;
	    	}));
    	 }
    	 
    	 function cambioEstado(usuario){
    		 var request = $http.put(CONSTANTES.SRV.SEGURIDAD_USUARIO_ESTADO, usuario);
    		 return (request.then(function(response) {
 	    		return response.data;
	    	}));
    	 }
    	 
    	 function consultar(){
    		 var request = $http.get(CONSTANTES.SRV.SEGURIDAD_USUARIO_CONSULTAR, {isArray : true});
    		 return (request.then(function(response) {
 	    		return response.data;
	    	}));
    	 }
    	 
    	 function consultarPerfiles(){
    		 var request = $http.get(CONSTANTES.SRV.LOGIN_PERFIL_CONSULTAR, {isArray : true});
    		 return (request.then(function(response) {
 	    		return response.data;
	    	}));
    	 }
   	 
    	 return contrato;
    }]);
});