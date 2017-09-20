/*global define*/
'use strict';

/**
 * Services
 */
define(['siav-module'], function (app) {
  app.factory('menuFactory',[function(){

	  var contrato = {
			  getMenu : getMenu,
			  setMenu : setMenu,
			  getUsuario : getUsuario,
			  setUsuario : setUsuario
	  }
	  
	  var menu = [];
	  var usuario = "";
	  
	  function getMenu(){
		  return this.menu;
	  }
	
	  function setMenu(menu){
		  this.menu = menu;
	  }
	  
	  function getUsuario(){
		  return this.usuario;
	  }
	  
	  function setUsuario(usuario){
		  this.usuario = usuario;
	  }
	  
	  return contrato;
	  
  }])
  
});