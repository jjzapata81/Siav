/*global define*/
'use strict';

define(['siav-module', 'usuario-sistema-services'], function (app) {
	
    return app.controller('seguridad-roles-controller', ['$scope', 'factoriaNotificaciones', 'usuarioSistemaServices', function($scope, factoriaNotificaciones, usuarioSistemaServices){
    	
    	$scope.init = function(){
    		$scope.consultarPerfiles();
    	}	
    	
    	$scope.consultarPerfiles = function(){
    		usuarioSistemaServices
			.consultarPerfiles()
			.then(function(perfiles){
    			$scope.perfiles = perfiles;
    			console.log(perfiles);
			});
    	}
    	
    	$scope.init();

    }])

});