/*global define*/
'use strict';

define(['siav-module', 'constantes'], function (app) {
    app.service('pagosServices', ['$http', '$q', 'CONSTANTES', function ($http, $q, CONSTANTES) {
    	 var contrato = {
    			 pagar : pagar,
    			 abonar : abonar,
    			 buscar : buscar,
    			 cargarPagos : cargarPagos
    	 };
    	 
    	 function pagar(pago){
    		 var request = $http.post(CONSTANTES.SRV.GUARDAR_PAGO, pago);
    		 return (request.then(function(response) {
 	    		return response.data;
	    	}));
    	 }
    	 
    	 function abonar(abono){
    		 var request = $http.post("/Reportes/rest/general/abono/pdf", abono, {responseType: 'arraybuffer'});
    		 return(request.then(function(response){
    			 return response.data;
    		 }));
    	 }
    	 
    	 function buscar(numeroInstalacion){
    		 var request = $http.post(CONSTANTES.SRV.BUSCAR_PAGO + numeroInstalacion);
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
    		 var request = $http.put(CONSTANTES.SRV.CARGAR_PAGO, formData, config);
    		 return (request.then(function(response) {
 	    		return response.data;
	    	}));
    	}
    	 
    	 return contrato;
    }]);
});