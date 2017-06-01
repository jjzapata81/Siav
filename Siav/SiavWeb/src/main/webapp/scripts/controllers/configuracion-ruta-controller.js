/*global define*/
'use strict';

define(['siav-module', 'ruta-services', 'ramal-services', 'constantes', 'modal-factory'], function (app) {
	
    return app.controller('configuracion-ruta-controller', ['$scope', '$filter', 'rutaServices', 'ramalServices', 'modalFactory', 'CONSTANTES', function($scope, $filter, rutaServices, ramalServices, modalFactory, CONSTANTES){
    	
    	$scope.init = function(){
    		$scope.instalaciones = [];
    		$scope.estaEditando = false;
    		$scope.instalacionAnterior = null;
    		$scope.esPrimer = 'true';
    		$scope.mostrarInstalacion = false;
    		$scope.consultarInstalaciones();
    	}
    	
    	$scope.consultarRamales = function(){
    		ramalServices
    		.consultar()
    		.then(function(ramales){
    			$scope.ramales = ramales;
    			angular.forEach($scope.instalaciones, function(instalacion, key) {
    				var filtro = $filter('filter')($scope.ramales, { codigoRamal : instalacion.ramal })[0];
    				instalacion.ramal = filtro;
    			});
    		});
    	}
    	
    	$scope.consultarInstalaciones = function(){
    		rutaServices
    		.consultar()
    		.then(function(instalaciones){
    			$scope.instalaciones = instalaciones;
    			$scope.consultarRamales();
    		});
    	}
    	
    	$scope.seleccionOrden = function(){
    		$scope.mostrarInstalacion = $scope.esPrimer == 'false';
    		if(!$scope.mostrarInstalacion){
    			$scope.instalacionAnterior = null;
    		}
    	}
    	
    	$scope.onBuscar = function(){
    		if($scope.numeroInstalacion){
    			rutaServices
        		.consultarPorNumero($scope.numeroInstalacion)
        		.then(function(instalacion){
        			$scope.instalacionCorregir = instalacion;
        			var filtro = $filter('filter')($scope.ramales, { codigoRamal : $scope.instalacionCorregir.ramal })[0];
        			$scope.instalacionCorregir.ramal = filtro;
        			$scope.estaEditando = true;
        			$scope.cambiarOrden = 'No';
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
    			var request = angular.copy($scope.instalacionCorregir);
    			request.instalacionAnterior = $scope.instalacionAnterior;
    			request.ramal = $scope.instalacionCorregir.ramal.codigoRamal;
    			rutaServices
        		.guardar(request)
        		.then(function(respuesta){
        			modalFactory.abrirDialogo(respuesta);
        			$scope.init();
        		});
    		}
    	}
    	
    	$scope.esCambioOrden = function(){
    		$scope.instalacionCorregir.cambiarOrden = $scope.cambiarOrden == 'Si';
    	}
    	
    	$scope.validar = function(){
    		if($scope.mostrarInstalacion && !$scope.instalacionAnterior){
    			modalFactory.abrir(CONSTANTES.ESTADO.ERROR, CONSTANTES.CONFIGURACION_RUTA.ERR_INSTALACION_OBLIGATORIO);
    			return false;
    		}
    		return true;
    	}
    	
    	$scope.onCambiarOrden = function(instalacion){
    		$scope.estaEditando = true;
    		$scope.cambiarOrden = 'No';
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