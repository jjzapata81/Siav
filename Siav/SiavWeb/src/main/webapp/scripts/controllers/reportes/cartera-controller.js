/*global define*/
'use strict';

define(['siav-module', 'reportes-services', 'modal-email', 'modal-factory', 'constantes'], function (app) {
	
    return app.controller('cartera-controller', ['$scope', '$sce', 'reportesServices', 'modalEmail', 'modalFactory', 'CONSTANTES',
	  function($scope, $sce, reportesServices, modalEmail, modalFactory, CONSTANTES){

    	$scope.content = null;
    	
    	$scope.filtro = {};
    	
    	var REPORTE = "cartera";
    	
    	$scope.onDescargar = function(){
			reportesServices
    		.descargar(REPORTE, "cartera_" + $scope.filtro.ciclo + ".xlsx", $scope.filtro);
    	}
    	
    	$scope.onEnviar = function(){
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
        
        $scope.onBuscarPDF = function(){
			reportesServices
			.getPDF(REPORTE, $scope.filtro)
			.then(function(data){
                var file = new Blob([ data ], {type : 'application/pdf'});
                var fileURL = URL.createObjectURL(file);
                $scope.content = $sce.trustAsResourceUrl(fileURL);
			});
    	}
        
    }])

});