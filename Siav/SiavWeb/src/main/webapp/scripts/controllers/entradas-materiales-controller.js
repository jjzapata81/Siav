/*global define*/
'use strict';

define(['siav-module', 'materiales-services', 'articulo-services', 'proveedor-services', 'modal-factory', 'modal-entrada-factory', 'constantes'], function (app) {
	
    return app.controller('entradas-materiales-controller', ['$scope', 'articuloServices', 'proveedorServices', 'materialesServices', 'modalFactory', 'modalEntrada', 'CONSTANTES', function($scope, articuloServices, proveedorServices, materialesServices, modalFactory, modalEntrada, CONSTANTES){
    	
    	$scope.init = function(){
    		$scope.entrada = {};
    		$scope.entrada.incluyeIva = false;
    		$scope.entrada.totalFactura = 0;
    		$scope.entrada.totalIva = 0;
    		$scope.mostrarTotal = false;
    		$scope.estaEditando = false;
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
        		.then(function(){
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
        		$scope.entrada.totalFactura = $scope.entrada.totalFactura + $scope.detalle.valorConIva;
        		$scope.entrada.totalIva = $scope.entrada.totalIva + $scope.detalle.valorIva;
        		$scope.articulos.splice($scope.articulos.indexOf($scope.detalle.articulo), 1);
        		$scope.detalle = null;
    		}
    	}
    	
    	$scope.onEditarDetalle = function(detalle){
    		$scope.articulosTemp = angular.copy($scope.articulos);
    		$scope.articulosTemp.push(detalle.articulo);
    		$scope.estaEditando = true;
    		$scope.detalle = detalle;
    		$scope.detallesTemp = angular.copy($scope.entrada.detalles);
    	}
    	
    	$scope.onActualizar = function(){
    		$scope.estaEditando = false;
    		$scope.detalle = null;
    	}
    	
    	$scope.onCancelarEditar = function(){
    		$scope.estaEditando = false;
    		$scope.detalle = null;
    		$scope.entrada.detalles = $scope.detallesTemp;
    	}
    	
    	$scope.onGuardar = function(){
    		modalEntrada
			.abrir($scope.entrada)
			.result
    		.then(function(){
    			$scope.continuarGuardar();
    		});
    	}
    	
    	$scope.continuarGuardar = function(){
    		delete($scope.entrada.incluyeIva);
    		delete($scope.entrada.totalFactura);
    		delete($scope.entrada.totalIva);
    		materialesServices
			.crearEntrada($scope.entrada)
			.then(function(respuesta){
				modalFactory.abrirDialogo(respuesta);
				if(respuesta.estado!=CONSTANTES.ESTADO.ERROR){
					$scope.init();
				}
			});
    	}
    	
    	$scope.onCalcularPrecio = function(){
    		if(!$scope.detalle.articulo){
    			$scope.detalle.articulo = {};
    		}
    		$scope.detalle.precio = $scope.detalle.precioUnitario * $scope.detalle.cantidad;
    		$scope.detalle.valorIva = $scope.entrada.incluyeIva ? 0 :$scope.detalle.articulo.porcentajeIva * $scope.detalle.precio;
    		$scope.detalle.valorConIva = $scope.detalle.valorIva + $scope.detalle.precio;
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