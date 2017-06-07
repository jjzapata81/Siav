/*global define*/
'use strict';

define(['siav-module', 'siav-services', 'modal-factory', 'constantes'], function (app) {
	
    return app.controller('empresa-controller', ['$scope', 'siavServices',  'modalFactory', 'CONSTANTES', function($scope, siavServices,  modalFactory, CONSTANTES){
    	
    	$scope.init = function(){
    		siavServices
    		.consultarEmmpresa()
    		.then(function(empresa){
    			$scope.empresa = empresa;
    		});
    	}
    	    	
    	$scope.init();

    }])

});