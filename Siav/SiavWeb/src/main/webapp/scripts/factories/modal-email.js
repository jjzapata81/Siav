/*global define*/
'use strict';

/**
 * Services
 */
define(['siav-module'], function (app) {
    app.factory('modalEmail', ['$uibModal', function ($uibModal) {
    	var alert, service = {
    			abrir : abrir
    		};
    		
    		function open() {
    			alert = $uibModal.open({
    				templateUrl : 'views/modals/email-modal.html',
    				controller : function($scope, $uibModalInstance) {
    					
    					$scope.init = function(){
    						$scope.titulo = "Información";
        					$scope.clase = "fa fa-info-circle blue";
        					$scope.emailValido = false;
    					}
    					
    					$scope.onChange = function(){
    						$scope.obligatorio = null;
    						$scope.emailValido = $scope.email.indexOf("@") != -1 && $scope.email.indexOf(".") != -1;
    					}
    					
						$scope.continuar = function() {
							if($scope.emailValido){
								$uibModalInstance.close($scope.email);
							}else{
								$scope.obligatorio = "Debe ingresar un e-mail válido.";
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