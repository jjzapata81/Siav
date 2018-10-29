/*global define*/
'use strict';

define(['siav-module', 'reportes-services', 'modal-email', 'modal-factory', 'constantes'], function (app) {
	
    return app.controller('instalaciones-usuarios-controller', ['$scope', '$window', '$sce', 'reportesServices', 'modalEmail', 'modalFactory', 'CONSTANTES',
        function($scope, $window, $sce, reportesServices, modalEmail, modalFactory, CONSTANTES){

    	
    	var REPORTE = "usuarios-instalaciones";
    	
    	$scope.init = function(){
    		$scope.filtro = {};
    	}
    	
    	$scope.onBuscar = function(){
    		reportesServices
    		.instalacionesUsuario($scope.filtro)
    		.then(function(usuarios){
    			$scope.usuarios = usuarios;
    		});
    	}    	
    	
    	$scope.onDescargar = function(){
    		reportesServices
    		.descargar(REPORTE, "usuarios.xlsx", $scope.filtro);
    	}
    	
    	$scope.onEnviar = function(){
    		modalEmail
    		.abrir()
    		.result
    		.then(function(email){
    			$scope.filtro.email = email;
        		reportesServices
        		.enviar(REPORTE, $scope.filtro)
        		.then(function(response){
        			modalFactory.abrir(CONSTANTES.ESTADO.OK, CONSTANTES.REPORTES.ARCHIVO_ENVIADO);
        		});
    		});
    	}
    	
    	$scope.itemsPerPage = 10;
        $scope.currentPage = 1;

        $scope.setPage = function (pageNo) {
          $scope.currentPage = pageNo;
        };
        
        $scope.init();

    }])

});