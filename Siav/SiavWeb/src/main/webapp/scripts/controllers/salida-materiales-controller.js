/*global define*/
'use strict';

define(['siav-module', 'materiales-services', 'articulo-services', 'proveedor-services', 'instalaciones-services', 'modal-factory', 'constantes'], function (app) {
	
    return app.controller('salida-materiales-controller', ['$scope', '$filter', 'articuloServices', 'proveedorServices', 'materialesServices', 'instalacionesServices', 'modalFactory', 'CONSTANTES', function($scope, $filter, articuloServices, proveedorServices, materialesServices, instalacionesServices, modalFactory, CONSTANTES){
    	
    	$scope.init = function(){
    		$scope.detalle = null;
    		$scope.salida = {};
    		$scope.salida.detalles = [];
    		$scope.nuevaOrden = true;
    		$scope.mostrarInstalacion = true;
    		$scope.consultarArticulos();
    		$scope.consultarDestinos();
    	}
    	
    	$scope.consultarArticulos = function(){
    		articuloServices
    		.consultarSalidas()
    		.then(function(articulos){
    			$scope.articulos = articulos;
    		});
    	}
    	
    	$scope.consultarDestinos = function(){
    		instalacionesServices
    		.consultarMaestro(CONSTANTES.MAESTROS.SALIDA_DESTINO)
    		.then(function(destinos){
    			$scope.destinos = destinos;
    			$scope.destinoSeleccionado = $filter('filter')($scope.destinos, { codigo : 1 })[0];
    		});
    	}
    	
    	$scope.calcularPrecio = function(){
    		$scope.detalle.precio = $scope.detalle.articulo.precioComercial * $scope.detalle.cantidad;
    	}
    	
    	$scope.cambiarDestino = function(){
    		if($scope.destinoSeleccionado.valor === CONSTANTES.SALIDA.DEST_INSTALACION){
    			$scope.mostrarInstalacion = true;
    		}else{
    			$scope.mostrarInstalacion = false;
    			$scope.salida.instalacion = null;
    		}
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
    		if($scope.validar()){
    			$scope.nuevaOrden = false;
    			delete($scope.detalle.articulo.cantidadDisponible);
    			delete($scope.detalle.articulo.precioUnitario);
    			delete($scope.detalle.articulo.precioComercial);
        		$scope.salida.detalles.push(angular.copy($scope.detalle));
        		$scope.articulos.splice($scope.articulos.indexOf($scope.detalle.articulo), 1);
        		$scope.detalle = null;
    		}
    	} 
    	  	
    	$scope.onGuardar = function(){
    		materialesServices
			.crearSalida($scope.salida)
			.then(function(respuesta){
				modalFactory.abrirDialogo(respuesta);
				$scope.init();
			});
    	}
    	
    	$scope.validar = function(){
    		if($scope.mostrarInstalacion && !$scope.salida.instalacion){
    			modalFactory.abrir(CONSTANTES.ESTADO.ERROR, CONSTANTES.SALIDA.ERR_INSTALACION);
    			return false;
    		}
    		if(!$scope.salida.fecha){
    			modalFactory.abrir(CONSTANTES.ESTADO.ERROR, CONSTANTES.SALIDA.ERR_FECHA);
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
    		if(!$scope.detalle.articulo.precioComercial){
    			modalFactory.abrir(CONSTANTES.ESTADO.ERROR, CONSTANTES.ENTRADA.ERR_DETALLE_PRECIO_UNITARIO);
    			return false;
    		}
    		if($scope.detalle.articulo.precioComercial < ($scope.detalle.articulo.precioUnitario + $scope.detalle.articulo.ivaUnitario)){
    			modalFactory.abrir(CONSTANTES.ESTADO.ERROR, CONSTANTES.SALIDA.ERR_PRECIO_MENOR);
    			return false;
    		}
    		if($scope.detalle.cantidad > $scope.detalle.articulo.cantidadDisponible){
    			modalFactory.abrir(CONSTANTES.ESTADO.ERROR, CONSTANTES.SALIDA.ERR_CANTIDAD_MAYOR);
    			return false;
    		}
    		return true;
    	}

    	$scope.init();
	

    }])

});