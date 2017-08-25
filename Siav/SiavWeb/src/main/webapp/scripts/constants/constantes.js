/*global define*/
'use strict';

define(['siav-module'], function(app) {
    app.constant("CONSTANTES", {
    	
    	"SRV" : {
    		
    		"ARTICULO_CONSULTAR" : "rest/articulo/consultar",
    		"ARTICULO_CONSULTAR_NOMBRES" : "rest/articulo/consultar/nombres",
    		"ARTICULO_CONSULTAR_CREAR" : "rest/articulo/crear",
    		"ARTICULO_CONSULTAR_ACTUALIZAR" : "rest/articulo/actualizar",

    		"BANCO_CREAR" : "rest/bancos/crear",
    		"BANCO_EDITAR" : "rest/bancos/editar",
    		"BANCO_CONSULTAR" : "rest/bancos/consultar",
    		"BANCO_CONSULTAR_CUENTAS" : "rest/bancos/consultar/cuentas",
    		
    		"CAUSA_NO_LECTURA_CREAR" : "rest/nolectura/crear",
    		"CAUSA_NO_LECTURA_ACTIVAR" : "rest/nolectura/activar",
    		"CAUSA_NO_LECTURA_CONSULTAR" : "rest/nolectura/consultar",
    		"CAUSA_NO_LECTURA_CONSULTAR_DESCRIPCIONES" : "rest/nolectura/consultar/descripciones",

    		"CICLO_CONSULTAR" : "rest/ciclos/consultar",
    		"CICLO_CONSULTAR_TODO" : "rest/ciclos/consultar/todo",
    		"CICLO_EDITAR" : "rest/ciclos/editar",
    		"CICLO_CERRAR" : "rest/ciclos/cerrar/",
    		
    		"CONSUMOS_INCOMPLETOS" : "rest/consumos/consultar/incompletos",
    		"CONSUMOS_INCOMPLETOS_LOG" : "rest/consumos/consultar/log",
    		"CONSUMOS_INCOMPLETOS_GUARDAR" : "rest/consumos/guardar",
    		"CONSUMOS_RANGO" : "rest/consumos/consultar/rango",
    		"CONSUMOS_RIESGO_GUARDAR" : "rest/consumos/riesgo/guardar",
    		"CONSUMOS_RIESGO_GUARDAR_CORRECCION_CONSUMO" : "rest/consumos/correccion/guardar",
    		
    		"CONTABILIDAD_CONSULTAR" : "rest/contabilidad/consultar",
    		"CONTABILIDAD_GUARDAR" : "rest/contabilidad/guardar",
    		
    		"CREDITO_BUSCAR" : "rest/creditos/buscar/",
    		"CREDITO_GUARDAR" : "rest/creditos/guardar",
    		"CREDITO_REFINANCIAR" : "rest/creditos/refinanciar",
    		"CREDITO_ELIMINAR" : "rest/creditos/eliminar",
    		
    		"EXCESO_EDITAR_RANGO" : "rest/excesos/editar/rango",
    		"EXCESO_GUARDAR" : "rest/excesos/guardar",
    		"EXCESO_CONSULTAR" : "rest/excesos/consultar",
    		
    		"FACTURACION_INICIAR" : "rest/facturacion/iniciar",
    		
    		"INSTALACION_BUSCAR" : "rest/instalacion/buscar/",
    		"INSTALACION_GUARDAR" : "rest/instalacion/guardar",
    		"INSTALACION_CREAR" : "rest/instalacion/crear",
    		"INSTALACION_CONSULTAR_VENCIDO" : "rest/instalacion/consultar/vencido/",
    		
    		"LOGIN_INGRESAR" : "rest/login/ingresar",
    		"LOGIN_PERFIL_CONSULTAR" : "rest/login/perfiles/consultar",
    		
    		"MACROS_CONSULTAR" : "rest/macros/consultar",
    		"MACROS_GUARDAR" : "rest/macros/",
    		
    		"MAESTROS_CONSULTAR" : "rest/listas/maestros/",
    		
    		"NOVEDAD_GUARDAR" : "rest/novedades/guardar",
    		"NOVEDAD_CONSULTAR" : "rest/novedades/consultar/",
    		"NOVEDAD_ELIMINAR" : "rest/novedades/eliminar",
    		"NOVEDAD_CONSULTAR_NOTA_CREDITO" : "rest/novedades/consultar/nota-credito/",
    		"NOVEDAD_GUARDAR_NOTA_CREDITO" : "rest/novedades/guardar/nota-credito",
    		
    		"ORDEN_RUTASCONSULTAR" : "rest/ruta/consultar",
    		"ORDEN_RUTAS_GUARDAR" : "rest/ruta/guardar",
    		
    		"PAGO_GUARDAR" : "rest/pagos/guardar",
    		"PAGO_BUSCAR" : "rest/pagos/buscar/",
    		"PAGO_CONSULTAR" : "rest/pagos/consultar",
    		"PAGO_CARGAR" : "rest/pagos/cargar/",
    		"PAGO_ABONAR" : "/Reportes/rest/general/abono/pdf",
    		"PAGO_ABONO_MATRICULA" : "/Reportes/rest/general/abono/matricula/pdf",
    		
    		"PQR_CONSULTAR" : "rest/pqr/consultar",
    		"PQR_CONSULTAR_DETALLE" : "rest/pqr/consultar/detalle",
    		"PQR_CREAR" : "rest/pqr/crear",
    		"PQR_ACTUALIZAR" : "rest/pqr/actualizar",
    		
    		"PROVEEDOR_CONSULTAR" : "rest/proveedor/consultar",
    		"PROVEEDOR_CONSULTAR_NOMBRES" : "rest/proveedor/consultar/nombres",
    		"PROVEEDOR_CONSULTAR_CREAR" : "rest/proveedor/crear",
    		"PROVEEDOR_CONSULTAR_ACTUALIZAR" : "rest/proveedor/actualizar",
    		
    		
    		"RAMAL_CONSULTAR_TODO" : "rest/ramal/consultar/todo",
    		"RAMAL_CONSULTAR" : "rest/ramal/consultar",
    		"RAMAL_GUARDAR" : "rest/ramal/guardar/",
    		
    		"RANGO_CREAR" : "rest/rangos/crear",
    		"RANGO_ELIMINAR" : "rest/rangos/eliminar",
    		"RANGO_CONSULTAR" : "rest/rangos/consultar",
    		
    		"REPORTES_DESCARGAR" : "/Reportes/rest/reports/",
    		"REPORTES_ENVIAR" : "/Reportes/rest/reports/",
    		"REPORTES_PDF" : "/Reportes/rest/reports/",
    		"REPORTES_BUSCAR" : "rest/reportes/buscar/",
    		"REPORTES_INSTALACIONES_USUARIO" : "/Reportes/rest/general/usuario/instalaciones",
    		
    		"SISTEMA_CONSULTAR_IP":"rest/general/consultar/ip",
    		"SISTEMA_CONSULTAR_ESTRUCTURA" : "rest/general/consultar/estructura",
    		"SISTEMA_CONSULTAR_EMPRESA" : "rest/general/consultar/empresa",
    		"SISTEMA_ACTUALIZAR_EMPRESA" : "rest/general/actualizar/empresa",
    		"SISTEMA_AGREGAR_JUNTA" : "rest/general/agregar/junta",
    		"SISTEMA_ACTUALIZAR_JUNTA" : "rest/general/actualizar/junta",
    		
    		"SEGURIDAD_CLAVE_CAMBIAR" : "rest/seguridad/clave/cambiar",
    		"SEGURIDAD_USUARIO_CONSULTAR" : "rest/seguridad/usuario/consultar",
    		"SEGURIDAD_USUARIO_ESTADO" : "rest/seguridad/estado",
    		"SEGURIDAD_USUARIO_ACTUALIZAR" : "rest/seguridad/usuario/actualizar",
    		"SEGURIDAD_USUARIO_CREAR" : "rest/seguridad/usuario/crear",
    		
    		"TARIFA_CREAR" : "rest/tarifas/crear",
    		"TARIFA_EDITAR" : "rest/tarifas/editar",
    		"TARIFA_CONSULTAR" : "rest/tarifas/consultar",
    		"TARIFA_CONSULTAR_CREDITO" : "rest/tarifas/consultar/credito",
    		"TARIFA_CONSULTAR_DESCRIPCION" : "rest/tarifas/consultar/descripciones",
    		"TARIFA_BUSCAR" : "rest/tarifas/buscar/",
    		
    		"USUARIOS_BUSCAR" : "rest/usuario/buscar/",
    		"USUARIOS_BUSCAR_POR_NOMBRE" : "rest/usuario/buscar/nombre",
    		"USUARIOS_BUSCAR_INFO" : "rest/usuario/buscar/info/",
    		"USUARIOS_GUARDAR" : "rest/usuario/guardar",
    		"USUARIOS_ACTUALIZAR" : "rest/usuario/actualizar",
    		
    		"VEREDA_CONSULTAR_NOMBRES" : "rest/veredas/consultar/nombres",
    		"VEREDA_CONSULTAR_TODO" : "rest/veredas/consultar/todo",
    		"VEREDA_CONSULTAR" : "rest/veredas/consultar",
    		"VEREDA_CREAR" : "rest/veredas/crear"
    		
    	},
    	"MSG" : {
    		"CAMBIO_GUARDADO" : "Los cambios han sido guardados de manera correcta.",
    		"CONFIRMAR_ELIMINAR_RANGO" : "Se procede a eliminar un rango de facturación. ¿Desea continuar?"
    	},
    	"ERR" : {
    		"CONSUMO_MINIMO_NEGATIVO" : "El valor del consumo mínimo debe ser positivo.",
    		"CONSUMO_MINIMO_OBLIGATORIO" : "Ingrese un valor para el consumo mínimo.",
    		"CARGO_FIJO_OBLIGATORIO" : "Ingrese un valor para el concepto cargo fijo.",
    		"BASICO_OBLIGATORIO" : "Ingrese un valor para el concepto consumo básico.",
    		"COMPLEMENTARIO_OBLIGATORIO" : "Ingrese un valor para el concepto consumo complementario.",
    		"SUNTUARIO_OBLIGATORIO" : "Ingrese un valor para el concepto consumo suntuario.",
    		"RECONEXION_OBLIGATORIO" : "Ingrese un valor para el concepto reconexión.",
    		"RECARGO_OBLIGATORIO" : "Ingrese un valor para el concepto recargo.",
    		"MOROSO_OBLIGATORIO" : "Ingrese un valor para el concepto moroso.",
    		"DERECHO_OBLIGATORIO" : "Ingrese un valor para el concepto de matrícula.",
    		"INTERES_OBLIGATORIO" : "Ingrese un valor para el concepto interés.",
    		"EPSILON_OBLIGATORIO" : "Ingrese un valor para el margen de error para rotación completa de medidor.",
    		"EPSILON_NEGATIVO" : "El valor del margen de error para rotación completa de medidor debe ser positivo.",
    		"CUENTAS_VENCIDAS_OBLIGATORIO" : "Ingrese un valor para el número de cuentas vencidas.",
    		"CUENTAS_VENCIDAS_NEGATIVO" : "El valor del número de cuentas vencidas debe ser positivo.",
    		"TOPE_OBLIGATORIO" : "Ingrese un valor para el techo.",
    		"TOPE_NEGATIVO" : "El valor del techo debe ser positivo.",
    		"OBLIGATORIO" : "Todos los campos son obligatorios.",
    		"CICLO_OBLIGATORIO" : "Debe ingresar un número de ciclo.",
    		"OBLIGATORIO_PENDTIENTE": "Existen campos obligatorios sin diligenciar."
    	},
    	"ABONO" : {
    		"ABONO_MAYOR" : "El valor del abono no debe ser mayor al valor de la factura." 
    	},
    	"ACCION" : {
    		"EDITAR" : "EDITAR",
    		"CREAR" : "CREAR",
    		"MODIFICAR_PROPIETARIO" : "Modificar propietario",
    		"AGREGAR_PROPIETARIO" : "Agregar propietario"
    	},
    	"BANCO" : {
    		"INFO_MODIFICAR_CUENTA" : "Se procede a modificar la cuenta. ¿Desea continuar?",
    		"ERR_OBLIGATORIO" : "Los campos de nombre y número de cuenta son obligatorios."
    	},
    	"CAUSAS" : {
    		"ERR_OBLIGATORIO" : "Debe ingresar la descripción de la causa."
    	},
    	"CICLO" : {
    		"ABIERTO" : "ABIERTO",
    		"ERR_CICLO_CERRADO" : "No se puede editar un ciclo cerrado."
    	},
    	"CIERRE" : {
    		"CONFIRMAR_CIERRE" : "Se procede a cerrar el ciclo ",
    		"CONFIRMAR_CIERRE_COMPLEMENTO" : ". ¿Desea continuar?",
    		"ERR_SIN_FECHA" : "No se puede cerra un ciclo sin fechas."
    	},
    	"CONFIGURACION_RUTA" : {
    		"ERR_INSTALACION_OBLIGATORIO" : "Debe ingresar el número de instalación que antecede la instalación actual.",
    		"ERR_CAMBIO_RAMAL" : "Para hacer un cambio de ruta debe indicar la nueva posición de la instalación."
    	},
    	"CREDITO" : {
    		"GUARDAR_EXITO" : "El crédito se ha registrado de manera exitosa.",
    		"ERR_VALOR_OBLIGATORIO" : "El valor del crédito es obligatorio.",
    		"ERR_NUMERO_CUOTAS_OBLIGATORIO" : "El número de cuotas es obligatorio.",
    		"ERR_INSTALACION_OBLIGATORIO" : "El número de la instalación es obligatorio.",
    		"ERR_TARIFA_OBLIGATORIO" : "Debe seleccionar un concepto para el crédito.",
    		"ERR_NEGATIVO" : "No se pueden ingresar valores negativos.",
    		"ERR_VALOR_SUPERIOR" : "El valor ingresado no debe ser superior al saldo del crédito.",
    		"ERR_COMPROBANTE_OBLIGATORIO" : "Debe ingresar un número de comprobante de pago."
    	},
    	"ESTADO" : {
    		"OK" : "OK",
    		"ERROR" : "ERROR",
    		"INFO" : "INFO"
    	},
    	"ESTRUCTURA" :{
    		"ERR_USUARIO" : "No se ha seleccionado un usuario.",
    		"ERR_ACTA" : "Debe ingresar el acta de nombramiento.",
    		"ERR_CARGO" : "Debe seleccionar un cargo.",
    		"INFO_MODIFICAR_ESTADO" : "Se procede a deshabilitar un miembro de la junta. ¿Desea continuar?"
    	},
    	"EXCESO" : {
    		"INFO_EDITAR_RANGO" : "La modificación de los límites puede afectar todos los consumos. ¿Desea continuar?",
    		"ERR_OBLIGATORIO" : "Los campos de código y descripción son obligatorios.",
    		"ERR_FALTA_VALOR_ESTRATO" : "Debe indicar los valores para cada uno de los estratos.",
    		"ERR_FALTA_VALOR" : "Debe indicar el valor del consumo."
    	},
    	"INCONSISTENCIA" : {
    		"ERR_LECTURA_FINAL_MENOR" : "La lectura final no puede ser menor que la lectura anterior",
    		"ERR_SERIAL_OBLIGATORIO" : "Ingrese un número de serial para el nuevo medidor",
    		"ERR_LECTURA_CORRECCION" : "Ingrese un valor para la lectura corregida"
    	},
    	"INSTALACION" : {
    		"ERR_BUSQUEDA_OBLIGATORIO" : "Ingrese un número de instalación.",
    		"ERR_USUARIO_NO_EXISTE" : "No existen usuarios con la cédula ingresada."
    	},
    	"LOGIN" : {
    		"ERR_OBLIGATORIO" : "Debe ingresar los datos de Usuario y Contraseña."
    	},
    	"MACROS" :{
    		"ERR_MACRO_NOMBRE" : "Debe ingresar el nombre del macromedidor."
    	},
    	"NOVEDAD" : {
    		"CONFIRMAR_ELIMINAR" : "Se procede a eliminar una novedad. ¿Desea continuar?"
    	},
    	"PAGO" : {
    		"NO_CANCELADO" : "Ha seleccionado pago no cancelado. ¿Está seguro que desea continuar?",
    		"ERR_EXTENSION" : "Sólo se permiten cargar archivos con extensión .csv",
    		"ERR_NUMERO_CUENTA" : "Debe seleccionar una cuenta bancaria para asociar el pago."
    	},
    	"PROVEEDOR" : {
    		"ERR_NIT" : "El Nit es obligatorio.",
    		"ERR_RAZON_SOCIAL" : "La Razón social es obligatoria."
    	},
    	"RANGO_FACTURACION" : {
    		"ELIMINAR_RANGO" : "Se procede a eliminar un rango de facturación. ¿Desea continuar?",
    		"ERR_LIMITE_INICIAL" : "El límite inicial debe ser mayor que cero.",
    		"ERR_LIMITE_FINAL" : "El límite final debe ser mayor al límite incial."
    	},
    	"RAMAL" : {
    		"ERR_NOMBRE_OBLIGATORIO" : "El nombre del ramal es obligatorio.",
    		"ERR_CODIGO_OBLIGATORIO" : "El código del ramal es obligatorio."
    	},
    	"REPORTES" : {
    		"ARCHIVO_ENVIADO" : "El archivo se ha enviado de manera exitosa.",
    		"ERR_LIMITE" : "El límite superior no debe ser mayor al límite inferior",
    		"ERR_VARIACION_NEGATIVO" : "El porcentaje de variación debe ser mayor que cero."
    	},
    	"SEGURIDAD" : {
    		"ERR_PASS" : "La contraseña ingresada no coincide con la confirmación.",
    		"ERR_APELLIDO" : "Ingrese al menos un apellido.",
    		"ERR_NOMBRE" : "Ingrese al menos un nombre.",
    		"ERR_IDENTIFICACION" : "Ingrese un número de identificación."
    	},
    	"SISTEMA" : {
    		"INFO_MODIFICAR_ESTRATO" : "Se procede a modificar el cobro por ESTRATO, este cambio elimina los valores en TARIFAS y CONSUMOS previamente configurados. ¿Desea continuar con el cambio?",
    	},
    	"TARIFA" : {
    		"INFO_MODIFICAR" : "Se procede a modificar una tarifa. ¿Desea continuar?",
    		"ERR_OBLIGATORIO" : "Los campos de código y descripción son obligatorios.",
    		"ERR_FALTA_VALOR_ESTRATO" : "Debe indicar los valores para cada uno de los estratos.",
    		"ERR_FALTA_VALOR" : "Debe indicar el valor de la tarifa."
    	},
    	"USUARIO" : {
    		"ERR_CEDULA_OBLIGATORIO" : "Debe digitar una cédula o un nombre para continuar la consulta.",
    		"ERR_OBLIGATORIO" : "Existen campos obligatorios sin diligenciar.",
    		"ERR_MAIL_OBLIGATORIO" : "Para suscribirse al envío de factura por correo electrónico debe ingresar un email válido.",
    		"ERR_USUARIO_NO_EXISTE" : "No existen usuarios con los criterios de búsqueda ingresados."
    	},
    	"VALOR" : {
    		"FIJO" : "FIJO"
    	},
    	"VEREDA" : { 
    		"ERR_NOMBRE_VEREDA" : "Debe ingresar el nombre de la vereda."
    	}
    		
    });
});