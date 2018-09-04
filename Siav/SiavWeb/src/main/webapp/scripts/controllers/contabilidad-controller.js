/*global define*/
'use strict';

define(['siav-module', 'contabilidad-services', 'modal-factory', 'constantes'], function (app) {
	
    return app.controller('contabilidad-controller', ['$scope', 'contabilidadServices', 'modalFactory', 'CONSTANTES', function($scope, contabilidadServices, modalFactory, CONSTANTES){
    	
    	$scope.init = function(){
    		$scope.valoresIniciales();
    		$scope.consultar();
    		$scope.noEstaEditando = true;
    	}
    	
    	$scope.valoresIniciales = function(){
        	$scope.contabilidad = {};
        	$scope.mostrarPorcentaje = false;
    	}
    	
    	$scope.consultar = function(){
    		contabilidadServices
    		.consultar()
    		.then(function(sistema){
    			$scope.contabilidad = sistema;
    			$scope.contabilidad.tieneTope = $scope.toString($scope.contabilidad.tieneTope);
    			$scope.contabilidad.esPorRango = $scope.toString($scope.contabilidad.esPorRango);
    			$scope.contabilidad.esPorEstrato = $scope.toString($scope.contabilidad.esPorEstrato);
    			$scope.esPorEstratoTemp = angular.copy($scope.contabilidad.esPorEstrato);
    		});
    	}
    	
    	$scope.seleccionTipoRecargo = function(){
    		$scope.mostrarPorcentaje = $scope.contabilidad.esRecargoFijo == "Si";
    	}
    	
    	$scope.onEditar = function(){
    		$scope.noEstaEditando = false;
    		$scope.contabilidadTemp = angular.copy($scope.contabilidad);
    	}

		$scope.onCancelar = function(){
			$scope.noEstaEditando = true;
			$scope.contabilidad = $scope.contabilidadTemp;
			$scope.seleccionTipoRecargo();
		}
    	
    	$scope.onGuardar = function(){
    		if($scope.esPorEstratoTemp != $scope.contabilidad.esPorEstrato){
    			modalFactory.abrir(CONSTANTES.ESTADO.INFO, CONSTANTES.SISTEMA.INFO_MODIFICAR_ESTRATO)
    			.result
    			.then(function(resultado) {
        			if(resultado){
        				$scope.continuarGuardar();
        			}
    			});
    		}else{
    			$scope.continuarGuardar();
    		}
    	}
    	
    	$scope.continuarGuardar = function(){
    		if($scope.validar()){
    			$scope.contabilidad.tieneTope = $scope.toBoolean($scope.contabilidad.tieneTope);
    			$scope.contabilidad.esPorRango = $scope.toBoolean($scope.contabilidad.esPorRango);
    			$scope.contabilidad.esPorEstrato = $scope.toBoolean($scope.contabilidad.esPorEstrato);
    			contabilidadServices
    			.guardar($scope.contabilidad)
    			.then(function(response){
    				modalFactory.abrirDialogo(response);
    				$scope.init();
    			});
    		}
    	}
    	
    	$scope.validar = function(){

    		if ($scope.contabilidad.consumoMinimo < 0){
    			modalFactory.abrir(CONSTANTES.ESTADO.ERROR, CONSTANTES.ERR.CONSUMO_MINIMO_NEGATIVO);
    			return false;
    		}
    		if(!$scope.contabilidad.idCargoFijo){
    			modalFactory.abrir(CONSTANTES.ESTADO.ERROR, CONSTANTES.ERR.CARGO_FIJO_OBLIGATORIO);
    			return false;
    		}
    		if(!$scope.contabilidad.idBasico){
    			modalFactory.abrir(CONSTANTES.ESTADO.ERROR, CONSTANTES.ERR.BASICO_OBLIGATORIO);
    			return false;
    		}
    		if(!$scope.contabilidad.idComplementario){
    			modalFactory.abrir(CONSTANTES.ESTADO.ERROR, CONSTANTES.ERR.COMPLEMENTARIO_OBLIGATORIO);
    			return false;
    		}
    		if(!$scope.contabilidad.idSuntuario){
    			modalFactory.abrir(CONSTANTES.ESTADO.ERROR, CONSTANTES.ERR.SUNTUARIO_OBLIGATORIO);
    			return false;
    		}
    		if(!$scope.contabilidad.idReconexion){
    			modalFactory.abrir(CONSTANTES.ESTADO.ERROR, CONSTANTES.ERR.RECONEXION_OBLIGATORIO);
    			return false;
    		}
    		if(!$scope.contabilidad.idRecargo){
    			modalFactory.abrir(CONSTANTES.ESTADO.ERROR, CONSTANTES.ERR.RECARGO_OBLIGATORIO);
    			return false;
    		}
    		if(!$scope.contabilidad.idMoroso){
    			modalFactory.abrir(CONSTANTES.ESTADO.ERROR, CONSTANTES.ERR.MOROSO_OBLIGATORIO);
    			return false;
    		}
    		if(!$scope.contabilidad.idDerecho){
    			modalFactory.abrir(CONSTANTES.ESTADO.ERROR, CONSTANTES.ERR.DERECHO_OBLIGATORIO);
    			return false;
    		}
    		if(!$scope.contabilidad.idInteres){
    			modalFactory.abrir(CONSTANTES.ESTADO.ERROR, CONSTANTES.ERR.INTERES_OBLIGATORIO);
    			return false;
    		}
    		if(!$scope.contabilidad.epsilon){
    			modalFactory.abrir(CONSTANTES.ESTADO.ERROR, CONSTANTES.ERR.EPSILON_OBLIGATORIO);
    			return false;
    		}
    		if($scope.contabilidad.epsilon <0){
    			modalFactory.abrir(CONSTANTES.ESTADO.ERROR, CONSTANTES.ERR.EPSILON_NEGATIVO);
    			return false;
    		}
    		if($scope.contabilidad.cuentasCorte <0){
    			modalFactory.abrir(CONSTANTES.ESTADO.ERROR, CONSTANTES.ERR.CUENTAS_CORTE_NEGATIVO);
    			return false;
    		}

    		if($scope.contabilidad.cuentasVencidas <0){
    			modalFactory.abrir(CONSTANTES.ESTADO.ERROR, CONSTANTES.ERR.CUENTAS_VENCIDAS_NEGATIVO);
    			return false;
    		}
    		if(!$scope.contabilidad.tope){
    			modalFactory.abrir(CONSTANTES.ESTADO.ERROR, CONSTANTES.ERR.TOPE_OBLIGATORIO);
    			return false;
    		}
    		if($scope.contabilidad.tope <0){
    			modalFactory.abrir(CONSTANTES.ESTADO.ERROR, CONSTANTES.ERR.TOPE_NEGATIVO);
    			return false;
    		}
    		return true;
    	}
    	
    	$scope.toBoolean = function(valor){
    		return "Si" == valor;
    	}
    	
    	$scope.toString = function(valor){
    		return valor ? "Si" : "No";
    	}
    	
    	$scope.init();

    }])

});