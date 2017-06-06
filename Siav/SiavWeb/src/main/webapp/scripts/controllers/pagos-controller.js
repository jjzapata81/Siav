/*global define*/
'use strict';

define(['siav-module', 'bancos-services', 'pagos-services', 'modal-factory', 'constantes'], function (app) {
	
    return app.controller('pagos-controller', ['$scope', 'bancosServices', 'pagosServices', 'modalFactory', 'CONSTANTES', function($scope, bancosServices, pagosServices, modalFactory, CONSTANTES){
    	
    	$scope.init = function(){
    		$scope.limpiar();
    		$scope.consultarCuentas();
    		$scope.puntoPago = "RECAUDO CONSIGNACIÓN CORREO O FAX";
    	}
    	
    	$scope.onBuscar = function(){
    		$scope.mostrarUsuario = false;
    		if($scope.numeroInstalacion){
    			pagosServices
        		.buscar($scope.numeroInstalacion)
        		.then(function(factura){
        			if(factura.mensaje){
        				modalFactory.abrirDialogo(factura);
        				$scope.numeroInstalacion = null;
        			}else{
        				$scope.mostrarUsuario = true;
        				$scope.factura = factura;
        			}
        		});
    		}else{
    			modalFactory.abrir(CONSTANTES.ESTADO.ERROR, CONSTANTES.INSTALACION.ERR_BUSQUEDA_OBLIGATORIO);
    		}
    	}
    	
    	$scope.consultarCuentas = function(){
    		bancosServices
    		.consultarCuentas()
    		.then(function(bancos){
    			$scope.bancos = bancos;
    		});
    	}
    	
    	$scope.onCancelar = function(){
    		$scope.init();
    	}
    	
    	
    	$scope.onGuardar = function(){
    		if($scope.camposValidos()){
    			$scope.pago.numeroFactura = $scope.factura.numeroFactura;
    			$scope.pago.codigoCuenta = $scope.cuentaSeleccionada.codigo;
    			pagosServices
    			.pagar($scope.pago)
    			.then(function(respuesta){
    				modalFactory.abrirDialogo(respuesta);
    				$scope.init();
    			});
    		}
    	}
    	
    	$scope.camposValidos = function(){
    		if(!$scope.cuentaSeleccionada
    				|| !$scope.pago
    				|| !$scope.pago.fecha){
    			modalFactory.abrir(CONSTANTES.ESTADO.ERROR, CONSTANTES.ERR.OBLIGATORIO);
    			return false;
    			
    		}
    		return true;
    	}
    	
    	$scope.limpiar = function(){
    		$scope.mostrarUsuario = false;
    		$scope.numeroInstalacion = null;
    		$scope.instalacion = {};
    		$scope.pago = {};
    		$scope.pago.fecha = new Date();
    	}
    	$scope.init();
	

    }])

});