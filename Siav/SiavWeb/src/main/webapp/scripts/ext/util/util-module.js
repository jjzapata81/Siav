/*global define*/
'use strict';

define(['moment'], function (moment) {
	var app = angular.module('util-module', ['ui.bootstrap']);
	app.service('utilidadesFecha', function() {
		return {

			convertirFechaUTC : function(date){
				if (!date) { return null; }
				var _utc = new Date(date.getUTCFullYear(), date.getUTCMonth(), date.getUTCDate(),  date.getUTCHours(), date.getUTCMinutes(), date.getUTCSeconds());
				return _utc;
	       	},
	       	
	       	esFechaPosteriorAHoy : function(date){
				if (!date) { return false; }
				var hoy = new Date();
		        return date > hoy;
	       	},
	       	
	       	diferenciaFechasEnDias : function(date1, date2){
				if (!date1|| !date2) { return false; }
				return moment(date1).diff(moment(date2), 'days');
	       	},
	       	
	     	diferenciaFechasEnMeses : function(date1, date2){
				if (!date1|| !date2) { return false; }
				return moment(date1).diff(moment(date2), 'months');
	       	}
		};
	});
	
	app.factory('factoriaNotificaciones', ['$uibModal', function ($uibModal) {
		var alert, service = {
    			error : error
    		};
    		
    		function open(message, clazz) {
    			alert = $uibModal.open({
    				templateUrl : 'views/modals/info-modal.html',
    				windowClass : clazz,
    				controller : function($scope, $uibModalInstance, $document, config) {
    					
    					$scope.init = function(){
    						$scope.titulo = "Error";
    						$scope.mensaje = config.msg;
        					$scope.puedeCancelar = false;
        					$scope.clase = "fa fa-exclamation-triangle red";
    					}
						
						$scope.continuar = function() {
							$uibModalInstance.dismiss('cancelar');
						}
						
						$scope.init();
						
					},
    				resolve: {
    					config : function () {
    						return {
    							msg : message
    							
    						};
    					}
    				}
    			});
    			return alert;
    		}
    		
    		function error(message) {
    			return open(message);
    		}
    		
    		return service;
    	 
    	 return contrato;
    }]);
	
	app.service('interceptor', ['$q', '$injector', function($q, $injector){
		var interceptor = this;

		interceptor.responseError = function(response) {
			var factoriaNotificaciones = $injector.get('factoriaNotificaciones');
			var mensaje = "";
			if(!response.headers()["mensaje-interno"]){
				mensaje = "Ocurrió un error al procesar la información, comuníquese con el administrador del servicio";
			}else{
				mensaje = response.headers()["mensaje-interno"];
			}
			factoriaNotificaciones.error(mensaje);
			return $q.reject(response);
		};
	}]);
	
		
	app.directive('siavCalendario', ['$compile', function ($compile) {
		var obtenerTemplate = function() {
			return '<div class="cal">' +
		    	'<button type="button" class="button-calendar" name="button" ng-click="open($event)" tabindex="-1"><i class="fa fa-calendar-check-o icon-calendar"></i></button>' +
		    	'<input id="{{ngId}}" type="text" uib-datepicker-popup="{{format}}" ' +
		    	'	is-open="status.opened" min-date="minDate" ' +
		    	'	max-date="maxDate" datepicker-options="dateOptions"' + 
		    	'	close-text="Cerrar"' +
		    	'	current-text="Hoy"' +
		    	'	clear-text="Limpiar"' +
		    	'	ng-model="ngModel"' +
		    	'	ng-class="{{ngClass}}"' +
		    	'	placeholder="{{siavPlaceholder}}" />' +
		    	'<div class="clear"></div>' +
		    '</div>';
		}
		
    	return {
    	    restrict : 'E',
    	    require: '^ngModel',
    	    template : obtenerTemplate,

    	    scope : {
    	    	ngModel: '=',
    	    	gcoPlaceholder: '@',
    	    	disabled: '@',
    	    	required: '@',
    	    	ngClass: '@',
    	    	name: '@',
    	    	ngId : '@'
    	    },
    	    //Organizar ngClass
    	    replace : true,
    	    controller: ['$scope', function($scope) {
    	    	$scope.open = function($event) {
    	    		if ($scope.disabled === "true") {
    	    			return;
    	    		}
    	    	    $scope.status.opened = true;
    	    	};

    	    	$scope.format = 'dd/MM/yyyy';
    	    	$scope.maxDate = new Date(2020, 5, 22);
    	    	$scope.minDate = new Date(1900, 1, 1);
    	    	 
    	    	$scope.status = {
    	    	    opened: false
    	    	 }
    	      }],
    	    link : function(scope, element, attrs) {
    	    	
    	    	if (scope.disabled == "true") {
    	    		element.children("input").attr("disabled", scope.disabled);
    	    	}
    	    	if (scope.required == "required") {
    	    		element.children("input").attr("required", '');
    	    	}
    	    	if (scope.name) {
    	    		element.children("input").attr("name", scope.name);
    	    	}
    	    	return $compile(element)(scope);
    	    } 
    	  }
    }]);
	
	app.directive('siavMaxLength', function ($parse) {
	    return {
	        scope: {
	          maxLength: '@'
	        },
	        link: function (scope, elm, attrs) {
	         
	          elm.bind('keypress', function(e){
	           
	            if(elm[0].value.length >= scope.maxLength){
	              e.preventDefault();
	              return false;
	            }
	          });
	        }
	    }   
	});
	
	app.directive('siavNumerico', function ($parse) {
	return {
		link: function (scope, elm, attrs) {
			elm.bind('keypress', function(e) {
				if (!(e.which >= 48 && e.which <= 57)) {
					e.preventDefault();
					return false;
				}
			});
		}
	}
	});
	
	app.directive('siavTexto', function ($parse) {
	    return {
	        link: function (scope, elm, attrs) {
	          elm.bind('keypress', function(e) {
	           if ((e.which >= 48 && e.which <= 57)) {
	        	   e.preventDefault();
		           return false;
	           }
	          });
	        }
	    }   
	});
	
	app.directive('ngEnter', function () {
	    return function (scope, element, attrs) {
	        element.bind("keydown keypress", function (event) {
	            if(event.which === 13) {
	                scope.$apply(function (){
	                    scope.$eval(attrs.ngEnter);
	                });

	                event.preventDefault();
	            }
	        });
	    };
	});
	
});