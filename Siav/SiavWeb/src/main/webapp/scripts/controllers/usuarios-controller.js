/*global define*/
'use strict';

define(['siav-module', 'instalaciones-services', 'usuarios-services', 'veredas-services', 'modal-factory', 'constantes'], function (app) {
	
    return app.controller('usuarios-controller',
    		['$scope', '$location', 'instalacionesServices', 'usuariosServices', 'veredasServices', 'modalFactory', 'CONSTANTES', 
    		 function($scope, $location, instalacionesServices, usuariosServices, veredasServices, modalFactory, CONSTANTES){	
    	
    	$scope.init = function(){
    		$scope.estaEditando = false;
    		$scope.existeUsuario = false;
    		$scope.usuario = {};
    		$scope.buscarCedula = null;
    	}
    	
    	$scope.onBuscarPropietario = function(){
    		if($scope.buscarCedula){
    			usuariosServices
        		.buscar($scope.buscarCedula)
        		.then(function(usuario){
        			if(usuario.mensaje){
        				modalFactory.abrir(usuario.estado, usuario.mensaje);
        				$scope.init();
        			}else{
        				$scope.usuario = usuario;
        				$scope.existeUsuario = true;
        			}
        		});
    		}else{
    			modalFactory.abrir(CONSTANTES.ESTADO.ERROR, CONSTANTES.USUARIO.ERR_CEDULA_OBLIGATORIO);
    		}
    	}
    	
    	$scope.onEditar = function(){
    		$scope.estaEditando = true;
    		$scope.esActualizacion = true;
    		$scope.usuarioTemp = angular.copy($scope.usuario);
    	}
    	
    	$scope.onCancelarEditar = function(){
    		$scope.estaEditando = false;
    		$scope.usuario = $scope.usuarioTemp;
    	}
    	
    	$scope.onCrearUsuario = function(){
    		$scope.init();
    		$scope.estaEditando = true;
    		$scope.esActualizacion = false;
    	}
    	
    	$scope.onGuardar = function(){
    		if($scope.usuarioValido()){
    			delete($scope.usuario.nombreCompleto);
    			usuariosServices
        		.guardar($scope.usuario, $scope.esActualizacion)
        		.then(function(respuesta){
        			modalFactory.abrirDialogo(respuesta);
        			$scope.init();
        		});
    		}else{
    			modalFactory.abrir(CONSTANTES.ESTADO.ERROR, CONSTANTES.USUARIO.ERR_OBLIGATORIO);
    		}
    	}
    	
    	$scope.usuarioValido = function(){
    		return $scope.usuario.cedula && $scope.usuario.nombres && $scope.usuario.apellidos;
    	}
    	
    	$scope.onCancelar = function(){
    		$scope.init();
    	}
    	
    	$scope.init();

    }])

});