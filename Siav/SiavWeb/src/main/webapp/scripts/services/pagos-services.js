/*global define*/
'use strict';

define(['siav-module', 'constantes'], function (app) {
    app.service('pagosServices', ['$http', '$q', 'CONSTANTES', function ($http, $q, CONSTANTES) {
    	 var contrato = {
    			 consultar : consultar,
    			 pagar : pagar,
    			 abonar : abonar,
    			 buscar : buscar,
    			 cargarPagos : cargarPagos,
    			 abonoMatricula : abonoMatricula
    	 };
    	 
    	 function consultar(filtro){
    		 var request = $http.post(CONSTANTES.SRV.PAGO_CONSULTAR, filtro, { isArray : true});
    		 return (request.then(function(response) {
 	    		return response.data;
	    	}));
    	 }
    	 
    	 function pagar(pago){
    		 var request = $http.post(CONSTANTES.SRV.PAGO_GUARDAR, pago);
    		 return (request.then(function(response) {
 	    		return response.data;
	    	}));
    	 }
    	 
    	 function abonar(abono){
    		 var config = {
    				 headers: {
    					 'siav_usuario': getData("user")
						}
    		 };
    		 var request = $http.post(CONSTANTES.SRV.PAGO_ABONAR, abono, config, {responseType: 'arraybuffer'});
    		 return(request.then(function(response){
    			 return response.data;
    		 }));
    	 }
    	 
    	 function abonoMatricula(matricula){
    		 var config = {
    				 headers: {
    					 'siav_usuario': getData("user")
						}
    		 };
    		 var request = $http.post(CONSTANTES.SRV.PAGO_ABONO_MATRICULA, matricula, config, {responseType: 'arraybuffer'});
    		 return(request.then(function(response){
    			 return response.data;
    		 }));
    	 }
    	 
    	 function buscar(numeroInstalacion){
    		 var request = $http.post(CONSTANTES.SRV.PAGO_BUSCAR + numeroInstalacion);
    		 return (request.then(function(response) {
 	    		return response.data;
	    	}));
    	 }
    	 
    	 function cargarPagos(formData, codigoBanco) {
    		 var config = {
 							transformRequest: angular.identity,
 							headers: {
 								'Content-Type': 'application/octet-stream',
 								'nombreArchivo' : formData.name,
 								'codigoBanco' : codigoBanco
 							}
    		 			}
    		 var request = $http.put(CONSTANTES.SRV.PAGO_CARGAR, formData, config);
    		 return (request.then(function(response) {
 	    		return response.data;
	    	}));
    	}
    	 
    	 return contrato;
    }]);
});