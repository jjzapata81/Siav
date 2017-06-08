/*global define*/
'use strict';

define(['siav-module', 'ramal-services', 'modal-factory', 'constantes'], function (app) {
	
    return app.controller('ramales-controller', ['$scope', 'ramalServices', 'modalFactory', 'CONSTANTES', function($scope, ramalServices, modalFactory, CONSTANTES){
    	
    	$scope.init = function(){
    		$scope.ramalNuevo = {};
    		$scope.esNuevo = false;
    		$scope.accion = null;
    		$scope.estaEditando = false;
    		$scope.consultarRamales();
    	}
    	
    	$scope.consultarRamales = function(){
    		ramalServices
    		.consultarTodo()
    		.then(function(ramales){
    			$scope.ramales = ramales;
    		});
    	}
    	
    	$scope.onAgregar = function(){
    		$scope.esNuevo = true;
    		$scope.accion = "nuevo";
    	}
    	$scope.onCancelar= function(){
    		$scope.init();
    	}
    	
    	$scope.onEditar = function (ramal){
    		$scope.estaEditando = true;
    		$scope.accion = "editar";
    		$scope.ramalNuevo = angular.copy(ramal);
    	}
    	
    	$scope.onGuardar = function(){
    		if($scope.validar()){
    			delete($scope.ramalNuevo.usuarios);
    			delete($scope.ramalNuevo.instalaciones);
    			ramalServices
    			.guardar($scope.ramalNuevo, $scope.accion)
    			.then(function(response){
    				modalFactory.abrirDialogo(response);
    				$scope.init();
    			});
    		}
    	}
    	
    	$scope.validar = function(){
    		if(!$scope.ramalNuevo || !$scope.ramalNuevo.codigoRamal){
    			modalFactory.abrir(CONSTANTES.ESTADO.ERROR, CONSTANTES.RAMAL.ERR_CODIGO_OBLIGATORIO);
    			return false;
    		}
    		if(!$scope.ramalNuevo.nombre){
    			modalFactory.abrir(CONSTANTES.ESTADO.ERROR, CONSTANTES.RAMAL.ERR_NOMBRE_OBLIGATORIO);
    			return false;
    		}
    		return true
    	}
    	
    	$scope.init();

    }])

});