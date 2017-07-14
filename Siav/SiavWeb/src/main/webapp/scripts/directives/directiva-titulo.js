/*global define*/
'use strict';

define(['siav-module', 'titulo-controller'], function (app) {
	
    return app.directive('titulo', [function() {
    	return {
    		restrict : 'E',
    		templateUrl : 'views/titulo-siav.html',
            scope: {
            	
            },
            controller : 'titulo-controller',
            link: function (scope, elm, attrs) {
                
            }
    	}
    }])

});