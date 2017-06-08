/*global define*/
'use strict';

define(['siav-module', 'macros-services', 'modal-factory', 'constantes'], function (app) {
	
    return app.controller('medidor-general-controller', ['$scope', 'macrosServices', 'modalFactory', 'CONSTANTES', function($scope, macrosServices, modalFactory, CONSTANTES){
    	
    	$scope.init = function(){
    		$scope.consultarMacros();
    		$scope.macroNuevo = {};
    		$scope.esNuevo = false;
    		$scope.estaEditando = false;
    		$scope.accion = null;
    	}
    	
    	$scope.consultarMacros = function(){
    		macrosServices
    		.consultar()
    		.then(function(macros){
    			$scope.macros = macros;
    		});
    	}
    	
    	$scope.onAgregar = function(){
    		$scope.esNuevo = true;
    		$scope.accion = "guardar";
    	}
    	
    	$scope.onGuardar = function(){
    		if($scope.validarGuardar()){
    			macrosServices
        		.guardar($scope.macroNuevo, $scope.accion)
        		.then(function(respuesta){
        			modalFactory.abrirDialogo(respuesta);
        			$scope.init();
        		});
    		}
    	}
    	
    	$scope.validarGuardar = function(){
    		if(!$scope.macroNuevo ||!$scope.macroNuevo.nombre){
    			modalFactory.abrir(CONSTANTES.ESTADO.ERROR, CONSTANTES.MACROS.ERR_MACRO_NOMBRE);
    			return false;
    		}
    		return true;
    	}
    	
    	$scope.onEditar=function(macro){
    		$scope.estaEditando = true;
    		$scope.macroNuevo = angular.copy(macro);
    		$scope.accion = "actualizar";
    	}
    	
    	$scope.onCancelar = function(){
    		$scope.init();
    	}
    	
    	$scope.init();

    }])

});