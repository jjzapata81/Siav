/*global define*/
'use strict';

/**
 * Services
 */
define(['siav-module'], function (app) {
    app.factory('modalFactory', ['$uibModal', function ($uibModal) {
    	var alert, service = {
    			abrirDialogo : abrirDialogo,
    			abrir : abrir
    		};
    		
    		function open(respuesta) {
    			alert = $uibModal.open({
    				templateUrl : 'views/modals/info-modal.html',
    				controller : function($scope, $uibModalInstance, respuesta) {
    					
    					$scope.init = function(){
    						$scope.titulo = "Información";
    						$scope.mensaje = respuesta.mensaje;
        					$scope.puedeCancelar = "INFO" == respuesta.estado;
        					$scope.obtenerClase();
    					}
    					
    					$scope.obtenerClase = function(){
    						if("ERROR" == respuesta.estado){
    							$scope.clase = "fa fa-exclamation-triangle red";
    						}else if("OK" == respuesta.estado){
    							$scope.clase = "fa fa-check-circle green";
    						}else{
    							$scope.clase = "fa fa-info-circle blue";
    						}
    					}
    					
						$scope.continuar = function() {
							$uibModalInstance.close("OK");
						}
						
						$scope.cancelar = function() {
							$uibModalInstance.dismiss('cancelar');
						}
						
						$scope.init();
						
					},
					resolve : {
						respuesta : function() {
							return respuesta;
						}
					}
    			});
    			return alert;
    		}
    		
    		function abrirDialogo(respuesta) {
    			return open(respuesta);
    		}
    		
    		function abrir(estado, mensaje) {
    			var respuesta = {
    					mensaje : mensaje,
    					estado :estado
    			}
    			return open(respuesta);
    		}
    		
    		return service;
    }]);
});