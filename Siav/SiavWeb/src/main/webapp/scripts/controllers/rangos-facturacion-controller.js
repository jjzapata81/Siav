/*global define*/
'use strict';

define(['siav-module', 'rangos-services', 'modal-factory', 'constantes'], function (app) {
	
    return app.controller('rangos-facturacion-controller', ['$scope', 'rangosServices', 'modalFactory', 'CONSTANTES', function($scope, rangosServices, modalFactory, CONSTANTES){

    	$scope.init = function(){
    		$scope.esNuevo = false;
    		$scope.rangoNuevo = {};
    		$scope.consultarRangos();
    	}
    	
    	$scope.consultarRangos = function(){
    		rangosServices
    		.consultar()
    		.then(function(rangos){
    			$scope.rangos = rangos;
    		});
    	
    	}
    	
    	$scope.onAgregar = function(){
    		$scope.esNuevo = !$scope.esNuevo;
    	}
    	
    	$scope.onCancelar = function(){
    		$scope.tarifaNueva = {};
    		$scope.esNuevo = !$scope.esNuevo;
    	}
    	
    	$scope.onCrear = function(){
    		if($scope.camposValidos()){
    			rangosServices
        		.crear($scope.rangoNuevo)
        		.then(function(respuesta){
        			modalFactory.abrirDialogo(respuesta);
        			$scope.init();
        		});
    		}
    	}
    	
    	$scope.onEliminar = function(rango){
    		modalFactory.abrir(CONSTANTES.ESTADO.INFO, CONSTANTES.RANGO_FACTURACION.ELIMINAR_RANGO)
			.result
			.then(function(resultado) {
    			if(resultado){
    				$scope.continuarEliminar(rango);
    			}
			});
    	}
    	
    	$scope.continuarEliminar = function(rango){
    		rangosServices
    		.eliminar(rango)
    		.then(function(respuesta){
    			modalFactory.abrirDialogo(respuesta);
    			$scope.init();
    		});
    	}
    	
    	$scope.camposValidos = function(){
    		if(!$scope.rangoNuevo || !$scope.rangoNuevo.limiteInicial || !$scope.rangoNuevo.limiteFinal || !$scope.rangoNuevo.resolucion){
    			modalFactory.abrir(CONSTANTES.ESTADO.ERROR, CONSTANTES.ERR.OBLIGATORIO);
    			return false;
    		}
    		if($scope.rangoNuevo.limiteInicial <=0){
    			modalFactory.abrir(CONSTANTES.ESTADO.ERROR, CONSTANTES.RANGO_FACTURACION.ERR_LIMITE_INICIAL);
    			return false;
    		}
    		if($scope.rangoNuevo.limiteFinal < $scope.rangoNuevo.limiteInicial){
    			modalFactory.abrir(CONSTANTES.ESTADO.ERROR, CONSTANTES.RANGO_FACTURACION.ERR_LIMITE_FINAL);
    			return false;
    		}
    		return true;
    	}
    	
    	$scope.init();

    }])

});