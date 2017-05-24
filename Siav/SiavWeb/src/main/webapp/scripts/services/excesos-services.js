/*global define*/
'use strict';

define(['siav-module'], function (app) {
    app.service('excesosServices', ['$http', '$q', function ($http, $q) {
    	 var contrato = {
    			 consultar : consultar,
    			 guardar : guardar,
    			 editarRango : editarRango
    	 };
    	 
    	 function guardar(exceso){
    		 var request = $http.post("rest/excesos/guardar", exceso);
    		 return (request.then(function(response) {
 	    		return response.data;
	    	}));
    	 }
    	 
    	 function editarRango(consumos){
    		 var request = $http.post("rest/excesos/editar/rango", consumos);
    		 return (request.then(function(response) {
 	    		return response.data;
	    	}));
    	 }
    	 
    	 function consultar(){
    		 var request = $http.get("rest/excesos/consultar", {isArray : true});
    		 return (request.then(function(response) {
 	    		return response.data;
	    	}));
    	 }
   	 
    	 return contrato;
    }]);
});