/*global define*/
'use strict';

define(['siav-module', 'causas-no-lectura-services', 'modal-factory', 'constantes'], function (app) {
	
    return app.controller('causas-no-lectura-controller', ['$scope', 'causasServices', 'modalFactory', 'CONSTANTES', function($scope, causasServices, modalFactory, CONSTANTES){
    	
    	$scope.init = function(){
    		$scope.esNuevo = false;
    		$scope.causaNueva = {};
    		$scope.cargarCausas();
    		$scope.cargarDescripciones();
    	}
    	
    	$scope.cargarCausas = function(){
    		causasServices
    		.consultar()
    		.then(function(causas){
    			$scope.causas = causas;
    		});
    	}
    	
    	$scope.cargarDescripciones = function(){
    		causasServices
    		.consultarDescripciones()
    		.then(function(descripciones){
    			$scope.descripciones = descripciones;
    		});
    	}
    	
    	$scope.onAgregar = function(){
    		$scope.esNuevo = true;
    	}
    	
    	$scope.onCancelar = function(){
    		$scope.esNuevo = false;
    		$scope.causaNueva = {};
    	}
    	
    	$scope.onGuardar = function(){
    		if($scope.causaNueva.descripcion){
    			causasServices
    			.crear($scope.causaNueva)
    			.then(function(respuesta){
    				modalFactory.abrirDialogo(respuesta);
    				$scope.init();
    			});
    			
    		}else{
    			modalFactory.abrir(CONSTANTES.ESTADO.ERROR, CONSTANTES.CAUSAS.ERR_OBLIGATORIO);
    		}
    	}
    	
    	$scope.onImprimir = function(){
    		window.print();
    	}
    	
    	$scope.init();

    }])

});