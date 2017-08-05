/*global define*/
'use strict';

define(['siav-module', 'pagos-services'], function (app) {
	
    return app.controller('pagos-consulta-controller', ['$scope', 'pagosServices', function($scope, pagosServices){
    	
    	$scope.init = function(){
    		$scope.filtro = {};
            $scope.hayResultado = false;
            $scope.resultado = null;
            $scope.lista = null;
    	}
    	
        
    	$scope.onBuscar = function(){
    		pagosServices
    		.consultar($scope.filtro)
    		.then(function(resultado){
    			$scope.hayResultado = resultado.length > 0;
    			$scope.resultado = resultado;
    		});
    	}
    	
    	$scope.onSeleccionar = function(pago){
    		$scope.lista = pago.pagos;
    	}
    	
    	
    	$scope.onCancelar = function(){
    		$scope.init();
    	}
    	
    	$scope.init();

    }])

});