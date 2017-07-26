/*global define*/
'use strict';

define(['siav-module', 'reportes-services', 'modal-email', 'modal-factory', 'constantes'], function (app) {
	
    return app.controller('detalle-recaudo-controller', ['$scope', '$sce', 'reportesServices', 'modalEmail', 'modalFactory', 'CONSTANTES',
	  function($scope, $sce, reportesServices, modalEmail, modalFactory, CONSTANTES){

    	$scope.content = {};
    	
    	var REPORTE = "detalle-recaudo";
    	
    	$scope.onDescargar = function(){
    		if($scope.validar()){
    			reportesServices
        		.descargar(REPORTE, "detalle_recaudo_ciclo" + $scope.filtro.ciclo + ".xlsx", $scope.filtro);
    		}
    	}
    	
    	$scope.onEnviar = function(){
    		if($scope.validar()){
    			modalEmail
        		.abrir()
        		.result
        		.then(function(email){
        			console.log(email);
        			$scope.filtro.email = email;
            		reportesServices
            		.enviar(REPORTE, $scope.filtro)
            		.then(function(response){
            			modalFactory.abrir(CONSTANTES.ESTADO.OK, CONSTANTES.REPORTES.ARCHIVO_ENVIADO);
            		});
        		});
    		}
    	}
        
        $scope.onBuscarPDF = function(){
        	if($scope.validar()){
    			reportesServices
    			.getPDF(REPORTE, $scope.filtro)
    			.then(function(data){
	                var file = new Blob([ data ], {type : 'application/pdf'});
	                var fileURL = URL.createObjectURL(file);
	                $scope.content = $sce.trustAsResourceUrl(fileURL);
    			});
    		}
    	}
        
        $scope.validar = function(){
        	if(!$scope.filtro || $scope.filtro.ciclo < 1){
        		modalFactory.abrir(CONSTANTES.ESTADO.ERROR, CONSTANTES.ERR.OBLIGATORIO);
        		return false;
        	}

        	return true;
        }
        
    }])

});