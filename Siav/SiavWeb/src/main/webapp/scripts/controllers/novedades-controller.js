/*global define*/
'use strict';

define(['siav-module', 'instalaciones-services', 'tarifas-services', 'novedades-services', 'modal-factory', 'constantes'], function (app) {
	
    return app.controller('novedades-controller', ['$scope', '$filter', 'instalacionesServices', 'tarifasServices', 'novedadesServices', 'modalFactory', 'CONSTANTES', function($scope, $filter, instalacionesServices, tarifasServices, novedadesServices, modalFactory, CONSTANTES){
    	
    	$scope.init = function (){
    		$scope.mostrarUsuario = false;
    		$scope.numeroInstalacion = null;
    		$scope.novedad = {};
    		$scope.consultarTarifas();
    		$scope.novedades = null;
    	}
    	
    	$scope.onBuscar = function(){
    		$scope.mostrarUsuario = false;
    		if($scope.numeroInstalacion){
    			instalacionesServices
        		.buscarInstalacion($scope.numeroInstalacion)
        		.then(function(respuesta){
        			if(respuesta.mensaje){
        				modalFactory.abrir(respuesta.estado, respuesta.mensaje);
        				$scope.numeroInstalacion = null;
        			}else{
        				$scope.mostrarUsuario = true;
        				$scope.instalacion = respuesta.instalacion;
        				$scope.consultarDetalle();
        			}
        		});
    		}else{
    			modalFactory.abrir(CONSTANTES.ESTADO.ERROR, CONSTANTES.INSTALACION.ERR_BUSQUEDA_OBLIGATORIO);
    		}
    	}
    	
    	$scope.consultarDetalle = function(){
    		novedadesServices
    		.consultar($scope.instalacion.numeroInstalacion)
    		.then(function(novedades){
    			$scope.novedades = novedades;
    			angular.forEach($scope.novedades, function(novedad, key) {
    				var filtro = $filter('filter')($scope.tarifas, { codigo : novedad.id.codigoConcepto })[0];
    				novedad.descripcion = filtro.descripcion;
    			});
    		});
    	}
    	
    	$scope.onGuardar = function(){
    		if($scope.formularioValido()){
    			$scope.novedad.id = {};
    			$scope.novedad.id.codigoConcepto = $scope.tarifaNovedad.codigo;
    			$scope.novedad.id.instalacion = $scope.instalacion.numeroInstalacion;
    			novedadesServices
    			.guardar($scope.novedad)
    			.then(function(respuesta){
    				modalFactory.abrirDialogo(respuesta);
    				$scope.limpiar();
    			});
    		}
    	}
    	
    	$scope.formularioValido = function(){
    		if(!$scope.instalacion || !$scope.tarifaNovedad || !$scope.novedad.valor){
    			modalFactory.abrir(CONSTANTES.ESTADO.ERROR, CONSTANTES.ERR.OBLIGATORIO);
    			return false;
    		}
    		return true;
    	}
    	
    	$scope.onSeleccionar = function(){
    		if($scope.tarifaNovedad && "1" == $scope.tarifaNovedad.tipo){
    			$scope.esFijo = true;
    			$scope.novedad.valor = $scope.tarifaNovedad.estrato0; 
    		}else{
    			$scope.esFijo = false;
    			$scope.novedad.valor = null; 
    		}
    	}
    	
    	$scope.limpiar = function(){
    		$scope.init();
    		$scope.instalacion = null;
    	}
    	
    	$scope.onCancelar = function(){
    		$scope.init();
    	}
    	
    	$scope.consultarTarifas = function(){
    		tarifasServices
    		.consultar()
    		.then(function(tarifas){
    			$scope.tarifas = tarifas;
    		});
    	}
    	
    	$scope.init();

    }])

});