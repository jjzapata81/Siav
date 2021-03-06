/*global define*/
'use strict';

define(['siav-module', 'reportes-services', 'modal-email', 'modal-factory', 'constantes'], function (app) {
	
    return app.controller('reporte-prefactura-controller', ['$scope', '$window', '$sce', 'reportesServices', 'modalEmail', 'modalFactory', 'CONSTANTES', function($scope, $window, $sce, reportesServices, modalEmail, modalFactory, CONSTANTES){

    	$scope.content = {};
    	
    	$scope.isContent = false;
    	
    	var REPORTE = "prefactura";
    	
    	$scope.onDescargar = function(){
    		if($scope.validar()){
    			reportesServices
        		.descargar(REPORTE, "PrefacturaCiclo" + $scope.filtro.ciclo + ".xlsx", $scope.filtro);
        		$scope.limpiar();
    		}
    	}
    	
    	$scope.onBuscarPDF = function(){
    		if($scope.validar()){
    			reportesServices
        		.descargarPDF(REPORTE, "PrefacturaCiclo" + $scope.filtro.ciclo + ".pdf", $scope.filtro);
        		$scope.limpiar();
    		}
    	}
    	
    	
    	
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
        
//        $scope.onBuscarPDF = function(){
//    		if($scope.validar()){
//    			reportesServices
//    			.getPDF(REPORTE, $scope.filtro)
//    			.then(function(data){
//	                var file = new Blob([ data ], {type : 'application/pdf'});
//	                var fileURL = URL.createObjectURL(file);
//	                $scope.content = $sce.trustAsResourceUrl(fileURL);
//	                $scope.isContent = true;
//	                $scope.limpiar();
//    			});
//    		}
//    	}
        
        $scope.limpiar = function(){
        	$scope.filtro = null;
        }
        
        $scope.validar = function(){
        	if(!$scope.filtro || !$scope.filtro.ciclo){
        		modalFactory.abrir(CONSTANTES.ESTADO.ERROR, CONSTANTES.REPORTES.ERR_CICLO_OBLIGATORIO);
        		return false;
        	}
        	return true;
        }
    	
    }])
});