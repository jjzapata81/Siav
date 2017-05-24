/*global define*/
'use strict';

define(['siav-module', 'clave-services', 'modal-factory', 'constantes'], function (app) {
	
    return app.controller('seguridad-clave-controller', ['$scope', 'modalFactory', 'claveServices', 'CONSTANTES', function($scope, modalFactory, claveServices, CONSTANTES){
    	
    	$scope.init = function(){
    		$scope.clave = {};
    		$scope.confirmarPassword = null;
    		$scope.estaEditando = false;
    		$scope.coinciden = false;
    	}
    	
    	$scope.onCancelar = function(){
    		$scope.init();
    	}
    	
    	$scope.onCambiar = function(){
    		if($scope.validarCampos()){
    			claveServices
    			.cambiar($scope.clave)
    			.then(function(respuesta){
    				modalFactory.abrirDialogo(respuesta);
        			$scope.init();
    			});
    		}
    		$scope.estaEditando = false;
    	}
    	
    	$scope.verificarPass = function(){
    		$scope.coinciden = $scope.confirmarPassword && $scope.clave.nuevoPassword == $scope.confirmarPassword;
    	}
    	
    	$scope.validarCampos = function(){
    		if(!$scope.clave || !$scope.clave.usuario || !$scope.clave.password || !$scope.clave.nuevoPassword || !$scope.confirmarPassword){
    			modalFactory.abrir(CONSTANTES.ESTADO.ERROR, CONSTANTES.ERR.OBLIGATORIO);
    			return false;
    		}
    		if(!$scope.coinciden){
    			modalFactory.abrir(CONSTANTES.ESTADO.ERROR, CONSTANTES.SEGURIDAD.ERR_PASS);
    			return false;
    		}
    		return true;
    	}
    	
    	$scope.init();

    }])

});