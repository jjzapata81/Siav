/*global define*/
'use strict';

define(['siav-module', 'inconsistencias-services', 'causas-no-lectura-services', 'modal-factory', 'constantes', 'modal-consumo-factory'], function (app) {
	
    return app.controller('inconsistencias-controller', ['$scope', '$filter', 'inconsistenciasServices', 'causasServices', 'modalFactory', 'CONSTANTES', 'modalConsumo', function($scope, $filter, inconsistenciasServices, causasServices, modalFactory, CONSTANTES, modalConsumo){
    	
    	$scope.init = function(){
    		$scope.estaEditando = false;
    		$scope.estaEditandoRiesgo = false;
    		$scope.rango = {};
    		$scope.incompletos = [];
    		$scope.consumosRiesgo = [];
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
    	
    	$scope.onCorregirRiesgo = function(consumo){
    		$scope.estaEditandoRiesgo = true;
    		$scope.corregirRiesgo = angular.copy(consumo);
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
    	
    	$scope.onGuardarRiesgo = function(){
    		if($scope.validarRiesgo()){
    			var request = {};
    			request.numeroInstalacion = $scope.corregirRiesgo.instalacion;
    			request.consumo = $scope.corregirRiesgo.consumoCorregido;
    			request.antiguoMedidor = $scope.corregirRiesgo.serieMedidor;
    			request.lecturaCorregida = $scope.corregirRiesgo.lecturaCorregida;
    			inconsistenciasServices
    			.guardarCorreccion(request)
    			.then(function(respuesta){
    				modalFactory.abrirDialogo(respuesta);
    				$scope.estaEditandoRiesgo = false;
    				$scope.consultarRango();
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
    	
    	$scope.validarRiesgo = function(){
    		if(!$scope.corregirRiesgo.lecturaCorreccion || $scope.corregirRiesgo.lecturaCorreccion < 0){
    			modalFactory.abrir(CONSTANTES.ESTADO.ERROR, CONSTANTES.INCONSISTENCIA.ERR_LECTURA_CORRECCION);
    			return false;
    		}
    		return true;
    	}
    	
    	$scope.onCancelar = function(){
    		$scope.estaEditando = false;
    		$scope.corregir = {};
    	}
    	
    	$scope.onCancelarEdicionRiesgo = function(){
    		$scope.estaEditandoRiesgo = false;
    		$scope.corregirRiesgo = {};
    	}
    	
    	$scope.onCancelarRiesgo = function(){
    		$scope.hayRiesgo = false;
    		$scope.init();
    	}
    	
    	$scope.consultarIncompletos = function(){
    		inconsistenciasServices
        	.consultar()
        	.then(function(incompletos){
        		$scope.incompletos = incompletos;
        		angular.forEach($scope.incompletos, function(incompleto, key) {
    				var causa = $filter('filter')($scope.causas, { codigo : incompleto.codigoCausaNoLectura })[0];
    				incompleto.causaNoLectura = causa.descripcion;
    			});
        		$scope.hayCorreccion = $scope.incompletos.length != 0;
        	});
    	}
    	
    	$scope.onBuscar = function(){
    		modalConsumo
    		.abrir()
    		.result
    		.then(function(consumo){
    			if(null!=consumo){
    				$scope.rango.consumo = consumo;
    				$scope.consultarRango();
    			}
    		});
    		
    	}
    	
    	$scope.consultarRango = function(){
			inconsistenciasServices
			.consultarRango($scope.rango)
			.then(function(consumosRiesgo){
				$scope.consumosRiesgo = consumosRiesgo;
				$scope.hayRiesgo = $scope.consumosRiesgo.length != 0;
			});
    	}
    	
    	$scope.onRecalcular = function(){
    		$scope.corregir.ultimoConsumo = $scope.corregir.lecturaFinal - $scope.corregir.lecturaAnterior;
    		$scope.corregir.consumoNuevoMedidor = $scope.corregir.ultimoConsumo + $scope.corregir.lecturaActual;
    	}
    	
    	$scope.onRecalcularCorreccion = function(){
    		$scope.corregirRiesgo.consumoCorregido = $scope.corregirRiesgo.lecturaCorreccion - $scope.corregirRiesgo.lecturaAnterior;
    	}
    	
    	$scope.itemsPerPage = 15;
        $scope.currentPage = 1;

        $scope.setPage = function (pageNo) {
          $scope.currentPage = pageNo;
        };
    	
    	$scope.init();
	
    }])

});