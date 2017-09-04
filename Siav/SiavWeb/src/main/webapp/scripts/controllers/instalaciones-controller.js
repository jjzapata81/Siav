/*global define*/
'use strict';

define(['siav-module', 'instalaciones-services', 'usuarios-services', 'veredas-services', 'ramal-services', 'modal-factory', 'modal-observacion', 'modal-usuario-factory', 'constantes'], function (app) {
	
    return app.controller('instalaciones-controller',
    		['$scope', '$location', '$filter', 'instalacionesServices', 'usuariosServices', 'veredasServices', 'ramalServices', 'modalFactory', 'modalUsuario', 'modalObservacion', 'CONSTANTES',
    		 function($scope, $location, $filter, instalacionesServices, usuariosServices, veredasServices, ramalServices, modalFactory, modalUsuario, modalObservacion, CONSTANTES){

    	$scope.estratos = [1, 2, 3, 4, 5, 6];
    			
    	$scope.init = function(){
    		$scope.limpiar();
    		$scope.puedeActivar = false;
    		veredasServices
    		.consultar()
    		.then(function(veredas){
    			$scope.veredas =  veredas;
    		});
    		ramalServices
    		.consultar()
    		.then(function(ramales){
    			$scope.ramales = ramales;
    		});
    		instalacionesServices
    		.consultarMaestro('FACTURACION')
    		.then(function(tiposFacturacion){
    			$scope.tiposFacturacion =  tiposFacturacion;
    		});
    	}
    		
    	$scope.onActivar = function(){
    		instalacionesServices
    		.activar($scope.instalacion.numeroInstalacion)
    		.then(function(respuesta){
    			modalFactory.abrirDialogo(respuesta);
    			$scope.init();
    		});
    	}
    	
    	$scope.onBuscar = function(){
    		if($scope.buscarInstalacion){
    			instalacionesServices
        		.buscarInstalacion($scope.buscarInstalacion)
        		.then(function(respuesta){
        			if(respuesta.mensaje){
        				modalFactory.abrir(respuesta.estado, respuesta.mensaje);
        				$scope.limpiar();
        			}else{
        				var ramal = $filter('filter')($scope.ramales, { codigoRamal : respuesta.instalacion.ramal })[0];
        				var tipoFacturacion = $filter('filter')($scope.tiposFacturacion, { codigo : respuesta.instalacion.facturacion })[0];
        				$scope.instalacion = respuesta.instalacion;
        				$scope.instalacion.ramal = ramal;
        				$scope.instalacion.facturacion = tipoFacturacion;
        				$scope.existeInstalacion = true;
        				$scope.puedeActivar = respuesta.activar;
        				instalacionesServices
    					.consultaVencido($scope.instalacion.numeroInstalacion)
    					.then(function(respuesta){
    						if(respuesta.estado === CONSTANTES.ESTADO.INFO){
    							modalFactory.abrir(respuesta.estado, respuesta.mensaje);
    							$scope.autorizado = respuesta.autorizado;
    						}
    					});
        			}
        		});
    		}else{
    			modalFactory.abrir(CONSTANTES.ESTADO.ERROR, CONSTANTES.INSTALACION.ERR_BUSQUEDA_OBLIGATORIO);
    		}
    	}
    	
    	$scope.buscarPorCedula = function(cedula){
    		usuariosServices
    		.buscar(cedula)
    		.then(function(usuario){
    			if(usuario.mensaje){
    				modalFactory.abrir(usuario.estado, usuario.mensaje);
    			}else{
    				$scope.instalacion.usuario = usuario;
    				$scope.existeInstalacion = true;
    			}
    		});
    	}
    	
    	$scope.onCrear = function(){
    		$scope.limpiar();
    		$scope.estaEditando = true;
    		$scope.esNueva = true;
    		$scope.accionPropietario = CONSTANTES.ACCION.AGREGAR_PROPIETARIO;
    		$scope.accion = CONSTANTES.ACCION.CREAR;
    	}
    	
    	$scope.onEditar = function(){
    		$scope.instalacionTemp = angular.copy($scope.instalacion);
    		$scope.accionPropietario = CONSTANTES.ACCION.MODIFICAR_PROPIETARIO;
    		$scope.accion = CONSTANTES.ACCION.EDITAR;
    		$scope.estaEditando = true;
    	}
    	
    	$scope.onDesactivar = function(){
    		modalObservacion
			.abrir()
    		.result
    		.then(function(observacion){
    			var request = {};
        		request.usuario = getData("user");
        		request.observacion = observacion;
        		request.instalacion = $scope.instalacion.numeroInstalacion;
        		instalacionesServices
        		.desactivar(request)
        		.then(function(respuesta){
        			modalFactory.abrirDialogo(respuesta);
        			$scope.init();
        		});
    		});
    		
    	}
    	
    	$scope.onGuardar = function(){
    		if($scope.camposValidos()){
    			delete($scope.instalacion.mensaje);
    			delete($scope.instalacion.estado);
    			delete($scope.instalacion.usuario.nombreCompleto);
    			$scope.instalacion.ramal = $scope.instalacion.ramal.codigoRamal;
    			$scope.instalacion.facturacion = $scope.instalacion.facturacion.codigo;
    			if($scope.accion === CONSTANTES.ACCION.CREAR){
    				$scope.continuarCrear();
    			}else{
    				$scope.continuarGuardar();
    			}
    			
    		}
    	}
    	
    	$scope.continuarGuardar = function(){
    		instalacionesServices
    		.guardar($scope.instalacion)
    		.then(function(respuesta){
    			modalFactory.abrirDialogo(respuesta);
    			$scope.limpiar();
    		});
    	}
    	
    	$scope.continuarCrear = function(){
    		instalacionesServices
    		.crear($scope.instalacion)
    		.then(function(respuesta){
    			modalFactory.abrirDialogo(respuesta);
    			$scope.limpiar();
    		});
    	}
    	
    	$scope.limpiar = function (){
    		$scope.esNueva = false;
    		$scope.autorizado = true;
    		$scope.instalacion = {};
			$scope.instalacion.usuario = null;
			$scope.estaEditando = false;
    		$scope.existeInstalacion = false;
    		$scope.buscarInstalacion = null;
    	}
    	
    	$scope.onEditarPropietario = function(){
    		modalUsuario
    		.abrir()
    		.result
    		.then(function(cedula){
    			if(null!=cedula){
    				$scope.buscarPorCedula(cedula);
    			}
    		});
    	}
    	
    	$scope.camposValidos = function(){
    		if(!$scope.instalacion.vereda || !$scope.instalacion.direccion || !$scope.instalacion.digitosMedidor || !$scope.instalacion.facturacion){
    			modalFactory.abrir(CONSTANTES.ESTADO.ERROR, CONSTANTES.ERR.OBLIGATORIO);
    			return false;
    		}
    		if(!$scope.instalacion.usuario){
    			modalFactory.abrir(CONSTANTES.ESTADO.ERROR, "Debe agregar un usuario a la instalación.");
    			return false;
    		}
    		if($scope.instalacion.digitosMedidor < 1){
    			modalFactory.abrir(CONSTANTES.ESTADO.ERROR, "El número de dígitos del medidor debe ser mayor a cero (0).");
    			return false;
    		}
    		return true;
    	}
    	
    	$scope.onChangeDigitos = function(){
    		$scope.instalacion.digitosMedidor = $scope.instalacion.digitosMedidor < 0 ? 1 : $scope.instalacion.digitosMedidor;
    	}
    	
    	$scope.onCancelarEditar = function(){
    		$scope.instalacion = $scope.instalacionTemp;
    		$scope.estaEditando = false;
    	}
    	
    	$scope.onCancelar = function(){
    		$scope.limpiar();
    	}
    	
    	$scope.init();
    	
    }])

});