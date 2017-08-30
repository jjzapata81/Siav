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
    		$scope.detalle = null;
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
    		if($scope.validarEncabezado()){
    			$scope.nuevaFactura = false;
        		$scope.mostrarTotal = true;
        		$scope.entrada.detalles.push(angular.copy($scope.detalle));
        		$scope.totalFactura = $scope.totalFactura + $scope.detalle.valorConIva;
        		$scope.articulos.splice($scope.articulos.indexOf($scope.detalle.articulo), 1);
        		$scope.detalle = null;
        		
    		}
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
    	
    	$scope.onCalcularPrecio = function(){
    		if(!$scope.detalle.articulo){
    			$scope.detalle.articulo = {};
    		}
    		$scope.detalle.precio = $scope.detalle.precioUnitario * $scope.detalle.cantidad;
    		$scope.detalle.valorIva = $scope.incluyeIva ? 0 :$scope.detalle.articulo.porcentajeIva * $scope.detalle.precio;
    		$scope.detalle.valorConIva = $scope.detalle.valorIva + $scope.detalle.precio;
    	}
    	
    	$scope.camposValidos = function(){
    		return true;
    	}
    	
    	$scope.validarEncabezado = function(){
    		if(!$scope.entrada.proveedor){
    			modalFactory.abrir(CONSTANTES.ESTADO.ERROR, CONSTANTES.ENTRADA.ERR_PROVEEDOR);
    			return false;
    		}
    		if(!$scope.entrada.factura){
    			modalFactory.abrir(CONSTANTES.ESTADO.ERROR, CONSTANTES.ENTRADA.ERR_FACTURA);
    			return false;
    		}
    		if(!$scope.entrada.fecha){
    			modalFactory.abrir(CONSTANTES.ESTADO.ERROR, CONSTANTES.ENTRADA.ERR_FECHA);
    			return false;
    		}
    		if(!$scope.detalle){
    			modalFactory.abrir(CONSTANTES.ESTADO.ERROR, CONSTANTES.ENTRADA.ERR_DETALLE_ARTICULO);
    			return false;
    		}
    		if(!$scope.detalle.articulo){
    			modalFactory.abrir(CONSTANTES.ESTADO.ERROR, CONSTANTES.ENTRADA.ERR_DETALLE_ARTICULO);
    			return false;
    		}
    		if(!$scope.detalle.cantidad){
    			modalFactory.abrir(CONSTANTES.ESTADO.ERROR, CONSTANTES.ENTRADA.ERR_DETALLE_CANTIDAD);
    			return false;
    		}
    		if(!$scope.detalle.precioUnitario){
    			modalFactory.abrir(CONSTANTES.ESTADO.ERROR, CONSTANTES.ENTRADA.ERR_DETALLE_PRECIO_UNITARIO);
    			return false;
    		}
    		return true;
    	}

    	$scope.init();
    }])

});