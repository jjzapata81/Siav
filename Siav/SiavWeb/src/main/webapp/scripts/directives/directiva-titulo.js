/*global define*/
'use strict';

define(['siav-module'], function (app) {
	
    return app.directive('titulo', [function() {
    	return {
    		restrict : 'E',
    		templateUrl : 'views/titulo-siav.html'
    	}
    }])

});