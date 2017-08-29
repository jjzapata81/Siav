/*global define*/
'use strict';

define(['siav-module', 'materiales-services', 'articulo-services', 'proveedor-services', 'modal-factory', 'constantes'], function (app) {
	
    return app.controller('entradas-materiales-controller', ['$scope', 'articuloServices', 'proveedorServices', 'materialesServices', 'modalFactory', 'CONSTANTES', function($scope, articuloServices, proveedorServices, materialesServices, modalFactory, CONSTANTES){
    	
    	$scope.init = function(){
    		$scope.entrada = {};
    		$scope.incluyeIva = false;
    		$scope.mostrarTotal = false;
    		$scope.totalFactura = 0;
    		$scope.entrada.detalles = [];
    		$scope.nuevaFactura = true;
    		$scope.consultarArticulos();
    		$scope.consultarProveedores();
    	}
    	
    	$scope.consultarArticulos = function(){
    		articuloServices
    		.consultar()
    		.then(function(articulos){
    			$scope.articulos = articulos;
    		});
    	}
    	
    	$scope.consultarProveedores = function(){
    		proveedorServices
    		.consultar()
    		.then(function(proveedores){
    			$scope.proveedores = proveedores;
    		});
    	} 	   	
    	
    	$scope.onCancelar = function(){
    		if($scope.entrada.detalles.length > 0){
    			modalFactory
        		.abrir(CONSTANTES.ESTADO.INFO, CONSTANTES.ENTRADA.INFO_CANCELAR)
        		.result
        		.then(function(respuesta){
        			$scope.init();
        		});
    		}else{
    			$scope.init();
    		}
    		
    	}
    	
    	$scope.onAgregar = function(){
    		$scope.nuevaFactura = false;
    		$scope.mostrarTotal = true;
    		$scope.entrada.detalles.push(angular.copy($scope.detalle));
    		$scope.totalFactura = $scope.totalFactura + $scope.detalle.precio;
    		$scope.detalle = null;
    	} 
    	  	
    	$scope.onGuardar = function(){
    		if($scope.camposValidos()){
    			materialesServices
    			.crearEntrada($scope.entrada)
    			.then(function(respuesta){
    				modalFactory.abrirDialogo(respuesta);
    				$scope.init();
    			});
    		}
    	}
    	
    	$scope.camposValidos = function(){
    		return true;
    	}

    	$scope.init();
	

    }])

});