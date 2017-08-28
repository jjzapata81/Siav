/*global define*/
'use strict';

define(['siav-module', 'materiales-services', 'articulo-services', 'proveedor-services', 'modal-factory', 'constantes'], function (app) {
	
    return app.controller('salida-materiales-controller', ['$scope', 'articuloServices', 'proveedorServices', 'materialesServices', 'modalFactory', 'CONSTANTES', function($scope, articuloServices, proveedorServices, materialesServices, modalFactory, CONSTANTES){
    	
    	$scope.init = function(){
    		$scope.salida = {};
    		$scope.salida.detalles = [];
    		$scope.nuevaOrden = true;
    		$scope.consultarArticulos();
    	}
    	
    	$scope.consultarArticulos = function(){
    		articuloServices
    		.consultar()
    		.then(function(articulos){
    			$scope.articulos = articulos;
    		});
    	}	   	
    	
    	$scope.onCancelar = function(){
    		if($scope.salida.detalles.length > 0){
    			modalFactory
        		.abrir(CONSTANTES.ESTADO.INFO, CONSTANTES.SALIDA.INFO_CANCELAR)
        		.result
        		.then(function(respuesta){
        			$scope.init();
        		});
    		}else{
    			$scope.init();
    		}
    		
    	}
    	
    	$scope.onAgregar = function(){
    		$scope.nuevaOrden = false;
    		$scope.salida.detalles.push(angular.copy($scope.detalle));
    		$scope.detalle = null;
    	} 
    	  	
    	$scope.onGuardar = function(){
    		if($scope.camposValidos()){
    			materialesServices
    			.crearSalida($scope.salida)
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