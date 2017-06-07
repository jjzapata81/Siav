/*global define*/
'use strict';

define(['siav-module', 'siav-services', 'usuarios-services', 'instalaciones-services', 'modal-factory', 'constantes'], function (app) {
	
    return app.controller('estructura-acueducto-controller', ['$scope', '$filter', 'siavServices', 'usuariosServices', 'instalacionesServices', 'modalFactory', 'CONSTANTES', function($scope, $filter, siavServices, usuariosServices, instalacionesServices, modalFactory, CONSTANTES){
    	
    	$scope.init = function(){
    		$scope.esNuevo = false;
    		$scope.estaEditando = false;
    		$scope.existeUsuario = false;
    		$scope.buscarCedula = null;
    		$scope.deshabilitar = true;
    		$scope.valoresIniciales();
    	}
    	
    	$scope.valoresIniciales = function(){
    		siavServices
    		.consultarEmpresa()
    		.then(function(empresa){
    			$scope.nombreAcueducto = empresa.nombreCorto;
    		});
    		instalacionesServices
    		.consultarMaestro('CODIGO_CARGO')
    		.then(function(cargos){
    			$scope.cargos =  cargos;
    			$scope.consultarJunta();
    		});
    	}
    	
    	$scope.consultarJunta = function(){
    		siavServices
    		.consultarEstructura()
    		.then(function(junta){
    			$scope.junta = junta;
    			angular.forEach($scope.junta, function(value, key) {
  				  value.cargo = $filter('filter')($scope.cargos, { codigo : value.cargo })[0];
    			});
    		});
    	}
    	
    	$scope.onActualizar = function(){
    		if($scope.deshabilitar){
    			modalFactory.abrir(CONSTANTES.ESTADO.INFO, CONSTANTES.ESTRUCTURA.INFO_MODIFICAR_ESTADO)
    			.result
    			.then(function(resultado) {
        			if(resultado){
        				$scope.continuarActualizar();
        			}
    			});
    		}else{
    			$scope.continuarActualizar();
    		}
    	}
    	
    	$scope.continuarActualizar = function(){
    		if($scope.validarActualizar()){
    			var request = {};
    			request.id = {};
    			request.id.cargo = $scope.usuarioTemp.cargo.codigo;
    			request.acta = $scope.usuarioEditar.acta;
    			request.id.cedula = $scope.usuarioEditar.cedula;
    			request.id.empresa = $scope.usuarioEditar.empresa;
    			request.id.fecha = $scope.usuarioEditar.fecha;
    			request.activo = !$scope.deshabilitar;
    			request.nuevoCargo = $scope.usuarioEditar.cargo.codigo;
    			siavServices
        		.actualizarJunta(request)
        		.then(function(response){
        			modalFactory.abrirDialogo(response);
        			$scope.init();
        		});
    		}
    	}
    	
    	$scope.onAgregar = function(){
    		$scope.esNuevo = true;
    	}
    	
    	$scope.onEditar = function(usuario){
    		$scope.estaEditando = true;
    		$scope.usuarioEditar = angular.copy(usuario);
    		$scope.usuarioTemp = angular.copy(usuario);
    	}
    	
    	$scope.onCancelar = function(){
    		$scope.init();
    	}
    	
    	$scope.onDeshabilitar = function(){
    		$scope.deshabilitar = !$scope.deshabilitar;
    	}
    	
    	$scope.onGuardar = function(){
    		if($scope.validarGuardar()){
    			var request = {};
    			request.id = {};
    			request.id.cargo = $scope.usuarioNuevo.cargo.codigo;
    			request.acta = $scope.usuarioNuevo.acta;
    			request.id.cedula = $scope.usuarioNuevo.cedula;
    			siavServices
        		.agregarJunta(request)
        		.then(function(response){
        			modalFactory.abrirDialogo(response);
        			$scope.init();
        		});
    		}
    		
    	}
    	
    	$scope.validarGuardar = function(){
    		if(!$scope.usuarioNuevo){
    			modalFactory.abrir(CONSTANTES.ESTADO.ERROR, CONSTANTES.ESTRUCTURA.ERR_USUARIO);
    			return false;
    		}
    		if(!$scope.usuarioNuevo.cargo){
    			modalFactory.abrir(CONSTANTES.ESTADO.ERROR, CONSTANTES.ESTRUCTURA.ERR_CARGO);
    			return false;
    		}
    		if(!$scope.usuarioNuevo.acta){
    			modalFactory.abrir(CONSTANTES.ESTADO.ERROR, CONSTANTES.ESTRUCTURA.ERR_ACTA);
    			return false;
    		}
    		return true;
    	}
    	
    	$scope.validarActualizar = function(){
    		if(!$scope.usuarioEditar.cargo){
    			modalFactory.abrir(CONSTANTES.ESTADO.ERROR, CONSTANTES.ESTRUCTURA.ERR_CARGO);
    			return false;
    		}
    		if(!$scope.usuarioEditar.acta){
    			modalFactory.abrir(CONSTANTES.ESTADO.ERROR, CONSTANTES.ESTRUCTURA.ERR_ACTA);
    			return false;
    		}
    		return true;
    	}
    	
    	$scope.onBuscar = function(){
    		if($scope.buscarCedula){
    			usuariosServices
        		.buscar($scope.buscarCedula)
        		.then(function(usuario){
        			if(usuario.mensaje){
        				modalFactory.abrir(usuario.estado, usuario.mensaje);
        				$scope.init();
        			}else{
        				$scope.usuarioNuevo = usuario;
        				$scope.existeUsuario = true;
        			}
        		});
    		}else{
    			modalFactory.abrir(CONSTANTES.ESTADO.ERROR, CONSTANTES.USUARIO.ERR_CEDULA_OBLIGATORIO);
    		}
    	}
	
    	$scope.init();

    }])

});