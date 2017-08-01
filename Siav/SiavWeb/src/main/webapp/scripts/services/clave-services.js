/*global define*/
'use strict';

define(['siav-module', 'constantes'], function (app) {
    app.service('claveServices', ['$http', '$q', 'CONSTANTES', function ($http, $q, CONSTANTES) {
    	 var contrato = {
    			 cambiar : cambiar
    	 };
    	 
    	 function cambiar(clave){
    		 var request = $http.post(CONSTANTES.SRV.SEGURIDAD_CLAVE_CAMBIAR, clave);
    		 return (request.then(function(response) {
 	    		return response.data;
	    	}));
    	 }
   	 
    	 return contrato;
    }]);
});