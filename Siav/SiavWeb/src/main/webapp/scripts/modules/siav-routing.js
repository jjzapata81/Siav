/*global define*/
'use strict';

define(['angularAMD', 'angular-route'], function (angularAMD) {
	return function ($routeProvider, cfpLoadingBarProvider) {
		cfpLoadingBarProvider.includeBar = false;
		
        $routeProvider
	        .when("/inicio", angularAMD.route({
	            templateUrl: 'views/login.html', 
	            controller: 'login-controller'
	        }))
	        .when("/principal", angularAMD.route({
	            templateUrl: 'views/principal.html', 
	            controller: 'principal-controller'
	        }))
	        .when("/contabilidad", angularAMD.route({
	            templateUrl: 'views/contabilidad.html', 
	            controller: 'contabilidad-controller'
	        }))
	        .when("/rangos-facturacion", angularAMD.route({
	            templateUrl: 'views/rangos-facturacion.html', 
	            controller: 'rangos-facturacion-controller'
	        }))
	        .when("/red", angularAMD.route({
	            templateUrl: 'views/red.html', 
	            controller: 'red-controller'
	        }))
	        .when("/seguridad-clave", angularAMD.route({
	            templateUrl: 'views/seguridad-clave.html', 
	            controller: 'seguridad-clave-controller'
	        }))
	        .when("/seguridad-usuarios", angularAMD.route({
	            templateUrl: 'views/seguridad-usuarios.html', 
	            controller: 'seguridad-usuarios-controller'
	        }))
	        .when("/seguridad-roles", angularAMD.route({
	            templateUrl: 'views/seguridad-roles.html', 
	            controller: 'seguridad-roles-controller'
	        }))
	        .when("/instalaciones", angularAMD.route({
	            templateUrl: 'views/instalaciones.html', 
	            controller: 'instalaciones-controller'
	        }))
	        .when("/usuarios", angularAMD.route({
	            templateUrl: 'views/usuarios.html', 
	            controller: 'usuarios-controller'
	        }))
	        .when("/veredas", angularAMD.route({
	            templateUrl: 'views/veredas.html', 
	            controller: 'veredas-controller'
	        }))
	        .when("/causas-no-lectura", angularAMD.route({
	            templateUrl: 'views/causas-no-lectura.html', 
	            controller: 'causas-no-lectura-controller'
	        }))
	        .when("/excesos", angularAMD.route({
	            templateUrl: 'views/excesos.html', 
	            controller: 'excesos-controller'
	        }))
	        .when("/tarifas", angularAMD.route({
	            templateUrl: 'views/tarifas.html', 
	            controller: 'tarifas-controller'
	        }))
	        .when("/cuentas-bancarias", angularAMD.route({
	            templateUrl: 'views/cuentas-bancarias.html', 
	            controller: 'cuentas-bancarias-controller'
	        }))
	        .when("/materiales", angularAMD.route({
	            templateUrl: 'views/materiales.html', 
	            controller: 'materiales-controller'
	        }))
	        .when("/ciclos", angularAMD.route({
	            templateUrl: 'views/ciclos.html', 
	            controller: 'ciclos-controller'
	        }))
	        .when("/medidor-general", angularAMD.route({
	            templateUrl: 'views/medidor-general.html', 
	            controller: 'medidor-general-controller'
	        }))
	        .when("/lecturas-digitadas", angularAMD.route({
	            templateUrl: 'views/lecturas-digitadas.html', 
	            controller: 'lecturas-digitadas-controller'
	        }))
	        .when("/lecturas-importadas", angularAMD.route({
	            templateUrl: 'views/lecturas-importadas.html', 
	            controller: 'lecturas-importadas-controller'
	        }))
	        .when("/inconsistencias", angularAMD.route({
	            templateUrl: 'views/inconsistencias.html', 
	            controller: 'inconsistencias-controller'
	        }))
	        .when("/credito-abono", angularAMD.route({
	            templateUrl: 'views/credito-abono.html', 
	            controller: 'credito-abono-controller'
	        }))
	        .when("/historico", angularAMD.route({
	            templateUrl: 'views/historico.html', 
	            controller: 'historico-controller'
	        }))
	        .when("/novedades", angularAMD.route({
	            templateUrl: 'views/novedades.html', 
	            controller: 'novedades-controller'
	        }))
	        .when("/creditos", angularAMD.route({
	            templateUrl: 'views/creditos.html', 
	            controller: 'creditos-controller'
	        }))
	        .when("/refinanciacion", angularAMD.route({
	            templateUrl: 'views/refinanciacion.html', 
	            controller: 'refinanciacion-controller'
	        }))
	        .when("/abonos", angularAMD.route({
	            templateUrl: 'views/abonos.html', 
	            controller: 'abonos-controller'
	        }))
	        .when("/pagos", angularAMD.route({
	            templateUrl: 'views/pagos.html', 
	            controller: 'pagos-controller'
	        }))
	        .when("/pagos-importados", angularAMD.route({
	            templateUrl: 'views/pagos-importados.html', 
	            controller: 'pagos-importados-controller'
	        }))
	        .when("/cambio-consignacion", angularAMD.route({
	            templateUrl: 'views/cambio-consignacion.html', 
	            controller: 'cambio-consignacion-controller'
	        }))
	        .when("/prefacturacion", angularAMD.route({
	            templateUrl: 'views/prefacturacion.html', 
	            controller: 'prefacturacion-controller'
	        }))
	        .when("/prefacturacion-impresion", angularAMD.route({
	            templateUrl: 'views/prefacturacion-impresion.html', 
	            controller: 'prefacturacion-impresion-controller'
	        }))
	        .when("/reliquidacion", angularAMD.route({
	            templateUrl: 'views/reliquidacion.html', 
	            controller: 'reliquidacion-controller'
	        }))
	        .when("/kardex", angularAMD.route({
	            templateUrl: 'views/kardex.html', 
	            controller: 'kardex-controller'
	        }))
	        .when("/cierre", angularAMD.route({
	            templateUrl: 'views/cierre.html', 
	            controller: 'cierre-controller'
	        }))
	        .when("/reporte-cuentas-vencidas", angularAMD.route({
	            templateUrl: 'views/reportes/cuentas-vencidas.html', 
	            controller: 'reporte-cuentas-vencidas-controller'
	        }))
	        .when("/reporte-prefactura", angularAMD.route({
	            templateUrl: 'views/reportes/prefactura.html', 
	            controller: 'reporte-prefactura-controller'
	        }))
	        .when("/reporte-factura", angularAMD.route({
	            templateUrl: 'views/reportes/factura.html', 
	            controller: 'reporte-factura-controller'
	        }))
	        .when("/reporte-ventas", angularAMD.route({
	            templateUrl: 'views/reportes/ventas.html', 
	            controller: 'reporte-ventas-controller'
	        }))
	        .when("/lecturas-consumos", angularAMD.route({
	            templateUrl: 'views/lecturas-consumos.html', 
	            controller: 'lecturas-consumos-controller'
	        }))
	        .when("/instalaciones-usuarios", angularAMD.route({
	            templateUrl: 'views/reportes/instalaciones-usuarios.html', 
	            controller: 'instalaciones-usuarios-controller'
	        }))
	        .when("/variacion-consumo", angularAMD.route({
	            templateUrl: 'views/reportes/variacion-consumo.html', 
	            controller: 'reporte-variacion-consumo-controller'
	        }))
	        .when("/instalaciones-ruta", angularAMD.route({
	            templateUrl: 'views/reportes/instalaciones-ruta.html', 
	            controller: 'reporte-instalaciones-ruta-controller'
	        }))
	        .otherwise(angularAMD.route({
	        	templateUrl: 'views/login.html', 
	            controller: 'login-controller'
	        }));
    }
});
