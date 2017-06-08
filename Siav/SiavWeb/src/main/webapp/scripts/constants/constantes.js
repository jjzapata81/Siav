/*global define*/
'use strict';

define(['siav-module'], function(app) {
    app.constant("CONSTANTES", {
    	
    	"SRV" : {
    		//Cuentas
    		"CREAR_BANCO" : "rest/bancos/crear",
    		"EDITAR_BANCO" : "rest/bancos/editar",
    		"CONSULTAR_BANCO" : "rest/bancos/consultar",
    		"CONSULTAR_CUENTAS" : "rest/bancos/consultar/cuentas",
    		//Ciclos
    		"CONSULTAR_CICLOS" : "rest/ciclos/consultar",
    		"CONSULTAR_CICLOS_TODO" : "rest/ciclos/consultar/todo",
    		"EDITAR_CICLOS" : "rest/ciclos/editar",
    		"CERRAR_CICLO" : "rest/ciclos/cerrar/",
    		
    		"GUARDAR_PAGO" : "rest/pagos/guardar",
    		"ABONAR_PAGO" : "rest/pagos/abonar",
    		"BUSCAR_PAGO" : "rest/pagos/buscar/",
    		"CARGAR_PAGO" : "rest/pagos/cargar/",
    		"CONSUMOS_INCOMPLETOS" : "rest/consumos/consultar/incompletos",
    		"CONSUMOS_RANGO" : "rest/consumos/consultar/rango",
    		"CONSUMOS_INCOMPLETOS_GUARDAR" : "rest/consumos/guardar",
    		"CONSUMOS_RIESGO_GUARDAR" : "rest/consumos/riesgo/guardar",
    		"CONSUMOS_RIESGO_GUARDAR_CORRECCION_CONSUMO" : "rest/consumos/correccion/guardar",
    		"CONSULTAR_ORDEN_RUTAS" : "rest/ruta/consultar",
    		"GUARDAR_ORDEN_RUTAS" : "rest/ruta/guardar",
    		"SISTEMA_CONSULTAR_IP":"rest/general/consultar/ip",
    		"SISTEMA_CONSULTAR_ESTRUCTURA" : "rest/general/consultar/estructura",
    		"SISTEMA_CONSULTAR_EMPRESA" : "rest/general/consultar/empresa",
    		"SISTEMA_ACTUALIZAR_EMPRESA" : "rest/general/actualizar/empresa",
    		"SISTEMA_AGREGAR_JUNTA" : "rest/general/agregar/junta",
    		"SISTEMA_ACTUALIZAR_JUNTA" : "rest/general/actualizar/junta",
    		"MACROS_CONSULTAR" : "rest/macros/consultar",
    		"MACROS_GUARDAR" : "rest/macros/",
    		"RAMAL_CONSULTAR_TODO" : "rest/ramal/consultar/todo",
    		"RAMAL_CONSULTAR" : "rest/ramal/consultar",
    		"RAMAL_GUARDAR" : "rest/ramal/guardar/"
    	},
    	"MSG" : {
    		"CAMBIO_GUARDADO" : "Los cambios han sido guardados de manera correcta.",
    		"CONFIRMAR_ELIMINAR_RANGO" : "Se procede a eliminar un rango de facturación. Desea continuar?"
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
    		"AGREGAR_PROPPIETARIO" : "Agregar propietario"
    	},
    	"BANCO" : {
    		"INFO_MODIFICAR_CUENTA" : "Se procede a modificar la cuenta, desea continuar?",
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
    		"CONFIRMAR_CIERRE_COMPLEMENTO" : ". Desea continuar?",
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
    		"INFO_MODIFICAR_ESTADO" : "Se procede a deshabilitar un miembro de la junta. Desea continuar?"
    	},
    	"EXCESO" : {
    		"INFO_EDITAR_RANGO" : "La modificación de los límites puede afectar todos los consumos, desea continuar?",
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
    	"MACROS" :{
    		"ERR_MACRO_NOMBRE" : "Debe ingresar el nombre del macromedidor."
    	},
    	"PAGO" : {
    		"NO_CANCELADO" : "Ha seleccionado pago no cancelado. Está seguro que desea continuar?",
    		"ERR_EXTENSION" : "Sólo se permiten cargar archivos con extensión .csv",
    		"ERR_NUMERO_CUENTA" : "Debe seleccionar una cuenta bancaria para asociar el pago."
    	},
    	"RANGO_FACTURACION" : {
    		"ELIMINAR_RANGO" : "Se procede a eliminar un rango de facturación. Desea continuar?",
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
    		"INFO_MODIFICAR_ESTRATO" : "Se procede a modificar el cobro por ESTRATO, este cambio elimina los valores en TARIFAS y CONSUMOS previamente configurados. Desea continuar con el cambio?",
    	},
    	"TARIFA" : {
    		"INFO_MODIFICAR" : "Se procede a modificar una tarifa. Desea continuar?",
    		"ERR_OBLIGATORIO" : "Los campos de código y descripción son obligatorios.",
    		"ERR_FALTA_VALOR_ESTRATO" : "Debe indicar los valores para cada uno de los estratos.",
    		"ERR_FALTA_VALOR" : "Debe indicar el valor de la tarifa."
    	},
    	"USUARIO" : {
    		"ERR_CEDULA_OBLIGATORIO" : "Debe ingresar una cédula.",
    		"ERR_OBLIGATORIO" : "Existen campos obligatorios sin diligenciar."
    	},
    	"VALOR" : {
    		"FIJO" : "FIJO"
    	},
    	"VEREDA" : { 
    		"ERR_NOMBRE_VEREDA" : "Debe ingresar el nombre de la vereda."
    	}
    		
    });
});