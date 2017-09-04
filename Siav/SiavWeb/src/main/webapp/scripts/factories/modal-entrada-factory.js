/*global define*/
'use strict';

/**
 * Services
 */
define(['siav-module'], function (app) {
    app.factory('modalEntrada', ['$uibModal', function ($uibModal) {
    	var alert, service = {
    			abrir : abrir
    		};
    		
    		function open(entrada) {
    			alert = $uibModal.open({
    				templateUrl : 'views/modals/entrada-modal.html',
    				controller : function($scope, $uibModalInstance, entrada) {
    					
    					$scope.entrada = entrada;
    					$scope.clase = "fa fa-info-circle blue";
						$scope.continuar = function() {
							$uibModalInstance.close();
						}
						
						$scope.cancelar = function() {
							$uibModalInstance.dismiss('cancelar');
						}
						
					},
					resolve : {
						entrada : function() {
							return entrada;
						}
					}
    			});
    			return alert;
    		}
    		
    		function abrir(entrada) {
    			return open(entrada);
    		}
    		
    		return service;
    }]);
});