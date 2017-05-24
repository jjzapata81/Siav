define(['siav-module'], function (app) {
	(function(localStorage){
		
		this.addData = function(key, value) {
			localStorage[key] = value;
		}
		
		this.getData = function(key){
			return localStorage[key];
		}
		
		
		this.removeData = function(key){
			localStorage.removeItem(key);
		}
		
		this.focus = function(element){
			angular.element(element).focus();
		}
	})(localStorage);

});