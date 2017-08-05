/*global define*/
'use strict';

define(['siav-module', 'inconsistencias-services'], function (app) {
	
    return app.controller('inconsistencias-consulta-controller', ['$scope', 'inconsistenciasServices', function($scope, inconsistenciasServices){
    	
    	$scope.init = function(){
    		$scope.filtro = {};
            $scope.hayResultado = false;
            $scope.resultado = null;
            $scope.lista = null;
    	}
    	
        
    	$scope.onBuscar = function(){
    		inconsistenciasServices
    		.consultarLog($scope.filtro)
    		.then(function(resultado){
    			$scope.hayResultado = resultado.length > 0;
    			$scope.resultado = resultado;
    		});
    	}
    	
    	$scope.onSeleccionar = function(consumo){
    		$scope.lista = consumo.consumos;
    	}
    	
    	
    	$scope.onCancelar = function(){
    		$scope.init();
    	}
    	
    	$scope.init();

    }])

});