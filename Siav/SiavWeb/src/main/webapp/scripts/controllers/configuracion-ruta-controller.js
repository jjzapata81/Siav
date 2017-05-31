/*global define*/
'use strict';

define(['siav-module', 'ruta-services', 'ramal-services', 'constantes', 'modal-factory'], function (app) {
	
    return app.controller('configuracion-ruta-controller', ['$scope', 'rutaServices', 'ramalServices', 'modalFactory', 'CONSTANTES', function($scope, rutaServices, ramalServices, modalFactory, CONSTANTES){
    	
    	$scope.init = function(){
    		$scope.instalaciones = [];
    		$scope.estaEditando = false;
    		$scope.esPrimer = 'true';
    		$scope.consultarInstalaciones();
    		$scope.consultarRamales();
    	}
    	
    	$scope.consultarRamales = function(){
    		ramalServices
    		.consultar()
    		.then(function(ramales){
    			$scope.ramales = ramales;
    		});
    	}
    	
    	$scope.consultarInstalaciones = function(){
    		rutaServices
    		.consultar()
    		.then(function(instalaciones){
    			$scope.instalaciones = instalaciones;
    		});
    	}
    	
    	$scope.onBuscar = function(){
    		if($scope.numeroInstalacion){
    			rutaServices
        		.consultarPorNumero($scope.numeroInstalacion)
        		.then(function(instalacion){
        			$scope.instalacionCorregir = instalacion;
        			$scope.estaEditando = true;
        		});
    		}else{
    			modalFactory.abrir(CONSTANTES.ESTADO.ERROR, CONSTANTES.INSTALACION.ERR_BUSQUEDA_OBLIGATORIO);
    		}
    	}
    	
    	$scope.onCancelar = function(){
    		$scope.init();
    	}
    	
    	$scope.onGuardar = function(){
    		if($scope.validar()){
    			rutaServices
        		.guardar($scope.instalacionCorregir)
        		.then(function(respuesta){
        			modalFactory.abrirDialogo(respuesta);
        			$scope.init();
        		});
    		}
    	}
    	
    	$scope.validar = function(){
    		return true;
    	}
    	
    	$scope.onCambiarOrden = function(instalacion){
    		$scope.estaEditando = true;
    		$scope.instalacionCorregir = angular.copy(instalacion);
    	}
    	
    	$scope.itemsPerPage = 15;
        $scope.currentPage = 1;

        $scope.setPage = function (pageNo) {
          $scope.currentPage = pageNo;
        }

    	$scope.init();
    }])

});