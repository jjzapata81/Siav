/*global define*/
'use strict';

define(['siav-module', 'ciclos-services', 'modal-factory', 'constantes'], function (app) {
	
    return app.controller('ciclos-controller', ['$scope', 'ciclosServices',  'modalFactory', 'CONSTANTES', function($scope, ciclosServices, modalFactory, CONSTANTES){
    	
    	$scope.ciclos = [];
    	
    	$scope.init = function(){
    		$scope.estaEditando = false;
    		$scope.estaConsultando = false;
    		$scope.cargarCiclo();
    	}
    	
    	$scope.cargarCiclo = function(){
    		ciclosServices
    		.consultar()
    		.then(function(ciclo){
    			$scope.ciclo = ciclo;
    		});
    	}
    	
    	$scope.onGuardar = function(){
    		delete $scope.ciclo.nombreMes;
    		ciclosServices
    		.editar($scope.ciclo)
    		.then(function(respuesta){
    			modalFactory.abrirDialogo(respuesta);
    			$scope.init();
    		});
    	}
    	
    	$scope.onEditar = function(){
    		if($scope.ciclo.estado === CONSTANTES.CICLO.ABIERTO){
    			$scope.estaEditando = true;
    		}else{
    			$scope.estaEditando = false;
    			modalFactory.abrir(CONSTANTES.ESTADO.ERROR, CONSTANTES.CICLO.ERR_CICLO_CERRADO);
    		}
    		
    	}
    	
    	$scope.onCancelar = function(){
    		$scope.init();
    	}
    	
    	$scope.onBuscar = function(){
    		ciclosServices
    		.consultarTodo()
    		.then(function(ciclos){
    			$scope.ciclos = ciclos;
    			$scope.estaConsultando = true;
    		});
    	}
    	
    	$scope.itemsPerPage = 15;
        $scope.currentPage = 1;

        $scope.setPage = function (pageNo) {
          $scope.currentPage = pageNo;
        };

    	$scope.init();

    }])

});