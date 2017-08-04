/*global define*/
'use strict';

define(['siav-module', 'inconsistencias-services'], function (app) {
	
    return app.controller('inconsistencias-consulta-controller', ['$scope', 'inconsistenciasServices', function($scope, inconsistenciasServices){
    	
    	$scope.init = function(){
    		inconsistenciasServices
    		.consultarLog()
    		.then(function(respuesta){
    			
    		});
    	}
    	
    	$scope.init();

    }])

});