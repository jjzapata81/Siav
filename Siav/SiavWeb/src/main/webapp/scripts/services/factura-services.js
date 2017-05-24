/*global define*/
'use strict';

define(['siav-module'], function (app) {
    app.service('facturaServices', ['$http', function ($http) {
    	 var contrato = {
    			 prefacturar : prefacturar
    	 };
    	 
    	 function prefacturar(){
    		 var request = $http.get("rest/facturacion/iniciar", {isArray : false});
    		 return (request.then(function(response) {
 	    		return response.data;
	    	}));
    	 }
   	 
    	 return contrato;
    }]);
});