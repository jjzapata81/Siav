/*global define*/
'use strict';

define(['siav-module', 'siav-services'], function (app) {
	
    return app.controller('red-controller', ['$scope', 'siavServices', function($scope, siavServices){
    	
    	$scope.init = function(){
    		siavServices
    		.consultarIp()
    		.then(function(response){
    			$scope.ip = response.ip;
    		});
    	}
    	
    	$scope.init();
	

    }])

});