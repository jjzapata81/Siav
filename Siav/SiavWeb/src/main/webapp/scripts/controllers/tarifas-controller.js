/*global define*/
'use strict';

define(['siav-module', 'tarifas-services', 'contabilidad-services', 'instalaciones-services','modal-factory', 'constantes'], function (app) {
	
    return app.controller('tarifas-controller', ['$scope', '$filter', 'tarifasServices', 'contabilidadServices', 'instalacionesServices', 'modalFactory', 'CONSTANTES', function($scope, $filter, tarifasServices, contabilidadServices, instalacionesServices, modalFactory, CONSTANTES){

    	$scope.init = function(){
    		$scope.esNuevo = false;
    		$scope.esEdicion = false;
    		$scope.tarifaNueva = {};
    		$scope.cargarTiposTarifas();
    		$scope.cargarDescripcionesTarifas();
    		$scope.tarifas = [];
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
    	
    	$scope.cargarTiposTarifas = function(){
    		instalacionesServices
    		.consultarMaestro('TIPO_TARIFA')
    		.then(function(tiposTarifas){
    			$scope.tiposTarifas =  tiposTarifas;
    			$scope.cargarTarifas();
    		});
    	}
    	
    	$scope.cargarTarifas = function(){
    		tarifasServices
    		.consultar()
    		.then(function(tarifas){
    			$scope.tarifas = tarifas;
    			angular.forEach($scope.tarifas, function(value, key) {
    				  value.tipo = $filter('filter')($scope.tiposTarifas, { codigo : value.tipo })[0];
    			});
    		});
    	}
    	
    	$scope.cargarDescripcionesTarifas = function(){
    		tarifasServices
    		.consultarDescripciones()
    		.then(function(descripciones){
    			$scope.descripciones = descripciones;
    		});
    	}
    	
    	$scope.onAgregar = function(){
    		$scope.esNuevo = !$scope.esNuevo;
    	}
    	
    	$scope.onCancelar = function(){
    		$scope.tarifaNueva = {};
    		$scope.esEdicion = false;
    		$scope.esNuevo = !$scope.esNuevo;
    	}
    	
    	$scope.onEditar = function(tarifa){
    		$scope.esNuevo = true;
    		$scope.esEdicion = true;
    		$scope.tarifaNueva = angular.copy(tarifa);
    	}
    	
    	$scope.onGuardar = function(){
    		if($scope.camposValidos()){
    			if($scope.esEdicion){
    				$scope.onActualizar();
    			}else{
    				$scope.onCrear();
    			}
    		}
    	}
    	
    	$scope.onActualizar = function(){
    		modalFactory
    		.abrir(CONSTANTES.ESTADO.INFO, CONSTANTES.TARIFA.INFO_MODIFICAR)
    		.result
    		.then(function(respuesta){
    			$scope.tarifaNueva.tipo = $scope.tarifaNueva.tipo.codigo;
    			tarifasServices
        		.editar($scope.tarifaNueva)
        		.then(function(respuesta){
        			modalFactory.abrirDialogo(respuesta);
        			$scope.limpiarCampos();
        		});
    		});
    	}
    	
    	$scope.onCrear = function(){
    		$scope.tarifaNueva.tipo = $scope.tarifaNueva.tipo.codigo;
    		$scope.tarifaNueva.esCredito = $scope.tarifaNueva.esCredito ? true : false;
    		$scope.tarifaNueva.activo = $scope.tarifaNueva.activo ? true : false;
    		tarifasServices
    		.crear($scope.tarifaNueva)
    		.then(function(respuesta){
    			modalFactory.abrirDialogo(respuesta);
    			$scope.limpiarCampos();
    		});
    	}
    	
    	$scope.camposValidos = function(){
    		if(!$scope.tarifaNueva.codigo || !$scope.tarifaNueva.descripcion){
    			modalFactory.abrir(CONSTANTES.ESTADO.ERROR, CONSTANTES.TARIFA.ERR_OBLIGATORIO);
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
    		if($scope.tarifaNueva.tipo.valor === 'FIJO' && (!$scope.tarifaNueva.estrato0 || $scope.tarifaNueva.estrato0 <= 0)){
    			modalFactory.abrir(CONSTANTES.ESTADO.ERROR, CONSTANTES.TARIFA.ERR_FALTA_VALOR);
    			return false;
    		}
    		return true;
    	}
    	
    	$scope.validarPorEstrato = function(){
    		if($scope.tarifaNueva.tipo === 'FIJO' && $scope.faltaEstrato()){
    			modalFactory.abrir(CONSTANTES.ESTADO.ERROR, CONSTANTES.TARIFA.ERR_FALTA_VALOR_ESTRATO);
    			return false;
    		}
    		return true;
    	}
    	
    	$scope.faltaEstrato = function(){
    		return !$scope.tarifaNueva.estrato1 || $scope.tarifaNueva.estrato1 <= 0 ||
    		!$scope.tarifaNueva.estrato2 || $scope.tarifaNueva.estrato2 <= 0 ||
    		!$scope.tarifaNueva.estrato3 || $scope.tarifaNueva.estrato3 <= 0 ||
    		!$scope.tarifaNueva.estrato4 || $scope.tarifaNueva.estrato4 <= 0 ||
    		!$scope.tarifaNueva.estrato5 || $scope.tarifaNueva.estrato5 <= 0 ||
    		!$scope.tarifaNueva.estrato6 || $scope.tarifaNueva.estrato6 <= 0;
    	}
    	
    	$scope.limpiarCampos = function(){
    		$scope.init();
    	}
    	
    	$scope.onImprimir = function(){
    		window.print();
    	}
    	
    	$scope.itemsPerPage = 10;
        $scope.currentPage = 1;

        $scope.setPage = function (pageNo) {
          $scope.currentPage = pageNo;
        };
    	
    	$scope.init();

    }])

});