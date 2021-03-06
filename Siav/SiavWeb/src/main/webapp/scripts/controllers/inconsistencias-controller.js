/*global define*/
'use strict';

define(['siav-module', 'inconsistencias-services', 'causas-no-lectura-services', 'modal-factory', 'modal-observacion', 'constantes', 'modal-consumo-factory'], function (app) {
	
    return app.controller('inconsistencias-controller', ['$scope', '$filter', 'inconsistenciasServices', 'causasServices', 'modalFactory', 'modalObservacion', 'CONSTANTES', 'modalConsumo', function($scope, $filter, inconsistenciasServices, causasServices, modalFactory, modalObservacion, CONSTANTES, modalConsumo){
    	
    	$scope.init = function(){
    		$scope.correccionLectura = 'true';
    		$scope.estaEditando = false;
    		$scope.estaEditandoRiesgo = false;
    		$scope.esConsultaInstalacion = false;
    		$scope.aux = false;
    		$scope.rango = {};
    		$scope.incompletos = [];
    		$scope.consumosRiesgo = [];
    		$scope.consultarCausasNoLectura();
    		
    	}
    	
    	$scope.tipoCorrecion = function(){
    		if($scope.correccionLectura=='false'){
    			$scope.corregirRiesgo.lecturaCorreccion = null;
    		}
    		$scope.corregirRiesgo = angular.copy($scope.corregirRiesgoTemp);
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
    		$scope.esPromedio = false;
    		$scope.corregir = angular.copy(consumo);
    		$scope.corregir.lecturaFinal = $scope.corregir.lecturaAnterior;
    		$scope.onRecalcular();
    	}
    	
    	$scope.onCorregirRiesgo = function(consumo){
    		$scope.estaEditandoRiesgo = true;
    		$scope.corregirRiesgo = angular.copy(consumo);
    		$scope.corregirRiesgoTemp = angular.copy(consumo);
    	}
    	
    	$scope.onSeleccionar = function(opcion){
    		$scope.esPromedio = 'PROMEDIO' === opcion;
    	}
    	
    	$scope.onGuardar = function(){
    		if($scope.validar()){
    			modalObservacion
    			.abrir()
        		.result
        		.then(function(observacion){
        			$scope.continuarGuardar(observacion);
        		});
    			
    		}
    	}
    	
    	$scope.continuarGuardar = function(observacion){
    		var request = {};
			request.numeroInstalacion = $scope.corregir.instalacion.numeroInstalacion;
			request.consumo = $scope.esPromedio ? $scope.corregir.consumoPromedio : $scope.corregir.consumoNuevoMedidor;
			request.lecturaCorregida = $scope.corregir.lecturaFinal;
			request.antiguoMedidor = $scope.corregir.instalacion.serieMedidor;
			request.nuevoMedidor = $scope.corregir.serieNueva;
			request.observacion = observacion;
			inconsistenciasServices
			.guardar(request)
			.then(function(respuesta){
				modalFactory.abrirDialogo(respuesta);
				$scope.init();
			});
    		
    	}
    	
    	$scope.onConsultaInstalacion = function(){
    		$scope.esConsultaInstalacion = true;
    		$scope.hayRiesgo = false;
    		$scope.buscarInstalacion = null;
    	}
    	
    	$scope.onCancelarConsulta = function(){
    		$scope.esConsultaInstalacion = false;
    		$scope.hayRiesgo = false;
    		$scope.buscarInstalacion = null;
    	}
    	
    	$scope.onGuardarRiesgo = function(){
    		if($scope.validarRiesgo()){
    			modalObservacion
    			.abrir()
        		.result
        		.then(function(observacion){
        			if($scope.correccionLectura=='true'){
        				$scope.continuarGuardarRiesgoLectura(observacion);
        			}else{
        				$scope.continuarGuardarRiesgoConsumo(observacion);
        			}
        		});
    		}
    	}
    	
    	$scope.continuarGuardarRiesgoLectura =function(observacion){
    		var request = {};
			request.numeroInstalacion = $scope.corregirRiesgo.instalacion;
			request.consumo = $scope.corregirRiesgo.consumoCorregido;
			request.lecturaCorregida = $scope.corregirRiesgo.lecturaCorreccion;
			request.antiguoMedidor = $scope.corregirRiesgo.serieMedidor;
			request.observacion = observacion;
			inconsistenciasServices
			.guardarCorreccionLectura(request)
			.then(function(respuesta){
				$scope.terminar(respuesta);
			});
    	}
    	
    	$scope.continuarGuardarRiesgoConsumo =function(observacion){
    		var request = {};
			request.numeroInstalacion = $scope.corregirRiesgo.instalacion;
			request.consumo = $scope.corregirRiesgo.consumoCorregido;
			request.antiguoMedidor = $scope.corregirRiesgo.serieMedidor;
			request.observacion = observacion;
			inconsistenciasServices
			.guardarCorreccionConsumo(request)
			.then(function(respuesta){
				$scope.terminar(respuesta);
				
			});
    	}
    	
    	$scope.terminar = function(respuesta){
    		modalFactory.abrirDialogo(respuesta);
			if($scope.aux){
				$scope.hayRiesgo = false;
	    		$scope.init();
			}else{
				$scope.estaEditandoRiesgo = false;
				$scope.consultarRango();
				$scope.correccionLectura = 'true';
			}
			
    	}
    	
    	$scope.validar = function(){
    		if(!$scope.corregir.serieNueva){
    			modalFactory.abrir(CONSTANTES.ESTADO.ERROR, CONSTANTES.INCONSISTENCIA.ERR_SERIAL_OBLIGATORIO);
    			return false;
    		}
    		return true;
    	}
    	
    	$scope.validarRiesgo = function(){
    		if($scope.correccionLectura=='true' && (!$scope.corregirRiesgo.lecturaCorreccion || $scope.corregirRiesgo.lecturaCorreccion < 0)){
    			modalFactory.abrir(CONSTANTES.ESTADO.ERROR, CONSTANTES.INCONSISTENCIA.ERR_LECTURA_CORRECCION);
    			return false;
    		}
    		if($scope.correccionLectura=='true' && ($scope.corregirRiesgo.lecturaAnterior > $scope.corregirRiesgo.lecturaCorreccion)){
    			modalFactory.abrir(CONSTANTES.ESTADO.ERROR, CONSTANTES.INCONSISTENCIA.ERR_LECTURA_CORRECCION_MENOR);
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
    		$scope.correccionLectura = 'true';
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
    				$scope.aux = false;
    			}
    		});
    		
    	}
    	
    	$scope.onBuscarInstalacion = function(){
    		if($scope.buscarInstalacion){
    			inconsistenciasServices
    			.consultarInstalacion($scope.buscarInstalacion)
    			.then(function(consumosRiesgo){
    				$scope.consumosRiesgo = consumosRiesgo;
    				$scope.hayRiesgo = $scope.consumosRiesgo.length != 0;
    				$scope.aux = true;
    				$scope.esConsultaInstalacion = false;
    			});
    		}else{
    			modalFactory.abrir(CONSTANTES.ESTADO.ERROR, CONSTANTES.INSTALACION.ERR_BUSQUEDA_OBLIGATORIO);
    		}
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