/*global define*/
'use strict';

define(['siav-module', 'bancos-services','modal-factory', 'constantes'], function (app) {
	
    return app.controller('cuentas-bancarias-controller', ['$scope', '$filter', 'bancosServices', 'modalFactory', 'CONSTANTES', function($scope, $filter, bancosServices, modalFactory, CONSTANTES){
    	
    	$scope.init = function(){
    		$scope.esNuevo = false;
    		$scope.esNuevoBanco = false;
    		$scope.esEdicion = false;
    		$scope.cargarBancos();
    		$scope.bancoNuevo = {};
    		$scope.nuevoBanco = null;
    	}
    	
    	$scope.cargarBancos = function(){
    		bancosServices
    		.consultar()
    		.then(function(bancos){
    			$scope.bancos = bancos;
    			$scope.cargarCuentas();
    		});
    	}
    	
    	$scope.cargarCuentas = function(){
    		bancosServices
    		.consultarCuentas()
    		.then(function(cuentas){
    			$scope.cuentas = cuentas;
    			angular.forEach($scope.cuentas, function(value, key) {
  				  value.banco = $filter('filter')($scope.bancos, { codigo : value.codigoBanco })[0];
    			});
    		});
    	}
    	
    	$scope.onAgregar = function(){
    		$scope.esNuevo = !$scope.esNuevo;
    	}
    	
    	$scope.onCancelar = function(){
    		$scope.bancoNuevo = {};
    		$scope.esNuevo = !$scope.esNuevo;
    		$scope.esEdicion = false;
    		$scope.nuevoBanco = null;
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
    	
    	$scope.onSeleccion = function(opcion){
    		$scope.esNuevoBanco = opcion === "NUEVO";
    		if(!$scope.esNuevoBanco){
    			$scope.nuevoBanco = null;
    		}
    	}
    	
    	$scope.onActualizar = function(){
    		modalFactory
    		.abrir(CONSTANTES.ESTADO.INFO, CONSTANTES.BANCO.INFO_MODIFICAR_CUENTA)
    		.result
    		.then(function(respuesta){
    			if($scope.esNuevoBanco){
        			$scope.bancoNuevo.nombreBanco = $scope.nuevoBanco;
        		}else{
        			$scope.bancoNuevo.codigoBanco = $scope.bancoNuevo.banco.codigo;
            		$scope.bancoNuevo.nombreBanco = $scope.bancoNuevo.banco.nombre;
        		}
        		delete($scope.bancoNuevo.banco);
    			bancosServices
        		.editar($scope.bancoNuevo)
        		.then(function(respuesta){
        			modalFactory.abrirDialogo(respuesta);
        			$scope.init();
        		});
    		});
    	}
    	
    	$scope.onCrear = function(){
    		if($scope.esNuevoBanco){
    			$scope.bancoNuevo.nombreBanco = $scope.nuevoBanco;
    		}else{
    			$scope.bancoNuevo.codigoBanco = $scope.bancoNuevo.banco.codigo;
        		$scope.bancoNuevo.nombreBanco = $scope.bancoNuevo.banco.nombre;
    		}
    		delete($scope.bancoNuevo.banco);
    		bancosServices
    		.crear($scope.bancoNuevo)
    		.then(function(respuesta){
    			modalFactory.abrirDialogo(respuesta);
    			$scope.init();
    		});
    	}
    	
    	$scope.onImprimir = function(){
    		window.print();
    	}
    	
    	$scope.init();

    }])

});