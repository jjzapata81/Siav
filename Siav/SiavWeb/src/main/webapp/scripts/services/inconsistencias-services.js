/*global define*/
'use strict';

define(['siav-module', 'constantes'], function (app) {
    app.service('inconsistenciasServices', ['$http', '$q', 'CONSTANTES', function ($http, $q, CONSTANTES) {
    	 var contrato = {
    			 consultar : consultar,
    			 consultarLog : consultarLog,
    			 consultarRango : consultarRango,
    			 guardar : guardar,
    			 guardarCorreccionLectura : guardarCorreccionLectura,
    			 guardarCorreccionConsumo : guardarCorreccionConsumo
    	 };
    	 
    	 function consultar(){
    		 var request = $http.get(CONSTANTES.SRV.CONSUMOS_INCOMPLETOS, {isArray : true});
    		 return(request.then(function(response){
    			 return response.data;
    		 }));
    	 }
    	 
    	 function consultarLog(filtro){
    		 var request = $http.post(CONSTANTES.SRV.CONSUMOS_INCOMPLETOS_LOG, filtro, {isArray : true});
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
    		 var config = {
    				 headers: {
    					 'siav_usuario': getData("user")
						}
    		 };
    		 var request = $http.post(CONSTANTES.SRV.CONSUMOS_INCOMPLETOS_GUARDAR, request, config);
    		 return(request.then(function(response){
    			 return response.data;
    		 }));
    	 }
    	 
    	 function guardarCorreccionLectura(request){
    		 var config = {
    				 headers: {
    					 'siav_usuario': getData("user")
						}
    		 };
    		 var request = $http.post(CONSTANTES.SRV.CONSUMOS_RIESGO_GUARDAR_CORRECCION_LECTURA, request, config);
    		 return(request.then(function(response){
    			 return response.data;
    		 }));
    	 }
    	 
    	 function guardarCorreccionConsumo(request){
    		 var config = {
    				 headers: {
    					 'siav_usuario': getData("user")
						}
    		 };
    		 var request = $http.post(CONSTANTES.SRV.CONSUMOS_RIESGO_GUARDAR_CORRECCION_CONSUMO, request, config);
    		 return(request.then(function(response){
    			 return response.data;
    		 }));
    	 }
    	 
    	 return contrato;
    }]);
});