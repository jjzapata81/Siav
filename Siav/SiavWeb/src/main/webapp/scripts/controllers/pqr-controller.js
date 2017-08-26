/*global define*/
'use strict';

define(['siav-module', 'pqr-services', 'instalaciones-services', 'usuario-sistema-services', 'modal-factory'], function (app) {
	
    return app.controller('pqr-controller', ['$scope','$filter', 'pqrServices', 'instalacionesServices', 'usuarioSistemaServices', 'modalFactory', function($scope, $filter, pqrServices, instalacionesServices, usuarioSistemaServices, modalFactory){
    	
    	$scope.init = function(){
    		$scope.pqrs = [];
    		$scope.pqrNuevo = {};
    		$scope.esNuevo = false;
    		$scope.mostrarDetalle = false;
    		$scope.cerrado = false;
    		$scope.itemsPerPage = 10;
            $scope.currentPage = 1;
    		$scope.consultar();
    		$scope.filtro = {};
    	}
    	
    	$scope.cargarListas = function(){
    		$scope.cargarEstados();
    		$scope.cargarUsuarios();
    	}
    	
    	$scope.cargarUsuarios = function(){
    		usuarioSistemaServices
    		.consultar()
    		.then(function(usuarios){
    			$scope.usuarios =  usuarios;
    		});
    	}
    	
    	$scope.cargarEstados = function(){
    		instalacionesServices
    		.consultarMaestro(CONSTANTES.MAESTROS.PQR_ESTADO)
    		.then(function(estados){
    			$scope.estados =  estados;
    			$scope.estado = $filter('filter')($scope.estados, { codigo : '1' }, true)[0];
    		});
    	}
    	
    	$scope.consultar = function(){
    		pqrServices
    		.consultar()
    		.then(function(pqrs){
    			$scope.pqrs = pqrs;
    		});
    	}
    	
    	$scope.onConsultarDetalle = function(pqr){
    		$scope.pqrEditar = pqr;
    		$scope.cerrado = "CERRADO" === $scope.pqrEditar.estado;
    		var request = {};
    		request.user = getData("user");
    		request.idPqr = pqr.id;
    		pqrServices
    		.consultarDetalle(request)
    		.then(function(response){
    			$scope.detalles = response.detalles;
    			$scope.mostrarDetalle = true;
    			$scope.puedeEditar = response.puedeEditar;
    		});
    	}
    	
    	$scope.onAgregar = function(){
    		$scope.esNuevo = true;
    	}
    	
    	$scope.onActualizar = function(){
    		delete($scope.pqrEditar.usuarioAsignar.nombreCompleto);
    		delete($scope.pqrEditar.nombreCompleto);
    		delete($scope.pqrEditar.fechaInicio);
    		delete($scope.pqrEditar.fechaFin);
    		delete($scope.pqrEditar.usuarioAsignado);
    		$scope.pqrEditar.estado = $scope.pqrEditar.estado.codigo;
    		pqrServices
    		.actualizar($scope.pqrEditar)
    		.then(function(response){
    			modalFactory.abrirDialogo(response);
    			$scope.init();
    		});
    	}
    	
    	$scope.onCrear = function(){
    		$scope.pqrNuevo.nombreUsuario = getData("user");
    		if($scope.pqrNuevo.usuarioAsignar){
    			delete($scope.pqrNuevo.usuarioAsignar.nombreCompleto);
    		}
    		pqrServices
    		.crear($scope.pqrNuevo)
    		.then(function(response){
    			modalFactory.abrirDialogo(response);
    			$scope.init();
    		});
    	}
    	
    	$scope.onCancelar = function(){
    		$scope.init();
    	}
    	
        $scope.setPage = function (pageNo) {
          $scope.currentPage = pageNo;
        };
    	
        $scope.cargarListas();
        
    	$scope.init();
    	
    }])

});