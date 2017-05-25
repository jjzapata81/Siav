/*global define*/
'use strict';

define(['siav-module', 'creditos-services', 'pagos-services', 'modal-factory', 'constantes'], function (app) {
	
    return app.controller('credito-abono-controller', ['$scope', '$filter', 'creditosServices', 'pagosServices', 'modalFactory', 'CONSTANTES',
                                                        function($scope, $filter, creditosServices, pagosServices, modalFactory, CONSTANTES){
    	
    	$scope.content = null;
    	
    	$scope.init = function (){
    		$scope.esTotal = true;
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
    			creditosServices
    			.refinanciar($scope.credito)
    			.then(function(){
    				$scope.limpiar();
    				modalFactory.abrir(CONSTANTES.ESTADO.OK, CONSTANTES.CREDITO.GUARDAR_EXITO);
    			});
    		}
    	}
    	
    	$scope.onGuardar = function(){
    		if($scope.validarCampos()){
    			$scope.abono.numeroInstalacion = $scope.factura.numeroInstalacion;
    			$scope.abono.numeroFactura = "123456";
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