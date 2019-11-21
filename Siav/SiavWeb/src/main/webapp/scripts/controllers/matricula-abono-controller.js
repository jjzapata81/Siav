/*global define*/
'use strict';

define(['siav-module', 'pagos-services', 'usuarios-services', 'modal-factory', 'constantes'], function (app) {
	
    return app.controller('matricula-abono-controller', ['$scope', '$sce', 'pagosServices', 'usuariosServices', 'modalFactory', 'CONSTANTES', function($scope, $sce, pagosServices, usuariosServices, modalFactory, CONSTANTES){
    	
    	$scope.init = function(){
    		$scope.mostrarForm = true;
    		$scope.matricula = {};
    		$scope.matricula.nuevo = true;
    		$scope.buscarCedula = null;
    		$scope.content = null;
    	}
    	
    	$scope.onGuardar = function(){
    		if($scope.validar()){
    			delete($scope.matricula.nombreCompleto);
    			delete($scope.matricula.envioMail);
    			$scope.matricula.usuario = getData("user");
    			pagosServices
    			.abonoMatricula($scope.matricula)
    			.then(function(data){
    				var blob = new Blob([data], {type : 'application/pdf'});
    		 		var a = document.createElement('a');
    		 		a.href = (window.URL || window.webkitURL).createObjectURL(blob); 
	                a.target = '_blank';
	                a.download = 'abono.pdf';
	                document.body.appendChild(a);
	                a.click();
	                
//    				var file = new Blob([ data ], {type : 'application/pdf'});
//	                var fileURL = URL.createObjectURL(file);
//	                $scope.content = $sce.trustAsResourceUrl(fileURL);
	                $scope.limpiar();
    			});
    		}
    	}
    	
    	$scope.onBuscar = function(){
    		if($scope.buscarCedula){
    			usuariosServices
        		.buscar($scope.buscarCedula)
        		.then(function(usuario){
        			if(usuario.mensaje){
        				modalFactory.abrir(usuario.estado, usuario.mensaje);
        				$scope.init();
        			}else{
        				$scope.matricula = usuario;
        				$scope.matricula.nuevo = false;
        			}
        		});
    		}else{
    			modalFactory.abrir(CONSTANTES.ESTADO.ERROR, CONSTANTES.USUARIO.ERR_CEDULA_OBLIGATORIO);
    		}
    	}
    	
    	$scope.onCancelar = function(){
    		$scope.init();
    	}
    	
    	$scope.limpiar = function (){
    		$scope.matricula = {};
    		$scope.mostrarForm = false;
    		$scope.buscarCedula = "";
    	}
    	
    	$scope.validar = function(){
    		if(!$scope.matricula || !$scope.matricula.cedula || !$scope.matricula.apellidos || !$scope.matricula.valor){
    			modalFactory.abrir(CONSTANTES.ESTADO.ERROR, CONSTANTES.ERR.OBLIGATORIO_PENDTIENTE);
    			return false;
    		}
    		return true;
    	}
    	
    	$scope.init();
	

    }])

});