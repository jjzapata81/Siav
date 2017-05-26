/*global define*/
'use strict';

define(['siav-module', 'creditos-services', 'pagos-services', 'modal-factory', 'constantes'], function (app) {
	
    return app.controller('credito-abono-controller', ['$scope', '$sce', '$filter', 'creditosServices', 'pagosServices', 'modalFactory', 'CONSTANTES',
                                                        function($scope, $sce, $filter, creditosServices, pagosServices, modalFactory, CONSTANTES){
    	
    	$scope.content = null;
    	
    	$scope.init = function (){
    		$scope.esTotal = true;
    		$scope.limpiar();
    	}
    	
    	$scope.onCancelar = function(){
    		$scope.limpiar();
    	}
    	
    	$scope.onBuscar = function(){
    		$scope.content = null;
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
    			$scope.abono.numeroInstalacion = $scope.instalacion.numeroInstalacion;
    			$scope.abono.numeroFactura = "123456";
    			$scope.abono.valor = $scope.esTotal ? $scope.credito.valor : $scope.credito.otroValor;
    			pagosServices
    			.abonar($scope.abono)
    			.then(function(data){
	                var file = new Blob([ data ], {type : 'application/pdf'});
	                var fileURL = URL.createObjectURL(file);
	                $scope.content = $sce.trustAsResourceUrl(fileURL);
	                $scope.limpiar();
    			});
    		}
    	}
    	
    	$scope.formularioValido = function(){
    		if(!$scope.esTotal && $scope.credito.otroValor > $scope.credito.valor){
    			modalFactory.abrir(CONSTANTES.ESTADO.ERROR, CONSTANTES.CREDITO.ERR_VALOR_SUPERIOR);
    			return false;
    		}
    		return true;
    	}
    	
    	$scope.limpiar = function(){
    		$scope.mostrarUsuario = false;
    		$scope.mostrarCredito = false;
    		$scope.credito = {};
    		$scope.instalacion = {};
    		$scope.abono = {};
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
    	
    	$scope.onSeleccion = function(valor){
    		$scope.esTotal = valor === 'TOTAL';
    		if($scope.esTotal){
    			$scope.credito.otroValor = null;
    		}
    	}
    	$scope.init();

    }])

});