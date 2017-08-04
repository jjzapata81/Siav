/*global define*/
'use strict';

define(['siav-module', 'pagos-services'], function (app) {
	
    return app.controller('pagos-consulta-controller', ['$scope', 'pagosServices', function($scope, pagosServices){
    	
    	$scope.init = function(){
    		pagosServices
    		.consultar()
    		.then(function(pagos){
    			$scope.pagos = pagos;
    		});
    	}
    	
    	$scope.itemsPerPage = 20;
        $scope.currentPage = 1;

        $scope.setPage = function (pageNo) {
          $scope.currentPage = pageNo;
        };
    	
    	$scope.init();

    }])

});