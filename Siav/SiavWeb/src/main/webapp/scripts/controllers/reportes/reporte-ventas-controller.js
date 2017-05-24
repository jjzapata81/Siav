/*global define*/
'use strict';

define(['siav-module', 'reportes-services'], function (app) {
	
    return app.controller('reporte-ventas-controller', ['$scope', 'reportesServices', function($scope, reportesServices, reportesFactory){

    	
    	$scope.onBuscar = function(){
    		reportesServices
    		.ventas($scope.filtro)
    		.then(function(items){
    			$scope.items = items;
    		});
    	}
    	
    	
    	$scope.itemsPerPage = 10;
        $scope.currentPage = 1;

        $scope.setPage = function (pageNo) {
          $scope.currentPage = pageNo;
        };

    }])

});