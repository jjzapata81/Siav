/*global define*/
'use strict';

define(['siav-module'], function (app) {
    app.service('novedadesServices', ['$http', '$q', function ($http, $q) {
    	 var contrato = {
    			 guardar : guardar,
    			 consultar : consultar
    	 };
    	 
    	 function guardar(novedad){
    		 var request = $http.put("rest/novedades/guardar", novedad);
    		 return (request.then(function(response) {
 	    		return response.data;
	    	}));
    	 }
    	 
    	 function consultar(numeroInstalacion){
    		 var request = $http.get("rest/novedades/consultar/" + numeroInstalacion, {isArray : true});
    		 return (request.then(function(response) {
 	    		return response.data;
	    	}));
    	 }
   	 
    	 return contrato;
    }]);
});