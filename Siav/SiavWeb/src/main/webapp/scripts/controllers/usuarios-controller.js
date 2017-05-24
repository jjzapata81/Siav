/*global define*/
'use strict';

define(['siav-module', 'instalaciones-services', 'usuarios-services', 'veredas-services', 'modal-factory', 'constantes', 'factoria-usuario'], function (app) {
	
    return app.controller('usuarios-controller',
    		['$scope', '$location', 'instalacionesServices', 'usuariosServices', 'veredasServices', 'modalFactory', 'CONSTANTES', 'usuarioFactory',
    		 function($scope, $location, instalacionesServices, usuariosServices, veredasServices, modalFactory, CONSTANTES, usuarioFactory){	
    	
    	$scope.init = function(){
    		$scope.estaEditando = false;
    		$scope.existeUsuario = false;
    		$scope.editandoInstalacion = false;
    		$scope.usuario = {};
    		$scope.instalacion = usuarioFactory.getInstalacion();
    		if($scope.instalacion){
    			$scope.usuario = $scope.instalacion.usuario;
    			$scope.estaEditando = true;
        		$scope.existeUsuario = true;
        		$scope.editandoInstalacion = true;
        		$scope.esActualizacion = true;
    		}
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
        			if(!$scope.editandoInstalacion){
        				modalFactory.abrirDialogo(respuesta);
        			}
        			$scope.goToInstalaciones(respuesta.estado);
        		});
    		}else{
    			modalFactory.abrir(CONSTANTES.ESTADO.ERROR, CONSTANTES.USUARIO.ERR_OBLIGATORIO);
    		}
    	}
    	
    	$scope.usuarioValido = function(){
    		return $scope.usuario.cedula && $scope.usuario.nombres && $scope.usuario.apellidos;
    	}
    	
    	$scope.onCancelar = function(){
    		$scope.goToInstalaciones('CANCELAR');
    	}
    	
    	$scope.goToInstalaciones = function(estado){
    		if($scope.editandoInstalacion){
    			$scope.instalacion.usuario = estado === 'ERROR' ? null : $scope.usuario;
    			usuarioFactory.setInstalacion($scope.instalacion);
    			$location.path("/instalaciones");
    		}
    		$scope.init();
    	}
    	
    	$scope.init();

    }])

});