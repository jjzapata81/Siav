/*global define*/
'use strict';

define(['siav-module', 'asignacion-recorridos-services', 'usuario-sistema-services', 'constantes', 'modal-factory'], function (app) {
	
    return app.controller('asignacion-recorridos-controller', ['$scope', '$filter', 'asignacionServices', 'usuarioSistemaServices', 'modalFactory', 'CONSTANTES', function($scope, $filter, asignacionServices, usuarioSistemaServices, modalFactory, CONSTANTES){
    	
    	$scope.init = function(){
    		$scope.asignaciones = [];
			$scope.usuarios = [];
			$scope.estaEditando = false;
			$scope.asignacionEditar ={};
    		$scope.consultarUsuarios();
    	}
    	
    	$scope.consultarUsuarios = function(){
    		usuarioSistemaServices
    		.consultaPerfil(CONSTANTES.RUTA.COD_OPERADOR)
    		.then(function(usuarios){
    			$scope.usuarios = usuarios;
    			console.log(usuarios);
    			$scope.consultarAsignaciones();
    		});
    	}
    	
    	$scope.consultarAsignaciones = function(){
    		asignacionServices
    		.consultar()
    		.then(function(asignaciones){
    			$scope.asignaciones = asignaciones;
    			angular.forEach($scope.asignaciones, function(asignacion, asignacionKey) {
    				asignacion.usuario = $filter('filter')($scope.usuarios, {nombreUsuario : asignacion.usuarioRamalPK.usuario }, true)[0];
    			});
    			console.log($scope.asignaciones);
    		});
    	}
    	
    	$scope.onCancelar = function(){
    		$scope.init();
		}
		
		$scope.onEditar = function(asignacion){
			$scope.estaEditando = false;
			
		}
    	
    	$scope.onGuardar = function(){
    		if($scope.validar()){
    			var request = angular.copy($scope.asignacionEditar);
    			request.instalacionAnterior = $scope.instalacionAnterior;
    			request.ramal = $scope.instalacionCorregir.ramal.codigoRamal;
    			asignacionServices
        		.actualizar(request)
        		.then(function(respuesta){
        			modalFactory.abrirDialogo(respuesta);
        			$scope.init();
        		});
    		}
    	}
    	
    	
    	$scope.validar = function(){
    		/**if($scope.mostrarInstalacion && !$scope.instalacionAnterior){
    			modalFactory.abrir(CONSTANTES.ESTADO.ERROR, CONSTANTES.CONFIGURACION_RUTA.ERR_INSTALACION_OBLIGATORIO);
    			return false;
    		}
    		if($scope.ramalTemp && $scope.ramalTemp != $scope.instalacionCorregir.ramal.codigoRamal && !$scope.instalacionCorregir.cambiarOrden){
    			modalFactory.abrir(CONSTANTES.ESTADO.ERROR, CONSTANTES.CONFIGURACION_RUTA.ERR_CAMBIO_RAMAL);
    			return false;
    		}**/
    		return true;
    	}
    	
    	$scope.itemsPerPage = 15;
        $scope.currentPage = 1;

        $scope.setPage = function (pageNo) {
          $scope.currentPage = pageNo;
        }

    	$scope.init();
    }])

});