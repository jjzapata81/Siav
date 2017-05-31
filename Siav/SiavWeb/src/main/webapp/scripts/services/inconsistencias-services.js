/*global define*/
'use strict';

define(['siav-module', 'constantes'], function (app) {
    app.service('inconsistenciasServices', ['$http', '$q', 'CONSTANTES', function ($http, $q, CONSTANTES) {
    	 var contrato = {
    			 consultar : consultar,
    			 consultarRango : consultarRango,
    			 guardar : guardar,
    			 guardarCorreccion : guardarCorreccion,
    			 guardarCorreccionConsumo : guardarCorreccionConsumo
    	 };
    	 
    	 function consultar(){
    		 var request = $http.get(CONSTANTES.SRV.CONSUMOS_INCOMPLETOS, {isArray : true});
    		 return(request.then(function(response){
    			 return response.data;
    		 }));
    	 }
    	 
    	 function consultarRango(consumo){
    		 var request = $http.post(CONSTANTES.SRV.CONSUMOS_RANGO, consumo, {isArray : true});
    		 return(request.then(function(response){
    			 return response.data;
    		 }));
    	 }
    	 
    	 function guardar(request){
    		 var request = $http.post(CONSTANTES.SRV.CONSUMOS_INCOMPLETOS_GUARDAR, request);
    		 return(request.then(function(response){
    			 return response.data;
    		 }));
    	 }
    	 
    	 function guardarCorreccion(request){
    		 var request = $http.post(CONSTANTES.SRV.CONSUMOS_RIESGO_GUARDAR, request);
    		 return(request.then(function(response){
    			 return response.data;
    		 }));
    	 }
    	 
    	 function guardarCorreccionConsumo(request){
    		 var request = $http.post(CONSTANTES.SRV.CONSUMOS_RIESGO_GUARDAR_CORRECCION_CONSUMO, request);
    		 return(request.then(function(response){
    			 return response.data;
    		 }));
    	 }
    	 
    	 return contrato;
    }]);
});