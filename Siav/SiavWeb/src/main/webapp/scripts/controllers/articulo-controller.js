/*global define*/
'use strict';

define(['siav-module', 'articulo-services', 'instalaciones-services', 'modal-factory', 'constantes'], function (app) {
	
    return app.controller('articulo-controller', ['$scope', '$filter', 'articuloServices', 'instalacionesServices', 'modalFactory', 'CONSTANTES', function($scope, $filter, articuloServices, instalacionesServices, modalFactory, CONSTANTES){
    	
    	$scope.init = function(){
    		$scope.articuloNuevo = {};
    		$scope.articuloNuevo.tieneIva = true;
    		$scope.articuloNuevo.porcentajeIva = 19;
    		$scope.filtro = {};
    		$scope.filtro.activo = true;
    		$scope.esNuevo = false;
    		$scope.estaEditando = false;
    		$scope.articulos = [];
    		$scope.consultarArticulos();
    		$scope.consultarNombresArticulos();
    		
    	}
    	
    	$scope.consultarArticulos = function(){
    		articuloServices
    		.consultar()
    		.then(function(articulos){
    			$scope.articulos = articulos;
    			angular.forEach($scope.articulos, function(articulo, key) {
    				articulo.porcentajeIva = articulo.porcentajeIva * 100;
    			});
    			$scope.cargarTiposUnidad();
    		});
    	}
    	$scope.consultarNombresArticulos = function(){
    		articuloServices
    		.consultarNombres()
    		.then(function(nombresArticulo){
    			$scope.nombresArticulo = nombresArticulo;
    		});
    	}
    	
    	$scope.cargarTiposUnidad = function(){
    		instalacionesServices
    		.consultarMaestro(CONSTANTES.MAESTROS.UNIDAD_ARTICULO)
    		.then(function(unidades){
    			$scope.unidades = unidades;
    			angular.forEach($scope.articulos, function(articulo, key) {
    				var filtro = $filter('filter')($scope.unidades, { codigo : articulo.unidad })[0];
    				articulo.unidad = filtro;
    			});
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
    			$scope.articuloNuevo.porcentajeIva = $scope.articuloNuevo.tieneIva ? $scope.articuloNuevo.porcentajeIva : 0;
    			articuloServices
        		.actualizar($scope.getRequest($scope.articuloNuevo))
        		.then(function(respuesta){
        			modalFactory.abrirDialogo(respuesta);
        			$scope.init();
        		});
    		}
    	}
    	
    	$scope.onCrear = function(){
    		if($scope.validar()){
    			$scope.articuloNuevo.porcentajeIva = $scope.articuloNuevo.tieneIva ? $scope.articuloNuevo.porcentajeIva : 0;
    			articuloServices
        		.crear($scope.getRequest($scope.articuloNuevo))
        		.then(function(respuesta){
        			modalFactory.abrirDialogo(respuesta);
        			$scope.init();
        		});
    		}
    	}
    	
    	$scope.onDesactivar = function(articulo){
    		modalFactory
    		.abrir(CONSTANTES.ESTADO.INFO, CONSTANTES.ARTICULO.INFO_MODIFICAR)
    		.result
    		.then(function(respuesta){
    			articulo.activo = !articulo.activo;
    			articuloServices
        		.actualizar($scope.getRequest(articulo))
        		.then(function(respuesta){
        			modalFactory.abrirDialogo(respuesta);
        			$scope.init();
        		});
    		});
    	}
    	
    	
    	
    	$scope.getRequest = function(articulo){
    		return {
	    			codigo:articulo.codigo,
	    			codigoContable:articulo.codigoContable,
	    			nombre:articulo.nombre,
	    			unidad:articulo.unidad,
	    			observacion:articulo.observacion,
	    			porcentajeIva:articulo.porcentajeIva,
	    			tieneIva:articulo.tieneIva,
	    			activo:articulo.activo
    			}; 
    	}
    	
    	$scope.onEditar = function(articulo){
    		$scope.estaEditando = true;
    		$scope.articuloNuevo = angular.copy(articulo);
    	}
    	
    	$scope.validar = function(){
    		if(!$scope.articuloNuevo.nombre){
    			modalFactory.abrir(CONSTANTES.ESTADO.ERROR, CONSTANTES.ARTICULO.ERR_NOMBRE);
    			return false;
    		}
    		if(!$scope.articuloNuevo.codigo){
    			modalFactory.abrir(CONSTANTES.ESTADO.ERROR, CONSTANTES.ARTICULO.ERR_CODIGO);
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