/*global define*/
'use strict';

define(['siav-module', 'siav-services', 'modal-factory', 'constantes'], function (app) {
	
    return app.controller('empresa-controller', ['$scope', 'siavServices',  'modalFactory', 'CONSTANTES', function($scope, siavServices,  modalFactory, CONSTANTES){
    	
    	$scope.init = function(){
    		$scope.estaEditando = false;
    		siavServices
    		.consultarEmpresa()
    		.then(function(empresa){
    			$scope.empresa = empresa;
    		});
    	}
    	
    	$scope.onCancelar = function (){
    		$scope.empresa = $scope.temp;
    		$scope.estaEditando = false;
    	}
    	
    	$scope.onEditar = function(){
    		$scope.temp = angular.copy($scope.empresa);
    		$scope.estaEditando = true;
    	}
    	
    	$scope.onGuardar = function(){
    		if($scope.validar()){
    			siavServices
    			.actualizarEmpresa($scope.empresa)
    			.then(function(respuesta){
    				modalFactory.abrirDialogo(respuesta);
    				$scope.init();
    			});
    		}
    	}
    	
    	$scope.validar = function (){
    		return true;
    	}
    	    	
    	$scope.init();

    }])

});