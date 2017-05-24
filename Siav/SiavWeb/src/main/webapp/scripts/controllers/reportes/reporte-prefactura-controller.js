/*global define*/
'use strict';

define(['siav-module', 'reportes-services', 'modal-email', 'modal-factory', 'constantes'], function (app) {
	
    return app.controller('reporte-prefactura-controller', ['$scope', '$window', '$sce', 'reportesServices', 'modalEmail', 'modalFactory', 'CONSTANTES', function($scope, $window, $sce, reportesServices, modalEmail, modalFactory, CONSTANTES){

    	$scope.content = {};
    	
    	var REPORTE = "prefactura";
    	
    	$scope.onDescargar = function(){
    		if($scope.validar()){
    			reportesServices
        		.descargar(REPORTE, "PrefacturaCiclo" + $scope.filtro.ciclo + ".xlsx", $scope.filtro);
        		$scope.limpiar();
    		}
    	}
    	
    	$scope.onEnviar = function(){
    		if($scope.validar()){
    			modalEmail
        		.abrir()
        		.result
        		.then(function(email){
        			console.log(email);
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
    			.getPDF(REPORTE, $scope.filtro)
    			.then(function(data){
	                var file = new Blob([ data ], {type : 'application/pdf'});
	                var fileURL = URL.createObjectURL(file);
	                $scope.content = $sce.trustAsResourceUrl(fileURL);
	                $scope.limpiar();
    			});
    		}
    	}
        
        $scope.limpiar = function(){
        	$scope.filtro = null;
        }
        
        $scope.validar = function(){
        	if(!$scope.filtro || !$scope.filtro.ciclo){
        		modalFactory.abrir(CONSTANTES.ESTADO.ERROR, CONSTANTES.ERR.OBLIGATORIO);
        		return false;
        	}
        	return true;
        }
    	
    }])
});