/*global define*/
'use strict';

/**
 * Services
 */
define(['siav-module'], function (app) {
    app.factory('modalConsumo', ['$uibModal', function ($uibModal) {
    	var alert, service = {
    			abrir : abrir
    		};
    		
    		function open() {
    			alert = $uibModal.open({
    				templateUrl : 'views/modals/consumo-modal.html',
    				controller : function($scope, $uibModalInstance) {
    					
    					$scope.init = function(){
    						$scope.titulo = "Información";
        					$scope.clase = "fa fa-info-circle blue";
    					}
    					
						$scope.continuar = function() {
							if($scope.consumo){
								$uibModalInstance.close($scope.consumo);
							}else{
								$scope.obligatorio = "Debe ingresar un consumo";
							}
						}
						
						$scope.cancelar = function() {
							$uibModalInstance.dismiss('cancelar');
						}
						
						$scope.init();
					}
    			});
    			return alert;
    		}
    		
    		function abrir() {
    			return open();
    		}
    		
    		return service;
    }]);
});