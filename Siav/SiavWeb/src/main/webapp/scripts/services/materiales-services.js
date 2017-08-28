/*global define*/
'use strict';

define(['siav-module', 'constantes'], function (app) {
    app.service('materialesServices', ['$http', '$q', 'CONSTANTES', function ($http, $q, CONSTANTES) {
    	 var contrato = {
    			 crearEntrada : crearEntrada,
    			 crearSalida : crearSalida
    	 };
    	 
    	 function crearEntrada(entrada){
    		 var request = $http.post(CONSTANTES.SRV.MATERIAL_ENTRADA_CREAR, entrada);
    		 return (request.then(function(response) {
 	    		return response.data;
	    	}));
    	 }
    	 
    	 function crearSalida(salida){
    		 var request = $http.post(CONSTANTES.SRV.MATERIAL_SALIDA_CREAR, salida);
    		 return (request.then(function(response) {
 	    		return response.data;
	    	}));
    	 }
   	 
    	 return contrato;
    }]);
});