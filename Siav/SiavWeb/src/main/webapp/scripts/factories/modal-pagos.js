/*global define*/
'use strict';

/**
 * Services
 */
define(['siav-module'], function (app) {
    app.factory('modalPagos', ['$uibModal', function ($uibModal) {
    	var alert, service = {
    			abrir : abrir
    		};
    		
    		function open(respuesta) {
    			alert = $uibModal.open({
    				templateUrl : 'views/modals/pagos-modal.html',
    				controller : function($scope, $uibModalInstance, respuesta) {
    					
    					$scope.pagos = respuesta;
    					
						$scope.cancelar = function() {
							$uibModalInstance.dismiss('cancelar');
						}
						
					},
					resolve : {
						respuesta : function() {
							return respuesta;
						}
					}
    			});
    			return alert;
    		}
    		
    		function abrir(respuesta) {
    			return open(respuesta);
    		}
    		
    		return service;
    }]);
});