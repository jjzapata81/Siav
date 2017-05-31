/*global define*/
'use strict';

/**
 * Services
 */
define(['siav-module'], function (app) {
    app.factory('modalObservacion', ['$uibModal', function ($uibModal) {
    	var alert, service = {
    			abrir : abrir
    		};
    		
    		function open() {
    			alert = $uibModal.open({
    				templateUrl : 'views/modals/observacion-modal.html',
    				controller : function($scope, $uibModalInstance) {
    					
    					$scope.init = function(){
    						$scope.titulo = "Información";
        					$scope.clase = "fa fa-info-circle blue";
    					}
    					
						$scope.continuar = function() {
							if(!$scope.observacion){
								$scope.error = "Debe ingresar una justificación.";
							}else{
								$uibModalInstance.close($scope.observacion);
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