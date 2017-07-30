/*global define*/
'use strict';

define(['siav-module', 'constantes'], function (app) {
    app.service('usuariosServices', ['$http', '$q', 'CONSTANTES', function ($http, $q, CONSTANTES) {
    	 var contrato = {
    			 guardar : guardar,
    			 buscar : buscar,
    			 buscarPorNombre : buscarPorNombre,
    			 buscarInfo : buscarInfo
    	 };
    	 
    	 function buscar(cedula){
    		 var request = $http.get(CONSTANTES.SRV.USUARIOS_BUSCAR + cedula, {isArray : false});
    		 return (request.then(function(response) {
 	    		return response.data;
	    	}));
    	 }
    	 
    	 function buscarPorNombre(filtro){
    		 var request = $http.post(CONSTANTES.SRV.USUARIOS_BUSCAR_POR_NOMBRE, filtro, {isArray : true});
    		 return (request.then(function(response) {
 	    		return response.data;
	    	}));
    	 }
    	 
    	 function buscarInfo(cedula){
    		 var request = $http.get(CONSTANTES.SRV.USUARIOS_BUSCAR_INFO + cedula, {isArray : false});
    		 return (request.then(function(response) {
 	    		return response.data;
	    	}));
    	 }
    	 
    	 function guardar(usuario, esActualizacion){
    		 var pathService = esActualizacion ? CONSTANTES.SRV.USUARIOS_ACTUALIZAR : CONSTANTES.SRV.USUARIOS_GUARDAR;
    		 var request = $http.post(pathService, usuario);
    		 return (request.then(function(response){
    			 return response.data;
    		 }));
    	 }
   	 
    	 return contrato;
    }]);
});