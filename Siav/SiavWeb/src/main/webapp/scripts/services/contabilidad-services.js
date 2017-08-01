/*global define*/
'use strict';

define(['siav-module', 'constantes'], function (app) {
    app.service('contabilidadServices', ['$http', 'CONSTANTES', function ($http, CONSTANTES) {
    	 var contrato = {
    			 consultar : consultar,
    			 guardar : guardar
    	 };
    	 
    	 function consultar(){
    		 var request = $http.get(CONSTANTES.SRV.CONTABILIDAD_CONSULTAR, {isArray : false});
    		 return (request.then(function(response) {
 	    		return response.data;
	    	}));
    	 }
    	 
    	 function guardar(contabilidad){
    		 var request = $http.post(CONSTANTES.SRV.CONTABILIDAD_GUARDAR, contabilidad);
    		 return (request.then(function(response){
    			 return response.data;
    		 }));
    	 }
   	 
    	 return contrato;
    }]);
});