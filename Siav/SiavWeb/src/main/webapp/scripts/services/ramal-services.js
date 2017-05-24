/*global define*/
'use strict';

define(['siav-module'], function (app) {
    app.service('ramalServices', ['$http', '$q', function ($http, $q) {
    	 var contrato = {
    			 consultar : consultar
    	 };
    	 
    	 function consultar(){
    		 var request = $http.get("rest/ramal/consultar/todo", {isArray : true});
    		 return (request.then(function(response) {
 	    		return response.data;
	    	}));
    	 }
   	 
    	 return contrato;
    }]);
});