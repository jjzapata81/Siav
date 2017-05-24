/*global define*/
'use strict';

/**
 * Services
 */
define(['siav-module'], function (app) {
  app.factory('reportesFactory',[function(){

	  var contrato = {
			  setReporte : setReporte,
			  getReporte : getReporte
	  }
	  
	  var reporte = [];
	  
	  function setReporte(reporte){
		  this.reporte = reporte;
	  }
	  
	  function getReporte(){
		  return this.reporte;
	  }
	  
	  return contrato;
	  
  }])
  
});