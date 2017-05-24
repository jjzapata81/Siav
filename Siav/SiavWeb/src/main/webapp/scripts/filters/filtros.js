/*global define*/
'use strict';

define(['siav-module', 'moment'], function (app, moment) {
    app.filter('filtroFecha', function() {
    	return function(items, fechaDate, campo) {
    		var itemsFiltrados = [];
    		if (!fechaDate) { return items; }
        	angular.forEach(items, function (item){
        		if(diferenciaFechasEnDias(item[campo], fechaDate) == 0){
        			itemsFiltrados.push(item);
        		}
        	});
        	return itemsFiltrados;
    	}
    });
    
    app.filter('startFrom', function() {
        return function(input, start) {
            start = +start; //parse to int
            return input.slice(start);
        }
    });
    
    var diferenciaFechasEnDias = function(fecha1, fecha2){
        return moment(fecha1).diff(moment(fecha2), 'days');
    };
    
});