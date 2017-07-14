/*global define*/
'use strict';

define(['siav-module', 'instalaciones-services', 'tarifas-services', 'novedades-services', 'modal-factory', 'constantes'], function (app) {
	
    return app.controller('nota-credito-controller', ['$scope', '$filter', 'instalacionesServices', 'tarifasServices', 'novedadesServices', 'modalFactory', 'CONSTANTES', function($scope, $filter, instalacionesServices, tarifasServices, novedadesServices, modalFactory, CONSTANTES){
    	
    	$scope.init = function (){
    		$scope.mostrarUsuario = false;
    		$scope.numeroInstalacion = null;
    		$scope.novedad = {};
    		$scope.novedades = null;
    	}
    	
    	$scope.onBuscar = function(){
    		$scope.mostrarUsuario = false;
    		if($scope.numeroInstalacion){
    			instalacionesServices
        		.buscarInstalacion($scope.numeroInstalacion)
        		.then(function(respuesta){
        			if(respuesta.mensaje){
        				modalFactory.abrir(respuesta.estado, respuesta.mensaje);
        				$scope.numeroInstalacion = null;
        			}else{
        				$scope.mostrarUsuario = true;
        				$scope.instalacion = respuesta.instalacion;
//        				$scope.consultarNotaCredito();
        			}
        		});
    		}else{
    			modalFactory.abrir(CONSTANTES.ESTADO.ERROR, CONSTANTES.INSTALACION.ERR_BUSQUEDA_OBLIGATORIO);
    		}
    	}
    	
//    	$scope.consultarNotaCredito = function(){
//    		novedadesServices
//    		.consultarNotaCredito($scope.instalacion.numeroInstalacion)
//    		.then(function(notaCredito){
//    			$scope.notaCredito = notaCredito;
//    		});
//    	}
    	
    	$scope.onGuardar = function(){
    		if($scope.formularioValido()){
    			var request = {};
    			request.numeroInstalacion = $scope.instalacion.numeroInstalacion;
    			request.valor = $scope.valor;
    			$scope.novedad.id.instalacion = $scope.instalacion.numeroInstalacion;
    			novedadesServices
    			.guardarNotaCredito(request)
    			.then(function(respuesta){
    				modalFactory.abrirDialogo(respuesta);
    				$scope.limpiar();
    			});
    		}
    	}
    	
  
    	
    	$scope.formularioValido = function(){
    		if(!$scope.instalacion || !$scope.valor){
    			modalFactory.abrir(CONSTANTES.ESTADO.ERROR, CONSTANTES.ERR.OBLIGATORIO);
    			return false;
    		}
    		return true;
    	}
    	
    	$scope.limpiar = function(){
    		$scope.init();
    		$scope.instalacion = null;
    	}
    	
    	$scope.onCancelar = function(){
    		$scope.init();
    	}
    	
    	$scope.init();

    }])

});