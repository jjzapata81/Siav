/*global define*/
'use strict';

define(['siav-module', 'constantes'], function (app) {
    app.service('reportesServices', ['$http', '$q', 'CONSTANTES', function ($http, $q, CONSTANTES) {
    	 var contrato = {
    			 buscar : buscar,
    			 getPDF : getPDF,
    			 sendMail : sendMail,
    			 descargar : descargar,
    			 enviar : enviar,
    			 instalacionesUsuario : instalacionesUsuario
    	 };
    	 
    	 function descargar(reporte, nombreArchivo, filtro){
    		 $http.post(CONSTANTES.SRV.REPORTES_DESCARGAR + reporte + "/download", filtro, {responseType: 'arraybuffer'})
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
    		 var request = $http.put(CONSTANTES.SRV.REPORTES_ENVIAR + reporte + "/send", filtro, {isArray : false});
    		 return (request.then(function(response) {
 	    		return response.data;
	    	}));
    	 }
    	 
    	 function getPDF(reporte, filtro){
    		 var request = $http.post(CONSTANTES.SRV.REPORTES_PDF + reporte + "/pdf", filtro, {responseType: 'arraybuffer'});
    		 return(request.then(function(response){
    			 return response.data;
    		 }));
    	 }
    	 
    	 function sendMail(){
    		 var request = $http.get(CONSTANTES.SRV.REPORTES_SEND_MAIL, { silent : true, ignoreLoadingBar: true});
    		 return(request.then(function(response){
    			 return response.data;
    		 }));
    	 }
    	 
    	 function buscar(ciclo){
    		 var request = $http.get(CONSTANTES.SRV.REPORTES_BUSCAR + ciclo, {isArray : false});
    		 return (request.then(function(response) {
 	    		return response.data;
	    	}));
    	 }
    	 
    	 function instalacionesUsuario(filtro){
    		 var request = $http.post(CONSTANTES.SRV.REPORTES_INSTALACIONES_USUARIO, filtro, {isArray : true});
    		 return(request.then(function(response){
    			 return response.data;
    		 }));
    	 }
   	 
    	 return contrato;
    }]);
});