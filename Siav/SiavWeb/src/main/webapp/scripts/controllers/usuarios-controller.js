/*global define*/
'use strict';

define(['siav-module', 'instalaciones-services', 'usuarios-services', 'veredas-services', 'causas-no-lectura-services', 'modal-factory', 'modal-resultados-factory', 'modal-info-usuario-factory', 'constantes'], function (app) {
	
    return app.controller('usuarios-controller',
    		['$scope', '$location', '$filter', 'instalacionesServices', 'usuariosServices', 'veredasServices', 'causasServices', 'modalFactory', 'modalResultados', 'modalInfoUsuario', 'CONSTANTES', 
    		 function($scope, $location, $filter, instalacionesServices, usuariosServices, veredasServices, causasServices, modalFactory, modalResultados, modalInfoUsuario, CONSTANTES){	
    	
    	$scope.init = function(){
    		if(!$scope.causas){
    			$scope.consultarCausasNoLectura();
    		}
    		$scope.estaEditando = false;
    		$scope.existeUsuario = false;
    		$scope.usuario = {};
    		$scope.buscarCedula = null;
    		$scope.filtro = {};
    	}
    	
    	$scope.consultarCausasNoLectura = function(){
    		causasServices
    		.consultar()
    		.then(function(causas){
    			$scope.causas = causas;
    		});
    	}
    	
    	$scope.onBuscarPropietario = function(){
    		if($scope.validar()){
    			if($scope.buscarCedula){
    				$scope.bucarPorCedula();
    			}else{
    				$scope.bucarPorNombre();
    			}
    			$scope.buscarCedula = null;
        		$scope.filtro = null;
    		}
    	}
    	
    	$scope.bucarPorNombre = function(){
    		usuariosServices
    		.buscarPorNombre($scope.filtro)
    		.then(function(usuarios){
    			if(usuarios.length==0){
    				modalFactory.abrir(CONSTANTES.ESTADO.ERROR, CONSTANTES.USUARIO.ERR_USUARIO_NO_EXISTE);
    				$scope.init();
    			}else{
    				modalResultados
    				.abrir(usuarios)
    				.result
    	    		.then(function(usuario){
    	    			if(null!=usuario){
    	    				$scope.usuario = usuario;
    	    				$scope.existeUsuario = true;
    	    			}
    	    		});
    			}
    		});
    	}
    	
    	$scope.bucarPorCedula = function(){
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
    	}
    	
    	$scope.validar = function(){
    		if($scope.buscarCedula || $scope.filtro.nombres || $scope.filtro.apellidos){
    			return true;
    		}
    		modalFactory.abrir(CONSTANTES.ESTADO.ERROR, CONSTANTES.USUARIO.ERR_CEDULA_OBLIGATORIO);
			return false;
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
    	
    	$scope.onInfo = function(){
    		usuariosServices
    		.buscarInfo($scope.usuario.cedula)
    		.then(function(resultado){
    			angular.forEach(resultado.instalaciones, function(instalacion, instalacionKey) {
    				angular.forEach(instalacion.consumos, function(consumo, key){
    					consumo.causa = $filter('filter')($scope.causas, {codigo : consumo.codigoCausaNoLectura}, true)[0];
    				});
      			});
    			modalInfoUsuario.abrir(resultado);
    		});
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