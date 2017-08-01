/*global define*/
'use strict';

define(['siav-module', 'constantes'], function (app) {
    app.service('excesosServices', ['$http', '$q', 'CONSTANTES', function ($http, $q, CONSTANTES) {
    	 var contrato = {
    			 consultar : consultar,
    			 guardar : guardar,
    			 editarRango : editarRango
    	 };
    	 
    	 function guardar(exceso){
    		 var request = $http.post(CONSTANTES.SRV.EXCESO_GUARDAR, exceso);
    		 return (request.then(function(response) {
 	    		return response.data;
	    	}));
    	 }
    	 
    	 function editarRango(consumos){
    		 var request = $http.post(CONSTANTES.SRV.EXCESO_EDITAR_RANGO, consumos);
    		 return (request.then(function(response) {
 	    		return response.data;
	    	}));
    	 }
    	 
    	 function consultar(){
    		 var request = $http.get(CONSTANTES.SRV.EXCESO_CONSULTAR, {isArray : true});
    		 return (request.then(function(response) {
 	    		return response.data;
	    	}));
    	 }
   	 
    	 return contrato;
    }]);
});