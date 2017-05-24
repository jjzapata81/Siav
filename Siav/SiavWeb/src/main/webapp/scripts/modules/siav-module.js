/*global define*/
'use strict';

define(['angularAMD', 
        'siav-routing', 
        'siav-http', 
        'siav-init', 
        'ui-bootstrap-tpls',
        'ui-bootstrap', 
        'angular-animate',
        'ui-mask',
        'util-module',
        'angular-scroll',
        'loading-bar'], function (angularAMD, routes, configuracionHttp, init) {
	var app = angular.module('siav-module', ['ngRoute', 'ngAnimate', 'ui.bootstrap', 'util-module', 'duScroll', 'angular-loading-bar']);
	app.config(routes);
	app.config(configuracionHttp);
	app.run(init);
    return angularAMD.bootstrap(app);
});

