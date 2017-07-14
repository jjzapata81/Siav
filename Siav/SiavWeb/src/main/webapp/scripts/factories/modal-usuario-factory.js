/*global define*/
'use strict';

/**
 * Services
 */
define(['siav-module'], function (app) {
    app.factory('modalUsuario', ['$uibModal', function ($uibModal) {
    	var alert, service = {
    			abrir : abrir
    		};
    		
    		function open() {
    			alert = $uibModal.open({
    				templateUrl : 'views/modals/usuario-modal.html',
    				controller : function($scope, $uibModalInstance) {
    					
    					$scope.init = function(){
    						$scope.titulo = "Información";
        					$scope.clase = "fa fa-info-circle blue";
    					}
    					
    					$scope.onChange = function(){
    						$scope.obligatorio = null;
    					}
    					
						$scope.continuar = function() {
							if($scope.cedula){
								$uibModalInstance.close($scope.cedula);
							}else{
								$scope.obligatorio = "Debe ingresar un número de documento";
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