/*global define*/
'use strict';

define(['siav-module', 'factoria-usuario', 'factoria-menu'], function (app) {
	
    return app.controller('menu-controller', ['$scope', '$location', 'usuarioFactory', 'menuFactory', function($scope, $location, usuarioFactory, menuFactory){
    	
    	$scope.menu = menuFactory.getMenu();

    	$scope.init = function(){
    		usuarioFactory.deleteInstalacion();
    	}
    	
    	$scope.goTo = function(novedad){
    		$location.path("/" + novedad.accion);
    	};
    	
    	$scope.init();
    }])

});