/*global define*/
'use strict';

define(['siav-module', 'constantes'], function (app) {
    app.service('macrosServices', ['$http', 'CONSTANTES', function ($http, CONSTANTES) {
    	 var contrato = {
    			 consultar : consultar,
    			 guardar : guardar
    	 };
    	 
    	 function consultar(){
    		 var request = $http.get(CONSTANTES.SRV.MACROS_CONSULTAR, {isArray : true});
    		 return (request.then(function(response) {
 	    		return response.data;
	    	}));
    	 }
    	 
    	 function guardar(macro, accion){
    		 var request = $http.post(CONSTANTES.SRV.MACROS_GUARDAR + accion, macro);
    		 return (request.then(function(response) {
 	    		return response.data;
	    	}));
    	 }
   	 
    	 return contrato;
    }]);
});