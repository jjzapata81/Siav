/*global define*/
'use strict';

define(['siav-module', 'constantes'], function (app) {
    app.service('siavServices', ['$http', 'CONSTANTES', function ($http, CONSTANTES) {
    	 var contrato = {
    			 consultarIp : consultarIp,
    			 consultarEmpresa : consultarEmpresa,
    			 actualizarEmpresa : actualizarEmpresa,
    			 consultarEstructura : consultarEstructura,
    			 agregarJunta : agregarJunta,
    			 actualizarJunta : actualizarJunta
    	 };
    	 
    	 function consultarIp(){
    		 var request = $http.get(CONSTANTES.SRV.SISTEMA_CONSULTAR_IP, {isArray : false});
    		 return (request.then(function(response) {
 	    		return response.data;
	    	}));
    	 }
    	 
    	 function consultarEmpresa(){
    		 var request = $http.get(CONSTANTES.SRV.SISTEMA_CONSULTAR_EMPRESA, {isArray : false});
    		 return (request.then(function(response) {
 	    		return response.data;
	    	}));
    	 }
    	 
    	 function consultarEstructura(){
    		 var request = $http.get(CONSTANTES.SRV.SISTEMA_CONSULTAR_ESTRUCTURA, {isArray : true});
    		 return (request.then(function(response) {
 	    		return response.data;
	    	}));
    	 }
    	 
    	 function actualizarEmpresa(empresa){
    		 var request = $http.post(CONSTANTES.SRV.SISTEMA_ACTUALIZAR_EMPRESA, empresa);
    		 return (request.then(function(response) {
 	    		return response.data;
	    	}));
    	 }
    	 
    	 function agregarJunta(junta){
    		 var request = $http.post(CONSTANTES.SRV.SISTEMA_AGREGAR_JUNTA, junta);
    		 return (request.then(function(response) {
 	    		return response.data;
	    	}));
    	 }
    	 
    	 function actualizarJunta(junta){
    		 var request = $http.post(CONSTANTES.SRV.SISTEMA_ACTUALIZAR_JUNTA, junta);
    		 return (request.then(function(response) {
 	    		return response.data;
	    	}));
    	 }
   	 
    	 return contrato;
    }]);
});