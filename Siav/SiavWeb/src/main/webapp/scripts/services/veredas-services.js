/*global define*/
'use strict';

define(['siav-module'], function (app) {
    app.service('veredasServices', ['$http', '$q', function ($http, $q) {
    	 var contrato = {
    			 consultar : consultar,
    			 consultarTodo : consultarTodo,
    			 consultarNombres : consultarNombres,
    			 crear : crear
    	 };
    	 
    	 function crear(vereda){
    		 var request = $http.post("rest/veredas/crear", vereda);
    		 return (request.then(function(response) {
 	    		return response.data;
	    	}));
    	 }
    	 
    	 function consultar(){
    		 var request = $http.get("rest/veredas/consultar", {isArray : true});
    		 return (request.then(function(response) {
 	    		return response.data;
	    	}));
    	 }
    	 
    	 function consultarTodo(){
    		 var request = $http.get("rest/veredas/consultar/todo", {isArray : true});
    		 return (request.then(function(response) {
 	    		return response.data;
	    	}));
    	 }
    	 
    	 function consultarNombres(){
    		 var request = $http.get("rest/veredas/consultar/nombres", {isArray : true});
    		 return (request.then(function(response) {
 	    		return response.data;
	    	}));
    	 }
   	 
    	 return contrato;
    }]);
});