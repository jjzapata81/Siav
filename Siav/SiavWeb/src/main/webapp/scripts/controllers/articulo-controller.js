/*global define*/
'use strict';

define(['siav-module', 'articulo-services', 'instalaciones-services', 'modal-factory', 'constantes'], function (app) {
	
    return app.controller('articulo-controller', ['$scope', '$filter', 'articuloServices', 'instalacionesServices', 'modalFactory', 'CONSTANTES', function($scope, $filter, articuloServices, instalacionesServices, modalFactory, CONSTANTES){
    	
    	$scope.init = function(){
    		$scope.articuloNuevo = {};
    		$scope.filtro = {};
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
    		$scope.articuloNuevo.tieneIva = false;
    		$scope.esNuevo = true;
    	}
    	$scope.onCancelar= function(){
    		$scope.init();
    	}
    	
    	$scope.onActualizar = function(){
    		if($scope.validar()){
    			articuloServices
        		.actualizar($scope.articuloNuevo)
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
        		.actualizar(articulo)
        		.then(function(respuesta){
        			modalFactory.abrirDialogo(respuesta);
        			$scope.init();
        		});
    		});
    	}
    	
    	$scope.onCrear = function(){
    		if($scope.validar()){
    			articuloServices
        		.crear($scope.articuloNuevo)
        		.then(function(respuesta){
        			modalFactory.abrirDialogo(respuesta);
        			$scope.init();
        		});
    		}
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