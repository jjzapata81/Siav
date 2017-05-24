/*global define*/
'use strict';

define(['siav-module', 'usuario-sistema-services', 'modal-factory', 'constantes'], function (app) {
	
    return app.controller('seguridad-usuarios-controller', ['$scope', 'usuarioSistemaServices', 'modalFactory', 'CONSTANTES', function($scope, usuarioSistemaServices, modalFactory, CONSTANTES){
    	
    	$scope.init = function(){
    		$scope.limpiar();
    		$scope.consultarPerfiles();
    		$scope.consultarUsuarios();
    		$scope.editandoPerfil = false;
    	}
    	
    	$scope.consultarPerfiles = function(){
    		usuarioSistemaServices
    		.consultarPerfiles()
    		.then(function(perfiles){
    			$scope.perfiles = perfiles;
    		});
    	}
    	
    	$scope.consultarUsuarios = function(){
    		usuarioSistemaServices
    		.consultar()
    		.then(function(usuarios){
    			$scope.usuarios = usuarios;
    		});
    	}
    	
    	$scope.limpiar = function(){
    		$scope.esNuevo = false;
    		$scope.coinciden = false;
    		$scope.usuarioNuevo = {};
    		$scope.confirmarPassword = "";
    	}
    	
    	$scope.onAgregar = function(){
    		$scope.esNuevo = !$scope.esNuevo;
    	}
    	
    	$scope.onCancelar = function(){
    		$scope.limpiar();
    	}
    	
    	$scope.onCrear = function(){
    		if($scope.camposValidos()){
    			usuarioSistemaServices
        		.crear($scope.usuarioNuevo)
        		.then(function(respuesta){
        			modalFactory.abrirDialogo(respuesta);
        			$scope.init();
        		});
    		}
    	}
    	
    	$scope.onActualizar = function(){
    		if($scope.actualizarValido()){
    			delete($scope.usuarioEditar.nombreCompleto);
    			usuarioSistemaServices
        		.actualizar($scope.usuarioEditar)
        		.then(function(respuesta){
        			modalFactory.abrirDialogo(respuesta);
        			$scope.init();
        		});
    		}
    	}
    	
    	$scope.actualizarValido = function(){
    		if(!$scope.usuarioEditar.nombres){
    			modalFactory.abrir(CONSTANTES.ESTADO.ERROR, CONSTANTES.SEGURIDAD.ERR_NOMBRE);
    			return false;
    		}
    		if(!$scope.usuarioEditar.apellidos){
    			modalFactory.abrir(CONSTANTES.ESTADO.ERROR, CONSTANTES.SEGURIDAD.ERR_APELLIDO);
    			return false;
    		}
    		return true;
    	}
    	
    	$scope.onEditar = function(usuario){
    		$scope.esNuevo = false;
    		$scope.editandoPerfil = true;
    		$scope.usuarioEditar = angular.copy(usuario);
    	}
    	
    	$scope.onCancelarEditar = function(){
    		$scope.editandoPerfil = false;
    		$scope.usuarioEditar = null;
    	}
    	
   	
    	$scope.onCambioEstado = function(usuario){
    		delete(usuario.nombreCompleto);
    		usuario.activo = !usuario.activo;
    		usuarioSistemaServices
    		.cambioEstado(usuario)
    		.then(function(respuesta){
    			$scope.init();
    		});
    	}
    	
    	$scope.camposValidos = function(){
    		if(!$scope.usuarioNuevo.id){
    			modalFactory.abrir(CONSTANTES.ESTADO.ERROR, CONSTANTES.SEGURIDAD.ERR_IDENTIFICACION);
    			return false;
    		}
    		if(!$scope.usuarioNuevo.nombres){
    			modalFactory.abrir(CONSTANTES.ESTADO.ERROR, CONSTANTES.SEGURIDAD.ERR_NOMBRE);
    			return false;
    		}
    		if(!$scope.usuarioNuevo.apellidos){
    			modalFactory.abrir(CONSTANTES.ESTADO.ERROR, CONSTANTES.SEGURIDAD.ERR_APELLIDO);
    			return false;
    		}
    		if(!$scope.coinciden){
    			modalFactory.abrir(CONSTANTES.ESTADO.ERROR, CONSTANTES.SEGURIDAD.ERR_PASS);
    			return false;
    		}
    		return true;
    	}
    	
    	$scope.verificarPass = function(){
    		$scope.coinciden = $scope.confirmarPassword == $scope.usuarioNuevo.password
    	}
    	
    	$scope.init();
	

    }])

});