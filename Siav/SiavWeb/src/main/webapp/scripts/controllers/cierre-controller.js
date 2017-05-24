/*global define*/
'use strict';

define(['siav-module', 'ciclos-services', 'modal-factory', 'constantes'], function (app) {
	
    return app.controller('cierre-controller', ['$scope', 'ciclosServices',  'modalFactory', 'CONSTANTES', function($scope, ciclosServices,  modalFactory, CONSTANTES){
    	
    	$scope.init = function(){
    		$scope.cargarCiclo();
    	}
    	
    	$scope.cargarCiclo = function(){
    		ciclosServices
    		.consultar()
    		.then(function(ciclo){
    			$scope.ciclo = ciclo;
    		});
    	}
    	
    	$scope.onCerrar = function(){
    		if($scope.camposValidos()){
    			modalFactory
        		.abrir(CONSTANTES.ESTADO.INFO, CONSTANTES.CIERRE.CONFIRMAR_CIERRE + $scope.ciclo.ciclo + CONSTANTES.CIERRE.CONFIRMAR_CIERRE_COMPLEMENTO)
        		.result
        		.then(function(){
        			ciclosServices
        			.cerrar($scope.ciclo.ciclo)
        			.then(function(respuesta){
        				modalFactory.abrirDialogo(respuesta);
        				$scope.init();
        			});
        		});
    		}
    	}
    	
    	$scope.camposValidos = function(){
    		if(!$scope.ciclo.fechaConRecargo || !$scope.ciclo.fechaSinRecargo){
    			modalFactory.abrir(CONSTANTES.ESTADO.ERROR, CONSTANTES.CIERRE.ERR_SIN_FECHA);
    			return false;
    		}
    		return true;
    	}
    	
    	$scope.init();

    }])

});