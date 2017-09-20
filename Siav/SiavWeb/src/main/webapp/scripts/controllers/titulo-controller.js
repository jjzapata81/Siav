/*global define*/
'use strict';

define(['siav-module', 'factoria-menu'], function (app) {
	
    return app.controller('titulo-controller', ['$scope', '$location', 'menuFactory', function($scope, $location, menuFactory){
    	
    	$scope.usuario = menuFactory.getUsuario();
    	
    	$scope.onIniciar = function(){
    		$location.path("/principal");
    	}
    	
    	$scope.onCerrar = function(){
    		addData("user", null);
    		$location.path("/inicio");
    	}

    }])

});