/*global define*/
'use strict';

define(['siav-module', 'pagos-services', 'modal-factory', 'constantes'], function (app) {
	
    return app.controller('abonos-controller', ['$scope', '$sce', 'pagosServices', 'modalFactory', 'CONSTANTES', function($scope, $sce, pagosServices, modalFactory, CONSTANTES){
    	
    	$scope.content = null;
    	
    	$scope.init = function(){
    		$scope.limpiar();
    	}
    	
    	$scope.onBuscar = function(){
    		$scope.mostrarUsuario = false;
    		if($scope.numeroInstalacion){
    			pagosServices
        		.buscar($scope.numeroInstalacion)
        		.then(function(respuesta){
        			if(respuesta.mensaje){
        				modalFactory.abrirDialogo(respuesta);
        				$scope.numeroInstalacion = null;
        			}else{
        				$scope.mostrarUsuario = true;
        				$scope.factura = respuesta;
        			}
        		});
    		}else{
    			modalFactory.abrir(CONSTANTES.ESTADO.ERROR, CONSTANTES.INSTALACION.ERR_BUSQUEDA_OBLIGATORIO);
    		}
    	}
    	
    	$scope.onCancelar = function(){
    		$scope.init();
    	}
    	
    	$scope.onGuardar = function(){
    		if($scope.validarCampos()){
    			$scope.abono.numeroInstalacion = $scope.factura.numeroInstalacion;
    			$scope.abono.numeroFactura = $scope.factura.numeroFactura;
    			pagosServices
    			.abonar($scope.abono)
    			.then(function(data){
	                var file = new Blob([ data ], {type : 'application/pdf'});
	                var fileURL = URL.createObjectURL(file);
	                $scope.content = $sce.trustAsResourceUrl(fileURL);
	                $scope.limpiar();
    			});
    		}
    	}

    	$scope.validarCampos = function(){
    		if(!$scope.factura || !$scope.abono.valor){
    			modalFactory.abrir(CONSTANTES.ESTADO.ERROR, CONSTANTES.ERR.OBLIGATORIO);
    			return false;
    		}
    		if($scope.abono.valor > $scope.factura.valor){
    			modalFactory.abrir(CONSTANTES.ESTADO.ERROR, CONSTANTES.ABONO.ABONO_MAYOR);
    			return false;
    		}
    		return true;
    	}
    	
    	$scope.limpiar = function(){
    		$scope.mostrarUsuario = false;
    		$scope.numeroInstalacion = null;
    		$scope.abono = {};
    	}
    	
    	$scope.init();

    }])

});