/*global define*/
'use strict';

define(['siav-module', 'bancos-services','modal-factory', 'constantes'], function (app) {
	
    return app.controller('cuentas-bancarias-controller', ['$scope', 'bancosServices', 'modalFactory', 'CONSTANTES', function($scope, bancosServices, modalFactory, CONSTANTES){
    	
    	$scope.init = function(){
    		$scope.esNuevo = false;
    		$scope.esEdicion = false;
    		$scope.cargarBancos();
    		$scope.bancoNuevo = {};
    	}
    	
    	$scope.cargarBancos = function(){
    		bancosServices
    		.consultar()
    		.then(function(bancos){
    			$scope.bancos = bancos;
    		});
    	}
    	
    	$scope.onAgregar = function(){
    		$scope.esNuevo = !$scope.esNuevo;
    	}
    	
    	$scope.onCancelar = function(){
    		$scope.bancoNuevo = {};
    		$scope.esNuevo = !$scope.esNuevo;
    		$scope.esEdicion = false;
    	}
    	
    	$scope.onEditar = function(banco){
    		$scope.esNuevo = true;
    		$scope.esEdicion = true;
    		$scope.bancoNuevo = angular.copy(banco);
    	}
    	
    	$scope.onGuardar = function(){
    		if($scope.bancoNuevo.nombre && $scope.bancoNuevo.numeroCuenta){
    			if($scope.esEdicion){
    				$scope.onActualizar();
        		}else{
        			$scope.onCrear();
        		}
    		}else{
    			modalFactory.abrir(CONSTANTES.ESTADO.ERROR, CONSTANTES.BANCO.ERR_OBLIGATORIO);
    		}
    		
    	}
    	
    	$scope.onActualizar = function(){
    		modalFactory
    		.abrir(CONSTANTES.ESTADO.INFO, CONSTANTES.BANCO.INFO_MODIFICAR_CUENTA)
    		.result
    		.then(function(respuesta){
    			bancosServices
        		.editar($scope.bancoNuevo)
        		.then(function(respuesta){
        			modalFactory.abrirDialogo(respuesta);
        			$scope.limpiarCampos();
        		});
    		});
    	}
    	
    	$scope.onCrear = function(){
    		bancosServices
    		.crear($scope.bancoNuevo)
    		.then(function(respuesta){
    			modalFactory.abrirDialogo(respuesta);
    			$scope.limpiarCampos();
    		});
    	}
    	
    	$scope.limpiarCampos = function(){
    		$scope.init();
    	}
    	
    	$scope.onImprimir = function(){
    		window.print();
    	}
    	
    	$scope.init();

    }])

});