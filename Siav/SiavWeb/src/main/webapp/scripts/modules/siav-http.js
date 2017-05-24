/*global define*/
'use strict';

define(['localstorage'], function () {
	return function ($httpProvider) {
		$httpProvider.defaults.headers.common['siav_usuario'] = getData("user");
		$httpProvider.interceptors.push('interceptor');
    }
});
