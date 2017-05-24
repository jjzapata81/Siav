/*global define*/
'use strict';

define(['siav-module', 'inconsistencias-services', 'causas-no-lectura-services', 'modal-factory', 'constantes'], function (app) {
	
    return app.controller('inconsistencias-controller', ['$scope', '$filter', 'inconsistenciasServices', 'causasServices', 'modalFactory', 'CONSTANTES', function($scope, $filter, inconsistenciasServices, causasServices, modalFactory, CONSTANTES){
    	
    	$scope.init = function(){
    		$scope.estaEditando = false;
    		$scope.incompletos = [];
    		$scope.consultarCausasNoLectura();
    		
    	}
    	
    	$scope.consultarCausasNoLectura = function(){
    		causasServices
    		.consultar()
    		.then(function(causas){
    			$scope.causas = causas;
    			$scope.consultarIncompletos();
    		});
    	}
    	
    	$scope.onCorregir = function(consumo){
    		$scope.estaEditando = true;
    		$scope.esPromedio = true;
    		$scope.corregir = angular.copy(consumo);
    		$scope.corregir.lecturaFinal = $scope.corregir.lecturaAnterior;
    		$scope.onRecalcular();
    	}
    	
    	$scope.onSeleccionar = function(){
    		$scope.esPromedio = !$scope.esPromedio;
    	}
    	
    	$scope.onGuardar = function(){
    		if($scope.validar()){
    			var request = {};
    			request.numeroInstalacion = $scope.corregir.instalacion.numeroInstalacion;
    			request.consumo = $scope.esPromedio ? $scope.corregir.consumoPromedio : $scope.corregir.consumoNuevoMedidor;
    			request.lecturaCorregida = $scope.corregir.lecturaFinal;
    			request.antiguoMedidor = $scope.corregir.instalacion.serieMedidor;
    			request.nuevoMedidor = $scope.corregir.serieNueva;
    			inconsistenciasServices
    			.guardar(request)
    			.then(function(respuesta){
    				modalFactory.abrirDialogo(respuesta);
    				$scope.init();
    			});
    		}
    	}
    	
    	$scope.validar = function(){
    		if($scope.corregir.lecturaFinal < $scope.corregir.lecturaAnterior){
    			modalFactory.abrir(CONSTANTES.ESTADO.ERROR, CONSTANTES.INCONSISTENCIA.ERR_LECTURA_FINAL_MENOR);
    			return false;
    		}
    		if(!$scope.corregir.serieNueva){
    			modalFactory.abrir(CONSTANTES.ESTADO.ERROR, CONSTANTES.INCONSISTENCIA.ERR_SERIAL_OBLIGATORIO);
    			return false;
    		}
    		return true;
    	}
    	
    	$scope.onCancelar = function(){
    		$scope.estaEditando = false;
    		$scope.corregir = {};
    	}
    	
    	$scope.consultarIncompletos = function(){
    		inconsistenciasServices
        	.consultarConsumosPendientes()
        	.then(function(incompletos){
        		$scope.incompletos = incompletos;
        		angular.forEach($scope.incompletos, function(incompleto, key) {
    				var causa = $filter('filter')($scope.causas, { codigo : incompleto.codigoCausaNoLectura })[0];
    				incompleto.causaNoLectura = causa.descripcion;
    			});
        		
        	});
    	}
    	
    	$scope.onRecalcular = function(){
    		$scope.corregir.ultimoConsumo = $scope.corregir.lecturaFinal - $scope.corregir.lecturaAnterior;
    		$scope.corregir.consumoNuevoMedidor = $scope.corregir.ultimoConsumo + $scope.corregir.lecturaActual;
    	}
    	
    	$scope.itemsPerPage = 15;
        $scope.currentPage = 1;

        $scope.setPage = function (pageNo) {
          $scope.currentPage = pageNo;
        };
    	
    	$scope.init();
	
    }])

});