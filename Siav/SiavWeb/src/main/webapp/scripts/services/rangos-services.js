/*global define*/
'use strict';

define(['siav-module', 'constantes'], function (app) {
    app.service('rangosServices', ['$http', '$q', 'CONSTANTES', function ($http, $q, CONSTANTES) {
    	 var contrato = {
    			 consultar : consultar,
    			 crear : crear,
    			 eliminar : eliminar
    	 };
    	 
    	 function crear(rango){
    		 var request = $http.post(CONSTANTES.SRV.RANGO_CREAR, rango);
    		 return (request.then(function(response) {
 	    		return response.data;
	    	}));
    	 }
    	 
    	 function eliminar(rango){
    		 var request = $http.post(CONSTANTES.SRV.RANGO_ELIMINAR, rango);
    		 return (request.then(function(response) {
 	    		return response.data;
	    	}));
    	 }
    	 
    	 function consultar(){
    		 var request = $http.get(CONSTANTES.SRV.RANGO_CONSULTAR, {isArray : true});
    		 return (request.then(function(response) {
 	    		return response.data;
	    	}));
    	 }
   	 
    	 return contrato;
    }]);
});