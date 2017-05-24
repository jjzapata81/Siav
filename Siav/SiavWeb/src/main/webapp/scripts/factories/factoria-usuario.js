/*global define*/
'use strict';

/**
 * Services
 */
define(['siav-module'], function (app) {
  app.factory('usuarioFactory',[function(){

	  var contrato = {
			  setInstalacion : setInstalacion,
			  getInstalacion : getInstalacion,
			  deleteInstalacion : deleteInstalacion,
			  isUsuario : isUsuario,
			  existeUsuario : existeUsuario
	  }
	  
	  var instalacion = {};
	  var hayUsuario = false;
	  
	  function setInstalacion(instalacion){
		  this.instalacion = instalacion;
	  }
	  
	  function getInstalacion(){
		  return angular.copy(this.instalacion);
	  }
	
	  function deleteInstalacion(){
		  this.instalacion = null;
	  }
	  
	  function isUsuario(hayUsuario){
		  this.hayUsuario = hayUsuario;
	  }
	  
	  function existeUsuario(){
		  return this.hayUsuario;
	  }
	  
	  return contrato;
	  
  }])
  
});