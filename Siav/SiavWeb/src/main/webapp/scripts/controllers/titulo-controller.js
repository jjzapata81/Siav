/*global define*/
'use strict';

define(['siav-module'], function (app) {
	
    return app.controller('titulo-controller', ['$scope', '$location', function($scope, $location){
    	
    	$scope.onIniciar = function(){
    		$location.path("/principal");
    	}
    	
    	$scope.onCerrar = function(){
    		addData("user", null);
    		$location.path("/inicio");
    	}

    }])

});