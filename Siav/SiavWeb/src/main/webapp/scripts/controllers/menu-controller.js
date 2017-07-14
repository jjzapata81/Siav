/*global define*/
'use strict';

define(['siav-module', 'factoria-menu'], function (app) {
	
    return app.controller('menu-controller', ['$scope', '$location', 'menuFactory', function($scope, $location, menuFactory){
    	
    	$scope.menu = menuFactory.getMenu();

    	$scope.goTo = function(novedad){
    		$location.path("/" + novedad.accion);
    	};
    	
    }])

});