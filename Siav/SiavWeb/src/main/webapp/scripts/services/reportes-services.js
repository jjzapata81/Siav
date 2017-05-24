/*global define*/
'use strict';

define(['siav-module'], function (app) {
    app.service('reportesServices', ['$http', '$q', function ($http, $q) {
    	 var contrato = {
    			 buscar : buscar,
    			 getPDF : getPDF,
    			 descargar : descargar,
    			 enviar : enviar,
    			 instalacionesUsuario : instalacionesUsuario
    	 };
    	 
    	 function descargar(reporte, nombreArchivo, filtro){
    		 $http.post("/Reportes/rest/reports/" + reporte + "/download", filtro, {responseType: 'arraybuffer'})
    		 	.success(function (data, status, headers, config) {
    		 		var blob = new Blob([data], {type : 'application/vnd.ms-excel'});
    		 		var a = document.createElement('a');
    		 		a.href = (window.URL || window.webkitURL).createObjectURL(blob); 
	                a.target = '_blank';
	                a.download = nombreArchivo;
	                document.body.appendChild(a);
	                a.click();
    		 });
    	 }
    	 
    	 function enviar(reporte, filtro){
    		 var request = $http.put("/Reportes/rest/reports/" + reporte + "/send", filtro, {isArray : false});
    		 return (request.then(function(response) {
 	    		return response.data;
	    	}));
    	 }
    	 
    	 function getPDF(reporte, filtro){
    		 var request = $http.post("/Reportes/rest/reports/" + reporte + "/pdf", filtro, {responseType: 'arraybuffer'});
    		 return(request.then(function(response){
    			 return response.data;
    		 }));
    	 }
    	 
    	 function buscar(ciclo){
    		 var request = $http.get("rest/reportes/buscar/" + ciclo, {isArray : false});
    		 return (request.then(function(response) {
 	    		return response.data;
	    	}));
    	 }
    	 
    	 function instalacionesUsuario(filtro){
    		 var request = $http.post("/Reportes/rest/general/usuario/instalaciones", filtro, {isArray : true});
    		 return(request.then(function(response){
    			 return response.data;
    		 }));
    	 }
   	 
    	 return contrato;
    }]);
});