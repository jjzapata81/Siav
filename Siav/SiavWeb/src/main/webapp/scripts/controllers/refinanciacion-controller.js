/*global define*/
'use strict';

define(['siav-module', 'creditos-services', 'modal-factory', 'constantes'], function (app) {
	
    return app.controller('refinanciacion-controller', ['$scope', '$filter', 'creditosServices', 'modalFactory', 'CONSTANTES',
                                                        function($scope, $filter, creditosServices, modalFactory, CONSTANTES){
    	
    	$scope.init = function (){
    		$scope.limpiar();
    	}
    	
    	$scope.onCancelar = function(){
    		$scope.limpiar();
    	}
    	
    	$scope.onBuscar = function(){
    		$scope.mostrarUsuario = false;
    		if($scope.numeroInstalacion){
    			creditosServices
        		.buscar($scope.numeroInstalacion)
        		.then(function(respuesta){
        			$scope.mostrarUsuario = true;
    				$scope.instalacion.numeroInstalacion = respuesta.numeroInstalacion;
    				$scope.instalacion.nombreCompleto = respuesta.nombreCompleto;
    				$scope.creditos = respuesta.creditos;
        		});
    		}
    	}
    	
    	$scope.onGuardar = function(){
    		if($scope.formularioValido()){
    			$scope.credito.comprobante = $scope.comprobante; 
    			creditosServices
    			.refinanciar($scope.credito)
    			.then(function(){
    				$scope.limpiar();
    				modalFactory.abrir(CONSTANTES.ESTADO.OK, CONSTANTES.CREDITO.GUARDAR_EXITO);
    			});
    			
    		}
    		
    	}
    	
    	$scope.formularioValido = function(){
    		if(!$scope.credito || !$scope.credito.numeroCuotas){
    			modalFactory.abrir(CONSTANTES.ESTADO.ERROR, CONSTANTES.CREDITO.ERR_NUMERO_CUOTAS_OBLIGATORIO);
    			return false;
    		}
    		if(($scope.credito.interes && $scope.credito.interes < 0) ||  $scope.credito.numeroCuotas < 0 || ($scope.credito.inicial && $scope.credito.inicial < 0)){
    			modalFactory.abrir(CONSTANTES.ESTADO.ERROR, CONSTANTES.CREDITO.ERR_NEGATIVO);
    			return false;
    		}
    		if($scope.credito && $scope.credito.inicial > 0 && !$scope.comprobante){
    			modalFactory.abrir(CONSTANTES.ESTADO.ERROR, CONSTANTES.CREDITO.ERR_COMPROBANTE_OBLIGATORIO);
    			return false;
    		}
    		return true;
    	}
    	
    	$scope.limpiar = function(){
    		$scope.mostrarUsuario = false;
    		$scope.mostrarCredito = false;
    		$scope.credito = {};
    		$scope.instalacion = {};
    	}
    	
    	$scope.calculoCuotas = function(){
    		$scope.credito.inicial = !$scope.credito.inicial ? 0 : $scope.credito.inicial;
    		if($scope.credito && $scope.credito.valor && $scope.credito.numeroCuotas > 0){
    			$scope.valorCuota = (parseInt($scope.credito.valor) - parseInt($scope.credito.inicial)) / $scope.credito.numeroCuotas;
    		}
    	}
    	
    	$scope.seleccionar = function(credito){
    		$scope.mostrarCredito = true;
    		var creditoTemp = angular.copy(credito);
    		$scope.credito.valor = creditoTemp.saldo;
    		$scope.credito.id = creditoTemp.id;
    		
    	}
    	
    	$scope.onCancelarSeleccion = function(){
    		$scope.mostrarCredito = false;
    		$scope.credito = {};
    		$scope.valorCuota = null;
    	}
    	
    	
    	$scope.init();

    }])

});