/*global define*/
'use strict';

define(['siav-module', 'reportes-services', 'modal-email', 'modal-factory', 'constantes'], function (app) {
	
    return app.controller('reporte-instalaciones-ruta-controller', ['$scope', '$window', '$sce', 'reportesServices', 'modalEmail', 'modalFactory', 'CONSTANTES',
	  function($scope, $window, $sce, reportesServices, modalEmail, modalFactory, CONSTANTES){

    	$scope.content = {};
    	
    	$scope.isContent = false;
    	
    	$scope.filtro = {};
    	
    	var REPORTE = "instalaciones-ruta";
    	
    	$scope.onDescargar = function(){
    		reportesServices
    		.descargar(REPORTE, "instalaciones_ruta.xlsx", $scope.filtro);
    	}
    	
    	$scope.onEnviar = function(){
    		modalEmail
    		.abrir()
    		.result
    		.then(function(email){
    			$scope.filtro.email = email;
        		reportesServices
        		.enviar(REPORTE, $scope.filtro)
        		.then(function(response){
        			modalFactory.abrir(CONSTANTES.ESTADO.OK, CONSTANTES.REPORTES.ARCHIVO_ENVIADO);
        		});
    		});
    	}
        
        $scope.onBuscarPDF = function(){
        	reportesServices
			.getPDF(REPORTE, $scope.filtro)
			.then(function(data){
                var file = new Blob([ data ], {type : 'application/pdf'});
                var fileURL = URL.createObjectURL(file);
                $scope.content = $sce.trustAsResourceUrl(fileURL);
                $scope.isContent = true;
			});
    	}
        
        
    }])

});