/*global define*/
'use strict';

define(['localstorage'], function () {
	return function($rootScope, $location) {
		$rootScope
			.$on('$routeChangeStart', function(event, toState, toParams, fromState, fromParams){
			var user = getData("user");
			if (!user) {
				$location.path("/inicio");
			}
		});
	}
});
