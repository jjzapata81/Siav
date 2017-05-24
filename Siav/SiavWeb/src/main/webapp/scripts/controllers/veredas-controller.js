/*global define*/
'use strict';

define(['siav-module', 'veredas-services', 'modal-factory', 'constantes'], function (app) {
	
    return app.controller('veredas-controller', ['$scope', 'veredasServices', 'modalFactory', 'CONSTANTES', function($scope, veredasServices, modalFactory, CONSTANTES){
    	
    	$scope.init = function(){
    		$scope.veredaNueva = {};
    		$scope.esNuevo = false;
    		$scope.consultarVeredas();
    		$scope.consultarNombresVeredas();
    	}
    	
    	$scope.consultarVeredas = function(){
    		veredasServices
    		.consultarTodo()
    		.then(function(veredas){
    			$scope.veredas = veredas;
    		});
    	}
    	$scope.consultarNombresVeredas = function(){
    		veredasServices
    		.consultarNombres()
    		.then(function(nombresVeredas){
    			$scope.nombresVeredas = nombresVeredas;
    		});
    	}
    	
    	$scope.onAgregar = function(){
    		$scope.esNuevo = !$scope.esNuevo;
    	}
    	$scope.onCancelar= function(){
    		$scope.esNuevo = !$scope.esNuevo;
    		$scope.veredaNueva = {};
    	}
    	
    	$scope.onCrear = function(){
    		if($scope.veredaNueva.nombre){
    			veredasServices
        		.crear($scope.veredaNueva.nombre)
        		.then(function(respuesta){
        			modalFactory.abrirDialogo(respuesta);
        			$scope.init();
        		});
    		}else{
    			modalFactory.abrir(CONSTANTES.ESTADO.ERROR, CONSTANTES.VEREDA.ERR_NOMBRE_VEREDA);
    		}
    	}
    	
    	$scope.init();

    }])

});