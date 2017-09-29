/*global define*/
'use strict';

define(['siav-module', 'reportes-services', 'factura-services', 'ciclos-services', 'modal-factory', 'constantes'], function (app) {
	
    return app.controller('factura-controller', ['$scope', '$sce', 'reportesServices', 'facturaServices', 'ciclosServices', 'modalFactory', 'CONSTANTES', function($scope, $sce, reportesServices, facturaServices, ciclosServices, modalFactory, CONSTANTES){

    	$scope.content = {};
    	
    	$scope.isContent = false;
    	
    	$scope.init= function(){
    		ciclosServices
    		.consultarEstado(CONSTANTES.CICLO.CERRADO)
    		.then(function(ciclo){
    			$scope.ciclo = ciclo;
    		});
    	}
    	
    	var REPORTE = "factura-ciclo";
    	
    	$scope.onFacturar = function(){
    		facturaServices
    		.consultarFacturas()
    		.then(function(response){
    			modalFactory.abrirDialogo(response)
    			.result
    			.then(function(resultado) {
        			if(resultado){
        				$scope.continuar();
        			}
    			});
    		});
    	}
    	       
        $scope.continuar = function(){
        	var filtro = {};
        	reportesServices
			.getPDF(REPORTE, filtro)
			.then(function(data){
                var file = new Blob([ data ], {type : 'application/pdf'});
                var fileURL = URL.createObjectURL(file);
                $scope.isContent = true;
                $scope.content = $sce.trustAsResourceUrl(fileURL);
			});
        	$scope.enviarEmail();
    	}
        
        $scope.enviarEmail = function(){
        	reportesServices
        	.sendMail()
			.then(function(response){});
    	}
        
        $scope.init();
    	
    }])
});