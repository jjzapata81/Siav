/*global define*/
'use strict';

define(['siav-module', 'constantes'], function (app) {
    app.service('loginServices', ['$http', '$q', 'CONSTANTES', function ($http, $q, CONSTANTES) {
    	 var contrato = {
    			 ingresar : ingresar
    	 };
    	 
    	 function ingresar(login){
    		 var request = $http.put(CONSTANTES.SRV.LOGIN_INGRESAR, login);
    		 return (request.then(function(response) {
 	    		return response.data;
	    	}));
    	 }
    	    	 
    	 return contrato;
    }]);
});