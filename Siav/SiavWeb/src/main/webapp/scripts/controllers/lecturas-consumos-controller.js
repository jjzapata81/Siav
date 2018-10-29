/*global define*/

'use strict';

define(['siav-module', 'reportes-services', 'modal-email', 'modal-factory', 'constantes'], function (app) {
	
    return app.controller('lecturas-consumos-controller', ['$scope', '$window', '$sce', 'reportesServices', 'modalEmail', 'modalFactory', 'CONSTANTES',
	  function($scope, $window, $sce, reportesServices, modalEmail, modalFactory, CONSTANTES){

    	$scope.content = {};
    	
    	var REPORTE = "lecturas-consumos";
    	
    	$scope.onDescargar = function(){
    		if($scope.validar()){
    			reportesServices
        		.descargar(REPORTE, "lecturas_consumos_ciclo" + $scope.filtro.ciclo + ".xlsx", $scope.filtro);
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
    			});
    		}
    	}
        
        $scope.validar = function(){
        	if(!$scope.filtro || !$scope.filtro.ciclo || $scope.filtro.ciclo < 1){
        		modalFactory.abrir(CONSTANTES.ESTADO.ERROR, CONSTANTES.REPORTES.ERR_CICLO_OBLIGATORIO);
        		return false;
        	}
        	if(($scope.filtro.valorDesde && $scope.filtro.valorDesde < 0)||($scope.filtro.valorHasta && $scope.filtro.valorHasta < 0)){
        		modalFactory.abrir(CONSTANTES.ESTADO.ERROR, CONSTANTES.REPORTES.ERR_NEGATIVO);
        		return false;
        	}
        	if($scope.filtro.valorHasta && $scope.filtro.valorHasta < $scope.filtro.valorDesde){
        		modalFactory.abrir(CONSTANTES.ESTADO.ERROR, CONSTANTES.REPORTES.ERR_LIMITE);
        		return false;
        	}
        	return true;
        }
        
    }])

});