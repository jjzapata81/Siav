/*global define*/
'use strict';

define(['siav-module', 'menu-controller'], function (app) {
	
    return app.directive('menu', [function() {
    	return {
    		restrict : 'E',
    		templateUrl : 'views/menu.html',
            scope: {
            	
            },
            controller : 'menu-controller',
            link: function (scope, elm, attrs) {
                
            }
    	}
    }])

});