/*global define*/
'use strict';

define(['siav-module', 'localstorage', 'login-services', 'modal-factory', 'factoria-menu', 'constantes'], function (app) {
	
    return app.controller('login-controller', ['$scope', '$location', 'loginServices', 'modalFactory', 'menuFactory', 'CONSTANTES', function($scope, $location, loginServices, modalFactory, menuFactory, CONSTANTES){
    	
    	$scope.onLogin  = function(){
    		loginServices
    		.ingresar($scope.login)
    		.then(function(respuesta){
    			modalFactory.abrirDialogo(respuesta);
    			if(CONSTANTES.ESTADO.OK === respuesta.estado){
    				addData("user", $scope.login.user);
    				menuFactory.setMenu(respuesta.menu);
            		$location.path("/principal");
    			}
    			
    		});
    		
    	}

    }])

});