/*global define*/
'use strict';

define(['siav-module'], function (app) {
    app.service('claveServices', ['$http', '$q', function ($http, $q) {
    	 var contrato = {
    			 cambiar : cambiar
    	 };
    	 
    	 function cambiar(clave){
    		 var request = $http.post("rest/seguridad/clave/cambiar", clave);
    		 return (request.then(function(response) {
 	    		return response.data;
	    	}));
    	 }
   	 
    	 return contrato;
    }]);
});