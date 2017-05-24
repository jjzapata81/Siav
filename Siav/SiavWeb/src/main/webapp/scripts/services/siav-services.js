/*global define*/
'use strict';

define(['siav-module'], function (app) {
    app.service('siavServices', ['$http', function ($http) {
    	 var contrato = {
    			 consultarIp : consultarIp
    	 };
    	 
    	 function consultarIp(){
    		 var request = $http.get("rest/general/consultar/ip", {isArray : false});
    		 return (request.then(function(response) {
 	    		return response.data;
	    	}));
    	 }
   	 
    	 return contrato;
    }]);
});