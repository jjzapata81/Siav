/*global define*/
'use strict';

define(['siav-module', 'constantes'], function (app) {
    app.service('materialesServices', ['$http', '$q', 'CONSTANTES', function ($http, $q, CONSTANTES) {
    	 var contrato = {
    			 crearEntrada : crearEntrada,
    			 crearSalida : crearSalida,
    			 crearExcel : crearExcel
    	 };
    	 
    	 function crearEntrada(entrada){
    		 var request = $http.post(CONSTANTES.SRV.MATERIAL_ENTRADA_CREAR, entrada);
    		 return (request.then(function(response) {
 	    		return response.data;
	    	}));
    	 }
    	 
    	 function crearSalida(salida){
    		 var request = $http.post(CONSTANTES.SRV.MATERIAL_SALIDA_CREAR, salida);
    		 return (request.then(function(response) {
 	    		return response.data;
	    	}));
    	 }
    	 
    	 function crearExcel(detalles, factura){
    		 $http.post(CONSTANTES.SRV.MATERIAL_EXCEL, detalles, {responseType: 'arraybuffer'})
    		 	.success(function (data, status, headers, config) {
    		 		var blob = new Blob([data], {type : 'application/pdf'});
    		 		var a = document.createElement('a');
    		 		a.href = (window.URL || window.webkitURL).createObjectURL(blob); 
	                a.target = '_blank';
	                a.download = 'comprobante_ingreso_' + factura + '.pdf';
	                document.body.appendChild(a);
	                a.click();
    		 });
    	 }
   	 
    	 return contrato;
    }]);
});