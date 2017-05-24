/*global define*/
'use strict';

/**
 * Services
 */
define(['siav-module'], function (app) {
  app.factory('menuFactory',[function(){

	  var contrato = {
			  getMenu : getMenu,
			  setMenu : setMenu
	  }
	  
	  var menu = [];
	  
	  function getMenu(){
		  return this.menu;
	  }
	
	  function setMenu(menu){
		  this.menu = menu;
	  }
	  
	  return contrato;
	  
  }])
  
});