/*global require*/
'use strict';
require.config({
	baseUrl : "scripts",
    paths: {
    	
    	//Recursos Externos
    	'angular' : 'ext/angular.min',
    	'angularAMD' : 'ext/angularAMD.min',
    	'angular-route' : 'ext/angular-route.min',
    	'angular-animate' : 'ext/angular-animate.min',
    	'jquery' : 'ext/jquery-1.11.3.min',
    	'bootstrap' : 'ext/bootstrap.min',
    	'ui-bootstrap' : 'ext/ui-bootstrap',
    	'ui-bootstrap-tpls' : 'ext/ui-bootstrap-tpls-1.3.3.min',
    	'moment' : 'ext/moment.min',
    	'util-module' : 'ext/util/util-module',
    	'angular-scroll' : 'ext/angular-scroll',
    	'ui-mask' : 'ext/ui-mask',
    	'loading-bar' : 'ext/loading-bar',
    	
    	//Recursos propios
    	'siav-module' : 'modules/siav-module',
    	'siav-routing' : 'modules/siav-routing',
    	'siav-http' : 'modules/siav-http',
    	'siav-init' : 'modules/siav-init',
    	'localstorage' : 'commons/localstorage-siav',
    	'filtro-fecha' : 'filters/filtros',
    	
    	//Servicios
    	
    	'login-services' : 'services/login-services',
    	'novedades-services' : 'services/novedades-services',
    	'creditos-services' : 'services/creditos-services',
    	'instalaciones-services' : 'services/instalaciones-services',
    	'usuarios-services' : 'services/usuarios-services',
    	'veredas-services' : 'services/veredas-services',
    	'causas-no-lectura-services' : 'services/causas-no-lectura-services',
    	'excesos-services' : 'services/excesos-services',
    	'tarifas-services' : 'services/tarifas-services',
    	'bancos-services' : 'services/bancos-services',
    	'ciclos-services' : 'services/ciclos-services',
    	'factura-services' : 'services/factura-services',
    	'contabilidad-services' : 'services/contabilidad-services',
    	'reportes-services' : 'services/reportes-services',
    	'rangos-services' :  'services/rangos-services',
    	'usuario-sistema-services' : 'services/usuario-sistema-services',
    	'clave-services' : 'services/clave-services',
    	"pagos-services" : "services/pagos-services",
    	"ramal-services" : "services/ramal-services",
    	"siav-services" : "services/siav-services",
    	"inconsistencias-services" : "services/inconsistencias-services",
    	"ruta-services" : "services/ruta-services",
    	"macros-services" : "services/macros-services",
    	"pqr-services" : "services/pqr-services",
    	"proveedor-services" : "services/proveedor-services",
    	"articulo-services" : "services/articulo-services",
    	"materiales-services" : "services/materiales-services",
    	
    	// constantes
    	'constantes' :  'constants/constantes',
    	//Interceptor
    	
    	//Factories
    	'modal-factory' : 'factories/modal-factory',
    	'modal-email' : 'factories/modal-email',
    	'modal-usuario-factory' : 'factories/modal-usuario-factory',
    	'modal-resultados-factory' : 'factories/modal-resultados-factory',
    	'reportes-factory' : 'factories/reportes-factory',
    	'factoria-menu' : 'factories/factoria-menu',
    	'modal-consumo-factory' : 'factories/modal-consumo-factory',
    	'modal-observacion' : 'factories/modal-observacion',
    	'modal-info-usuario-factory': 'factories/modal-info-usuario-factory',
    	'modal-entrada-factory' : 'factories/modal-entrada-factory',
    	
    	//Controladores
    	'menu-controller' : 'controllers/menu-controller',
    	'titulo-controller' : 'controllers/titulo-controller',
    	'principal-controller' : 'controllers/principal-controller',
    	'login-controller' : 'controllers/login-controller',
    	'contabilidad-controller' : 'controllers/contabilidad-controller',
    	'seguridad-clave-controller' : 'controllers/seguridad-clave-controller',
    	'seguridad-usuarios-controller' : 'controllers/seguridad-usuarios-controller',
    	'estructura-acueducto-controller' : 'controllers/estructura-acueducto-controller',
    	'ramales-controller' : 'controllers/ramales-controller',
    	'recuperacion-controller' : 'controllers/recuperacion-controller',
    	'instalaciones-controller' : 'controllers/instalaciones-controller',
    	'veredas-controller' : 'controllers/veredas-controller',
    	'causas-no-lectura-controller' : 'controllers/causas-no-lectura-controller',
    	'excesos-controller' : 'controllers/excesos-controller',
    	'tarifas-controller' : 'controllers/tarifas-controller',
    	'cuentas-bancarias-controller' : 'controllers/cuentas-bancarias-controller',
    	'materiales-controller' : 'controllers/materiales-controller',
    	'ciclos-controller' : 'controllers/ciclos-controller',
    	'medidor-general-controller' : 'controllers/medidor-general-controller',
    	'lecturas-digitadas-controller' : 'controllers/lecturas-digitadas-controller',
    	'inconsistencias-controller' : 'controllers/inconsistencias-controller',
    	'credito-abono-controller' : 'controllers/credito-abono-controller',
    	'configuracion-ruta-controller' : 'controllers/configuracion-ruta-controller',
    	'novedades-controller' : 'controllers/novedades-controller',
    	'creditos-controller' : 'controllers/creditos-controller',
    	'refinanciacion-controller' : 'controllers/refinanciacion-controller',
    	'abonos-controller' : 'controllers/abonos-controller',
    	'pagos-controller' : 'controllers/pagos-controller',
    	'pagos-importados-controller' : 'controllers/pagos-importados-controller',
    	'matricula-abono-controller' : 'controllers/matricula-abono-controller',
    	'prefacturacion-controller' : 'controllers/prefacturacion-controller',
    	'reliquidacion-controller' : 'controllers/reliquidacion-controller',
    	'kardex-controller' : 'controllers/kardex-controller',
    	'cierre-controller' : 'controllers/cierre-controller',
    	'usuarios-controller' : 'controllers/usuarios-controller',
    	'rangos-facturacion-controller' : 'controllers/rangos-facturacion-controller',
    	'lecturas-consumos-controller' : 'controllers/lecturas-consumos-controller',
    	'seguridad-roles-controller' : 'controllers/seguridad-roles-controller',
    	'red-controller' : 'controllers/red-controller',
    	'empresa-controller' : 'controllers/empresa-controller',
    	'nota-credito-controller' : 'controllers/nota-credito-controller',
    	'pagos-consulta-controller' : 'controllers/pagos-consulta-controller',
    	'inconsistencias-consulta-controller' : 'controllers/inconsistencias-consulta-controller',
    	'pqr-controller' : 'controllers/pqr-controller',
    	'proveedor-controller' : 'controllers/proveedor-controller',
    	'articulo-controller' : 'controllers/articulo-controller',
    	'entradas-materiales-controller' : 'controllers/entradas-materiales-controller',
    	'salida-materiales-controller' : 'controllers/salida-materiales-controller',
    	'factura-controller' : 'controllers/factura-controller',
    	
    	//Reportes
    	
    	'reporte-cuentas-vencidas-controller' : 'controllers/reportes/reporte-cuentas-vencidas-controller',
    	'reporte-ventas-controller' : 'controllers/reportes/reporte-ventas-controller',
    	'reporte-prefactura-controller' : 'controllers/reportes/reporte-prefactura-controller',
    	'reporte-factura-controller' : 'controllers/reportes/reporte-factura-controller',
    	'instalaciones-usuarios-controller' : 'controllers/reportes/instalaciones-usuarios-controller',
    	'reporte-variacion-consumo-controller' : 'controllers/reportes/reporte-variacion-consumo-controller',
    	'reporte-instalaciones-ruta-controller' : 'controllers/reportes/reporte-instalaciones-ruta-controller',
    	'consumo-no-facturado-controller' : 'controllers/reportes/consumo-no-facturado-controller',
    	'estadisticas-controller' : 'controllers/reportes/estadisticas-controller',
    	'consolidado-concepto-controller' : 'controllers/reportes/consolidado-concepto-controller',
    	'detalle-recaudo-controller' : 'controllers/reportes/detalle-recaudo-controller',
    	'cartera-controller' : 'controllers/reportes/cartera-controller',
    	//Directivas
    	
    	'directiva-menu' : 'directives/directiva-menu',
    	'directiva-titulo' : 'directives/directiva-titulo'


    },
    shim: {
    	'angular' : ['jquery'],
    	'bootstrap' : ['angular'],
    	'ui-bootstrap' : ['angular'],
    	'ui-bootstrap-tpls' : ['angular'],
    	'angularAMD' : ['angular'],
    	'angular-route' : ['angular'],
    	'angular-animate' : ['angular'],
    	'util-module' : ['angular'],
    	'angular-scroll' : ['angular'],
    	'ui-mask' : ['jquery'],
    	'loading-bar' : ['angular']
    },

    deps : ['siav-module', 'filtro-fecha', 'directiva-menu', 'directiva-titulo']

});

