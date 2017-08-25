/*global define*/
'use strict';

define(['siav-module', 'proveedor-services', 'modal-factory', 'constantes'], function (app) {
	
    return app.controller('proveedor-controller', ['$scope', 'proveedorServices', 'modalFactory', 'CONSTANTES', function($scope, proveedorServices, modalFactory, CONSTANTES){
    	
    	$scope.init = function(){
    		$scope.proveedorNuevo = {};
    		$scope.esNuevo = false;
    		$scope.estaEditando = false;
    		$scope.proveedores = [];
    		$scope.consultarProveedores();
    		$scope.consultarNombresProveedores();
    	}
    	
    	$scope.consultarProveedores = function(){
    		proveedorServices
    		.consultar()
    		.then(function(proveedores){
    			$scope.proveedores = proveedores;
    		});
    	}
    	$scope.consultarNombresProveedores = function(){
    		proveedorServices
    		.consultarNombres()
    		.then(function(nombresProveedor){
    			$scope.nombresProveedor = nombresProveedor;
    		});
    	}
    	
    	$scope.onAgregar = function(){
    		$scope.esNuevo = true;
    	}
    	$scope.onCancelar= function(){
    		$scope.init();
    	}
    	
    	$scope.onActualizar = function(){
    		if($scope.validar()){
    			proveedorServices
        		.actualizar($scope.proveedorNuevo)
        		.then(function(respuesta){
        			modalFactory.abrirDialogo(respuesta);
        			$scope.init();
        		});
    		}
    	}
    	
    	$scope.onCrear = function(){
    		if($scope.validar()){
    			proveedorServices
        		.crear($scope.proveedorNuevo)
        		.then(function(respuesta){
        			modalFactory.abrirDialogo(respuesta);
        			$scope.init();
        		});
    		}
    	}
    	
    	$scope.onEditar = function(proveedor){
    		$scope.estaEditando = true;
    		$scope.proveedorNuevo = angular.copy(proveedor);
    	}
    	
    	$scope.validar = function(){
    		if(!$scope.proveedorNuevo.nit){
    			modalFactory.abrir(CONSTANTES.ESTADO.ERROR, CONSTANTES.PROVEEDOR.ERR_NIT);
    			return false;
    		}
    		if(!$scope.proveedorNuevo.razonSocial){
    			modalFactory.abrir(CONSTANTES.ESTADO.ERROR, CONSTANTES.PROVEEDOR.ERR_RAZON_SOCIAL);
    			return false;
    		}
    		return true;
    	}
    	
    	$scope.itemsPerPage = 10;
        $scope.currentPage = 1;

        $scope.setPage = function (pageNo) {
          $scope.currentPage = pageNo;
        };
    	
    	$scope.init();

    }])

});