/*global define*/
'use strict';

define(['siav-module', 'instalaciones-services', 'tarifas-services', 'creditos-services', 'contabilidad-services', 'modal-factory', 'constantes'], function (app) {
	
    return app.controller('creditos-controller', ['$scope', '$filter', 'instalacionesServices', 'tarifasServices', 'creditosServices', 'contabilidadServices', 'modalFactory', 'CONSTANTES',
                                                  function($scope, $filter, instalacionesServices, tarifasServices, creditosServices, contabilidadServices, modalFactory, CONSTANTES){
    	
    	$scope.init = function (){
    		$scope.consultarTarifas();
    		$scope.mostrarUsuario = false;
    		$scope.mostrarTarifa = false;
    		$scope.mostrarComprobante = false;
    		$scope.numeroInstalacion = null;
    		$scope.credito = {};
    		$scope.instalacion = null;
    		$scope.tarifa = null;
    		$scope.creditos = null;
    		$scope.valorCuota = null;
    	}
    	
    	$scope.onCancelar = function(){
    		$scope.init();
    	}
    	
    	$scope.onBuscar = function(){
    		$scope.mostrarUsuario = false;
    		if($scope.numeroInstalacion){
    			instalacionesServices
        		.buscarInstalacion($scope.numeroInstalacion)
        		.then(function(respuesta){
        			if(respuesta.mensaje){
        				modalFactory.abrirDialogo(respuesta);
        				$scope.numeroInstalacion = null;
        			}else{
        				creditosServices
                		.buscar($scope.numeroInstalacion)
                		.then(function(respuestaCredito){
            				$scope.creditos = respuestaCredito.creditos;
                		});
        				$scope.mostrarUsuario = true;
        				$scope.instalacion = respuesta.instalacion;
        			}
        		});
    		}
    	}
    	
    	$scope.consultarTarifas = function(){
    		tarifasServices
    		.consultarTarifaCredito()
    		.then(function(tarifas){
    			$scope.tarifas = tarifas;
    		});
    	}
    	
    	$scope.onSeleccionar = function(){
    		if("1" == $scope.tarifaCredito.tipo){
    			$scope.esFijo = true;
    			$scope.credito.valor = $scope.tarifaCredito.estrato0; 
    		}else{
    			$scope.esFijo = false;
    			$scope.credito.valor = null; 
    		}
    	}
    	
    	$scope.onGuardar = function(){
    		if($scope.formularioValido()){
    			$scope.credito.codigoConcepto = $scope.tarifaCredito.codigo;
    			$scope.credito.instalacion = $scope.instalacion.numeroInstalacion;
    			$scope.credito.comprobante = $scope.comprobante;
    			creditosServices
    			.guardar($scope.credito)
    			.then(function(){
    				$scope.init();
    				modalFactory.abrir(CONSTANTES.ESTADO.OK, CONSTANTES.CREDITO.GUARDAR_EXITO);
    			});
    			
    		}
    		
    	}
    	
    	$scope.formularioValido = function(){
    		if(!$scope.instalacion){
       			modalFactory.abrir(CONSTANTES.ESTADO.ERROR, CONSTANTES.CREDITO.ERR_INSTALACION_OBLIGATORIO);
    			return false;
    		}
    		if(!$scope.tarifaCredito){
       			modalFactory.abrir(CONSTANTES.ESTADO.ERROR, CONSTANTES.CREDITO.ERR_TARIFA_OBLIGATORIO);
    			return false;
    		}
    		if(!$scope.credito || $scope.credito.inicial < 0 || $scope.credito.interes < 0){
       			modalFactory.abrir(CONSTANTES.ESTADO.ERROR, CONSTANTES.CREDITO.ERR_NEGATIVO);
    			return false;
    		}
    		if(!$scope.credito.numeroCuotas || $scope.credito.numeroCuotas <= 0){
    			modalFactory.abrir(CONSTANTES.ESTADO.ERROR, CONSTANTES.CREDITO.ERR_NUMERO_CUOTAS_OBLIGATORIO);
    			return false;
    		}
    		if(!$scope.credito.valor || $scope.credito.valor <= 0){
    			modalFactory.abrir(CONSTANTES.ESTADO.ERROR, CONSTANTES.CREDITO.ERR_VALOR_OBLIGATORIO);
    			return false;
    		}
    		if($scope.credito && $scope.credito.inicial > 0 && !$scope.comprobante){
    			modalFactory.abrir(CONSTANTES.ESTADO.ERROR, CONSTANTES.CREDITO.ERR_COMPROBANTE_OBLIGATORIO);
    			return false;
    		}
    		return true;
    	}
    	
    	$scope.calculoCuotas = function(){
    		if($scope.credito && ($scope.credito.valor || $scope.credito.valor >= 0) && ($scope.credito.inicial || $scope.credito.inicial >= 0) && $scope.credito.numeroCuotas > 0){
    			$scope.valorCuota = (parseInt($scope.credito.valor) - $scope.credito.inicial) / $scope.credito.numeroCuotas;
    		}
    	}
    	
    	$scope.init();

    }])

});