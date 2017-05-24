/*global define*/
'use strict';

define(['siav-module'], function (app) {
    app.service('rangosServices', ['$http', '$q', function ($http, $q) {
    	 var contrato = {
    			 consultar : consultar,
    			 crear : crear,
    			 eliminar : eliminar
    	 };
    	 
    	 function crear(rango){
    		 var request = $http.post("rest/rangos/crear", rango);
    		 return (request.then(function(response) {
 	    		return response.data;
	    	}));
    	 }
    	 
    	 function eliminar(rango){
    		 var request = $http.post("rest/rangos/eliminar", rango);
    		 return (request.then(function(response) {
 	    		return response.data;
	    	}));
    	 }
    	 
    	 function consultar(){
    		 var request = $http.get("rest/rangos/consultar", {isArray : true});
    		 return (request.then(function(response) {
 	    		return response.data;
	    	}));
    	 }
   	 
    	 return contrato;
    }]);
});