/*global define*/
'use strict';

/**
 * Services
 */
define(['siav-module'], function (app) {
  app.factory('factoriaFacturacion',[function(){

	  var contrato = {
			  setPrefactura : setPrefactura,
			  getPrefactura : getPrefactura
	  }
	  
	  var prefactura = [];
	  
	  function setPrefactura(prefactura){
		  this.prefactura = prefactura;
	  }
	  
	  function getPrefactura(){
		  console.log("Desde factoria: ", this.prefactura);
		  return this.prefactura;
	  }
	  
	  return contrato;
	  
  }])
  
});