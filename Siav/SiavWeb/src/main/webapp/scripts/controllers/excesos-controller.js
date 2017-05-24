/*global define*/
'use strict';

define(['siav-module', 'excesos-services', 'contabilidad-services', 'modal-factory', 'constantes'], function (app) {
	
    return app.controller('excesos-controller', ['$scope', '$filter', 'excesosServices', 'contabilidadServices', 'modalFactory', 'CONSTANTES', function($scope, $filter, excesosServices, contabilidadServices, modalFactory, CONSTANTES){
    	
    	$scope.init = function(){
    		$scope.esNuevo = false;
    		$scope.editarLimites = false;
    		$scope.excesoNuevo = {};
    		$scope.cargarExcesos();
    		$scope.basico = {};
    		$scope.complementario = {};
    		$scope.suntuario = {};
    		$scope.consultarSistema();
    	}
    	
    	$scope.consultarSistema = function(){
    		contabilidadServices
    		.consultar()
    		.then(function(sistema){
    			$scope.contabilidad = sistema;
    			$scope.esPorEstrato = $scope.contabilidad.esPorEstrato === "Si"; 
    		});
    	}
    	
    	$scope.cargarExcesos = function(){
    		excesosServices
    		.consultar()
    		.then(function(excesos){
    			$scope.excesos = excesos;
    		});
    	}
    	
    	$scope.onGuardar = function(){
    		if($scope.camposValidos()){
    			delete($scope.excesoNuevo.tipo);
    			excesosServices
        		.guardar($scope.excesoNuevo)
        		.then(function(respuesta){
        			modalFactory.abrirDialogo(respuesta);
        			$scope.limpiarCampos();
        		});
    		}
    	}
    	
    	$scope.onEditar = function(exceso){
    		$scope.excesoNuevo = angular.copy(exceso);
    		$scope.esNuevo = true;
    	}
    	
    	$scope.onGuardarEdicionRango = function(){
    		var consumos = [];
    		consumos.push($scope.basico);
    		consumos.push($scope.complementario);
    		consumos.push($scope.suntuario);
    		excesosServices
    		.editarRango({ excesos : consumos })
    		.then(function(respuesta){
    			modalFactory.abrirDialogo(respuesta);
    			$scope.limpiarCampos();
    		});
    	}
    	
    	$scope.onEditarRango = function(){
    		modalFactory
    		.abrir(CONSTANTES.ESTADO.INFO, CONSTANTES.EXCESO.INFO_EDITAR_RANGO)
    		.result
    		.then(function(){
    			$scope.onEditarLimites();
    		});
    	}
    	
    	$scope.camposValidos = function(){
    		if(!$scope.excesoNuevo.codigo || !$scope.excesoNuevo.descripcion){
    			modalFactory.abrir(CONSTANTES.ESTADO.ERROR, CONSTANTES.EXCESO.ERR_OBLIGATORIO);
    			return false;
    		}
    		return $scope.validarTipoTarifa();
    	}
    	
    	$scope.validarTipoTarifa = function(){
    		if($scope.esPorEstrato){
    			return $scope.validarPorEstrato();
    		}else{
    			return $scope.validarSinEstrato();
    		}
    	}
    	
    	$scope.validarSinEstrato = function(){
    		if(!$scope.excesoNuevo.estrato0 || $scope.excesoNuevo.estrato0 <= 0){
    			modalFactory.abrir(CONSTANTES.ESTADO.ERROR, CONSTANTES.EXCESO.ERR_FALTA_VALOR);
    			return false;
    		}
    		return true;
    	}
    	
    	$scope.validarPorEstrato = function(){
    		if($scope.faltaEstrato()){
    			modalFactory.abrir(CONSTANTES.ESTADO.ERROR, CONSTANTES.EXCESO.ERR_FALTA_VALOR_ESTRATO);
    			return false;
    		}
    		return true;
    	}
    	
    	$scope.faltaEstrato = function(){
    		return !$scope.excesoNuevo.estrato1 || $scope.excesoNuevo.estrato1 <= 0 ||
    		!$scope.excesoNuevo.estrato2 || $scope.excesoNuevo.estrato2 <= 0 ||
    		!$scope.excesoNuevo.estrato3 || $scope.excesoNuevo.estrato3 <= 0 ||
    		!$scope.excesoNuevo.estrato4 || $scope.excesoNuevo.estrato4 <= 0 ||
    		!$scope.excesoNuevo.estrato5 || $scope.excesoNuevo.estrato5 <= 0 ||
    		!$scope.excesoNuevo.estrato6 || $scope.excesoNuevo.estrato6 <= 0;
    	}
    	
    	$scope.onEditarLimites = function(){
    		$scope.editarLimites = true;
    		$scope.basico = angular.copy($filter('filter')($scope.excesos, { descripcion : 'BASICO'})[0]);
    		$scope.complementario = angular.copy($filter('filter')($scope.excesos, { descripcion : 'COMPLEMENTARIO'})[0]);
    		$scope.suntuario = angular.copy($filter('filter')($scope.excesos, { descripcion : 'SUNTUARIO'})[0]);
    		delete($scope.basico.tipo);
			delete($scope.complementario.tipo);
			delete($scope.suntuario.tipo);
    	}
    	
    	$scope.onCancelar = function(){
    		$scope.excesoNuevo = {};
    		$scope.esNuevo = !$scope.esNuevo;
    	}
    	
    	$scope.onCancelarEdicionRango = function(){
    		$scope.editarLimites = false;
    	}
    	
    	$scope.limpiarCampos = function(){
    		$scope.init();
    	}
    	
    	$scope.onCambioLimite = function(consumo){
    		if('BASICO' === consumo){
    			$scope.complementario.limInicial = $scope.basico.limFinal + 1;
    		}
    		if('COMPLEMENTARIO' === consumo){
    			$scope.suntuario.limInicial = $scope.complementario.limFinal + 1;
    			$scope.basico.limFinal = $scope.complementario.limInicial - 1;
    		}
    		if('SUNTUARIO' === consumo){
    			$scope.complementario.limFinal = $scope.suntuario.limInicial - 1;
    		}
    		if($scope.basico.limFinal - $scope.basico.limInicial < 2){
    			$scope.basico.limFinal = $scope.basico.limInicial + 1;
    			$scope.complementario.limInicial = $scope.basico.limFinal + 1;
    		}

    		if($scope.complementario.limFinal - $scope.complementario.limInicial < 2){
    			$scope.complementario.limFinal = $scope.complementario.limInicial + 1;
    			$scope.suntuario.limInicial = $scope.complementario.limFinal + 1;
    		}
    	}
    	
    	$scope.onImprimir = function(){
    		window.print();
    	}
    	
    	$scope.init();

    }])

});