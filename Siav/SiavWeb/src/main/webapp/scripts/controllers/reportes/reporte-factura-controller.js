/*global define*/
'use strict';

define(['siav-module', 'reportes-services', 'modal-email', 'modal-factory', 'constantes'], function (app) {
	
    return app.controller('reporte-factura-controller', ['$scope', '$window', '$sce', 'reportesServices', 'modalEmail', 'modalFactory', 'CONSTANTES', function($scope, $window, $sce, reportesServices, modalEmail, modalFactory, CONSTANTES){

    	$scope.content = {};
    	
    	$scope.isContent = false;
    	
    	var REPORTE = "factura";
    	
    	$scope.onEnviar = function(){
    		if($scope.validar()){
    			modalEmail
        		.abrir()
        		.result
        		.then(function(email){
        			$scope.filtro.email = email;
            		reportesServices
            		.enviar(REPORTE, $scope.filtro)
            		.then(function(response){
            			modalFactory.abrir(CONSTANTES.ESTADO.OK, CONSTANTES.REPORTES.ARCHIVO_ENVIADO);
            			$scope.limpiar();
            		});
        		});
    		}
    	}
    	
    	$scope.onBuscarPDF = function(){
    		if($scope.validar()){
    			reportesServices
        		.descargarPDF(REPORTE, "facturacion_ciclo" + $scope.filtro.ciclo + ".pdf", $scope.filtro);
    		}
    	}
        
//        $scope.onBuscarPDF = function(){
//    		if($scope.validar()){
//    			reportesServices
//    			.getPDF(REPORTE, $scope.filtro)
//    			.then(function(data){
//	                var file = new Blob([ data ], {type : 'application/pdf'});
//	                var fileURL = URL.createObjectURL(file);
//	                $scope.isContent = true;
//	                $scope.content = $sce.trustAsResourceUrl(fileURL);
//	                $scope.limpiar();
//    			});
//    		}
//    	}
        
        $scope.limpiar = function(){
        	$scope.filtro = null;
        }
        
        $scope.validar = function(){
        	if(!$scope.filtro || !$scope.filtro.ciclo || $scope.filtro.ciclo < 1){
        		modalFactory.abrir(CONSTANTES.ESTADO.ERROR, CONSTANTES.REPORTES.ERR_CICLO_OBLIGATORIO);
        		return false;
        	}
        	return true;
        }
    	
    }])
});