/*global define*/
'use strict';

define(['siav-module', 'reportes-services', 'modal-email', 'modal-factory', 'constantes'], function (app) {
	
    return app.controller('cartera-total-controller', ['$scope', '$sce', 'reportesServices', 'modalEmail', 'modalFactory', 'CONSTANTES',
	  function($scope, $sce, reportesServices, modalEmail, modalFactory, CONSTANTES){

    	$scope.content = null;
    	
    	$scope.filtro = {};
    	
    	$scope.isContent = false;
    	
    	var REPORTE = "cartera-total";
    	
    	$scope.onDescargar = function(){
			reportesServices
    		.descargar(REPORTE, "cartera.xlsx", $scope.filtro);
    	}
    	
    	$scope.onBuscarPDF = function(){
    		reportesServices
    		.descargarPDF(REPORTE, "cartera.pdf", $scope.filtro);
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
        
//        $scope.onBuscarPDF = function(){
//			reportesServices
//			.getPDF(REPORTE, $scope.filtro)
//			.then(function(data){
//                var file = new Blob([ data ], {type : 'application/pdf'});
//                var fileURL = URL.createObjectURL(file);
//                $scope.content = $sce.trustAsResourceUrl(fileURL);
//                $scope.isContent = true;
//			});
//    	}
        
    }])

});