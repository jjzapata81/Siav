/*global define*/
'use strict';

define(['siav-module', 'asignacion-recorridos-services', 'usuario-sistema-services', 'constantes', 'modal-factory'], function (app) {
	
    return app.controller('asignacion-recorridos-controller', ['$scope', '$filter', 'asignacionServices', 'usuarioSistemaServices', 'modalFactory', 'CONSTANTES', function($scope, $filter, asignacionServices, usuarioSistemaServices, modalFactory, CONSTANTES){
    	
    	$scope.init = function(){
    		$scope.asignaciones = [];
			$scope.usuarios = [];
			$scope.estaEditando = false;
			$scope.asignacionEditar ={};
			$scope.asignacionTemp = {};
    		$scope.consultarUsuarios();
    	}
    	
    	$scope.consultarUsuarios = function(){
    		usuarioSistemaServices
    		.consultaPerfil(CONSTANTES.RUTA.COD_OPERADOR)
    		.then(function(usuarios){
    			$scope.usuarios = usuarios;
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
    		});
    	}
    	
    	$scope.onCancelar = function(){
    		$scope.init();
		}
		
		$scope.onEditar = function(asignacion){
			$scope.estaEditando = true;
			$scope.asignacionEditar = asignacion;
			$scope.asignacionTemp = angular.copy($scope.asignacionEditar);
		}
    	
    	$scope.onGuardar = function(){
    		var request = {};
    		request.usuarioNuevo = $scope.asignacionEditar.usuario.nombreUsuario;
    		request.usuarioActual = $scope.asignacionTemp.usuario.nombreUsuario;
    		request.fechaInicial = $scope.asignacionEditar.usuarioRamalPK.fechaInicial;
    		request.codigoRamal = $scope.asignacionEditar.usuarioRamalPK.codigoRamal;
			asignacionServices
    		.actualizar(request)
    		.then(function(respuesta){
    			modalFactory.abrirDialogo(respuesta);
    			$scope.init();
    		});
    	}
    	
    	$scope.itemsPerPage = 15;
        $scope.currentPage = 1;

        $scope.setPage = function (pageNo) {
          $scope.currentPage = pageNo;
        }

    	$scope.init();
    }])

});