/*global define*/
'use strict';

define(['siav-module', 'factura-services', 'ciclos-services', 'reportes-services', 'modal-factory'], function (app) {
	
    return app.controller('prefacturacion-controller', ['$scope', '$sce', 'facturaServices', 'ciclosServices', 'reportesServices', 'modalFactory',
                                                        function($scope, $sce,  facturaServices, ciclosServices, reportesServices, modalFactory){
    	
    	var REPORTE = "prefactura";
    	
    	$scope.content = {};
    	
    	$scope.init = function(){
    		$scope.isContent = false;
    		$scope.sinPDF = true;
    		ciclosServices
    		.consultar()
    		.then(function(ciclo){
    			$scope.ciclo = ciclo;
    		});
    	}
    	
    	$scope.prefacturar = function(){
    		facturaServices
    		.prefacturar()
    		.then(function(respuesta){
    			modalFactory.abrir(respuesta.estado, respuesta.mensaje);
    			$scope.onBuscarPDF(respuesta.estado);
    		});
    	}
    	
    	$scope.onBuscarPDF = function(respuesta){
    		if("OK"===respuesta){
    			$scope.filtro = {};
    			$scope.filtro.ciclo = $scope.ciclo.ciclo;
    			reportesServices
    			.getPDF(REPORTE, $scope.filtro)
    			.then(function(data){
	                var file = new Blob([ data ], {type : 'application/pdf'});
	                var fileURL = URL.createObjectURL(file);
	                $scope.content = $sce.trustAsResourceUrl(fileURL);
	                $scope.isContent = true;
	                $scope.sinPDF = false;
    			});
    		}
    	}
    	
    	$scope.init();
    	
    }])

});