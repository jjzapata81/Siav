/*global define*/
'use strict';

define(['siav-module', 'modal-factory', 'constantes', 'pagos-services', 'bancos-services'], function (app) {
	
    return app.controller('pagos-importados-controller', ['$scope', 'pagosServices', 'bancosServices', 'modalFactory', 'CONSTANTES', function($scope, pagosServices, bancosServices, modalFactory, CONSTANTES){
    	
    	var file = "";
    	var fileInput = angular.element('#txtFile');
    	
    	$scope.files = [];
        
        $scope.archivos = [];
        
        $scope.mensaje = "Seleccione un archivo";
        
        $scope.init = function(){
        	$scope.cargarCuentas();
        }
        
        $scope.cargarCuentas = function(){
    		bancosServices
    		.consultarCuentas()
    		.then(function(cuentas){
    			$scope.cuentas = cuentas;
    			angular.forEach($scope.cuentas, function(cuenta, key) {
    				cuenta.nombreCompleto = cuenta.numeroCuenta + " - " + cuenta.nombre;
    			});
    		});
    	}
    	
        $scope.hasError = function(){
    		var extn = file.name.split(".").pop();		
    		if(extn!="csv"){
    			modalFactory.abrir(CONSTANTES.ESTADO.ERROR, CONSTANTES.PAGO.ERR_EXTENSION);
    			return true;
    		}
    		return false;
       }
           
        fileInput.bind('change', function(e) {
        	file = e.target.files[0];
        	$scope.mensaje = file.name;
        	if(!$scope.hasError() || $scope.hayCuenta()){
        		pagosServices
            	.cargarPagos(file, $scope.cuentaSeleccionada)
            	.then(function(){
            		$scope.files = null;
            		modalFactory.abrir(CONSTANTES.ESTADO.OK, "La carga del archivo " + file.name + " terminó exitosamente.");
        			$scope.mensaje = "Seleccione un archivo...";
            	});
        		$scope.$apply();
        		$('#txtFile').replaceWith( $("#txtFile").val('').clone(true));
            	
    	    }
    	 });
        
        $scope.hayCuenta = function(){
        	if(!$scope.cuentaSeleccionada){
        		modalFactory.abrir(CONSTANTES.ESTADO.ERROR, CONSTANTES.PAGO.ERR_NUMERO_CUENTA);
        		return false;
        	}
        	return true;
        }
     
        $scope.init();
	

    }])

});