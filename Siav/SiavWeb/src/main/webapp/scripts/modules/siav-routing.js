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
	        .when("/empresa", angularAMD.route({
	            templateUrl: 'views/empresa.html', 
	            controller: 'empresa-controller'
	        }))
	        .when("/estructura-acueducto", angularAMD.route({
	            templateUrl: 'views/estructura-acueducto.html', 
	            controller: 'estructura-acueducto-controller'
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
	        .when("/macromedidor", angularAMD.route({
	            templateUrl: 'views/medidor-general.html', 
	            controller: 'medidor-general-controller'
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
	        .when("/pagos-consulta", angularAMD.route({
	            templateUrl: 'views/pagos-consulta.html', 
	            controller: 'pagos-consulta-controller'
	        }))
	        .when("/inconsistencias-consulta", angularAMD.route({
	            templateUrl: 'views/inconsistencias-consulta.html', 
	            controller: 'inconsistencias-consulta-controller'
	        }))
	        .when("/pqr", angularAMD.route({
	            templateUrl: 'views/pqr.html', 
	            controller: 'pqr-controller'
	        }))
	        .when("/materiales", angularAMD.route({
	            templateUrl: 'views/materiales.html', 
	            controller: 'materiales-controller'
	        }))
	        .when("/ciclos", angularAMD.route({
	            templateUrl: 'views/ciclos.html', 
	            controller: 'ciclos-controller'
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
	        .when("/ramales", angularAMD.route({
	            templateUrl: 'views/ramales.html', 
	            controller: 'ramales-controller'
	        }))
	        .when("/configuracion-ruta", angularAMD.route({
	            templateUrl: 'views/configuracion-ruta.html', 
	            controller: 'configuracion-ruta-controller'
	        }))
	        .when("/asignacion-recorridos", angularAMD.route({
	            templateUrl: 'views/asignacion-recorridos.html', 
	            controller: 'asignacion-recorridos-controller'
	        }))
	        .when("/novedades", angularAMD.route({
	            templateUrl: 'views/novedades.html', 
	            controller: 'novedades-controller'
	        }))
	        .when("/nota-credito", angularAMD.route({
	            templateUrl: 'views/nota-credito.html', 
	            controller: 'nota-credito-controller'
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
	        .when("/matricula-abono", angularAMD.route({
	            templateUrl: 'views/matricula-abono.html', 
	            controller: 'matricula-abono-controller'
	        }))
	        .when("/prefacturacion", angularAMD.route({
	            templateUrl: 'views/prefacturacion.html', 
	            controller: 'prefacturacion-controller'
	        }))
	        .when("/factura", angularAMD.route({
	            templateUrl: 'views/factura.html', 
	            controller: 'factura-controller'
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
	        .when("/consumo-no-facturado", angularAMD.route({
	            templateUrl: 'views/reportes/consumo-no-facturado.html', 
	            controller: 'consumo-no-facturado-controller'
	        }))
	        .when("/estadisticas", angularAMD.route({
	            templateUrl: 'views/reportes/estadisticas.html', 
	            controller: 'estadisticas-controller'
	        }))
	        .when("/cartera", angularAMD.route({
	            templateUrl: 'views/reportes/cartera-total.html', 
	            controller: 'cartera-total-controller'
	        }))
	        .when("/consolidado-concepto", angularAMD.route({
	            templateUrl: 'views/reportes/consolidado-concepto.html', 
	            controller: 'consolidado-concepto-controller'
	        }))
	        .when("/detalle-recaudo", angularAMD.route({
	            templateUrl: 'views/reportes/detalle-recaudo.html', 
	            controller: 'detalle-recaudo-controller'
	        }))
	        .when("/lecturas-consumos", angularAMD.route({
	            templateUrl: 'views/reportes/lecturas-consumos.html', 
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
	        .when("/proveedores", angularAMD.route({
	            templateUrl: 'views/proveedores.html', 
	            controller: 'proveedor-controller'
	        }))
	        .when("/articulos", angularAMD.route({
	            templateUrl: 'views/articulos.html', 
	            controller: 'articulo-controller'
	        }))
	        .when("/entradas-materiales", angularAMD.route({
	            templateUrl: 'views/entradas-materiales.html', 
	            controller: 'entradas-materiales-controller'
	        }))
	        .when("/salida-materiales", angularAMD.route({
	            templateUrl: 'views/salida-materiales.html', 
	            controller: 'salida-materiales-controller'
	        }))
	        .when("/reporte-kardex", angularAMD.route({
	            templateUrl: 'views/reportes/kardex.html', 
	            controller: 'kardex-controller'
	        }))
	        .when("/reporte-ventas-matricula", angularAMD.route({
	            templateUrl: 'views/reportes/ventas-matriculas.html', 
	            controller: 'ventas-matricula-controller'
	        }))
	        .otherwise(angularAMD.route({
	        	templateUrl: 'views/login.html', 
	            controller: 'login-controller'
	        }));
    }
});
