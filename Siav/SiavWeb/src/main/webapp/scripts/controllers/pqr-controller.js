/*global define*/
'use strict';

define(['siav-module', 'pqr-services', 'instalaciones-services', 'modal-factory'], function (app) {
	
    return app.controller('pqr-controller', ['$scope','$filter', 'pqrServices', 'instalacionesServices', 'modalFactory', function($scope, $filter, pqrServices, instalacionesServices, modalFactory){
    	
    	$scope.init = function(){
    		$scope.pqrs = [];
    		$scope.pqrNuevo = {};
    		$scope.esNuevo = false;
    		$scope.itemsPerPage = 10;
            $scope.currentPage = 1;
    		$scope.cargarEstados();
    		$scope.consultar();
    	}
    	
    	$scope.cargarEstados = function(){
    		instalacionesServices
    		.consultarMaestro('PQR_ESTADO')
    		.then(function(estados){
    			$scope.estados =  estados;
    			$scope.estadoFiltro = $filter('filter')($scope.estados, { codigo : '1' }, true)[0];
    		});
    	}
    	
    	$scope.consultar = function(){
    		pqrServices
    		.consultar()
    		.then(function(pqrs){
    			$scope.pqrs = pqrs;
    		});
    	}
    	
    	$scope.onAgregar = function(){
    		$scope.esNuevo = true;
    		$scope.pqrNuevo.instalacion = {};
    		$scope.pqrNuevo.usuario = {};
    	}
    	
    	$scope.onCrear = function(){
    		$scope.pqrNuevo.usuario.nombreUsuario = getData("user");
    		pqrServices
    		.crear($scope.pqrNuevo)
    		.then(function(response){
    			modalFactory.abrirDialogo(respuesta);
    			$scope.init();
    		});
    	}
    	
    	$scope.onCancelar = function(){
    		$scope.init();
    	}
    	
        $scope.setPage = function (pageNo) {
          $scope.currentPage = pageNo;
        };
    	
    	$scope.init();
    	
    }])

});