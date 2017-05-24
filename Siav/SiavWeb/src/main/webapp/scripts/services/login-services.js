/*global define*/
'use strict';

define(['siav-module'], function (app) {
    app.service('loginServices', ['$http', '$q', function ($http, $q) {
    	 var contrato = {
    			 ingresar : ingresar
    	 };
    	 
    	 function ingresar(login){
    		 var request = $http.put("rest/login/ingresar", login);
    		 return (request.then(function(response) {
 	    		return response.data;
	    	}));
    	 }
    	    	 
    	 return contrato;
    }]);
});