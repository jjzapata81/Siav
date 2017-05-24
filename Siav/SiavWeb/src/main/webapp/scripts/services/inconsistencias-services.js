/*global define*/
'use strict';

define(['siav-module', 'constantes'], function (app) {
    app.service('inconsistenciasServices', ['$http', '$q', 'CONSTANTES', function ($http, $q, CONSTANTES) {
    	 var contrato = {
    			 consultarConsumosPendientes : consultarConsumosPendientes,
    			 guardar : guardar
    	 };
    	 
    	 function consultarConsumosPendientes(){
    		 var request = $http.get(CONSTANTES.SRV.CONSUMOS_INCOMPLETOS, {isArray : true});
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
    	 
    	 return contrato;
    }]);
});