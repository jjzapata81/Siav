package co.com.siav.utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public final class Constantes {

	private Constantes() {
	}

	// public static final Long REGISTRO_NUMERO_FACTURA = 1L;

	public static final String INSTALACION_NO_EXISTE = "La instalación %s no existe.";
	public static final String INSTALACION_EXISTE = "La instalación %s ya existe.";
	public static final String INSTALACION_CREADA = "Se ha creado la instalación %s.";

	public static final String VEREDA_INSTALACION_NO_EXISTE = "No se encontró una vereda para la instalación %s.";
	public static final String CODIGO_TARIFA_NO_EXISTE = "La tarifa con código %s no existe.";

	// Sirve para los estados de pago de las facturas
	public static final String PENDIENTE = "P";
	public static final String CANCELADO = "C";

	public static final String SI = "S";
	public static final String NO = "N";
	public static final String FIJO = "1";
	public static final String VARIABLE = "2";

	public static final String ABIERTO = "ABIERTO";
	public static final String CERRADO = "CERRADO";
	public static final String VENCIDO = "VENCIDO";

	// Estados en los rangos de la facturación
	public static final String DISPONIBLE = "DISPONIBLE"; // Rango que se
															// encuentra
															// disponible para
															// su uso
	public static final String EMITIDO = "EMITIDO"; // Rango que se ha utilizado
													// por completo
	public static final String VIGENTE = "VIGENTE"; // Rango empleado acualmente
	public static final String ESPACIO = " ";
	
	public static final String COD_NOTA_CREDITO = "888888";

	public static final String USUARIO_ACTIVO = "ACTIVO";
	public static final String USUARIO_EXISTE = "Ya existe un usuario con la cédula %s.";
	public static final String USUARIO_NO_EXISTE = "No existen usuarios con la cédula %s.";

	// Prefacturacion
	public static final String ERR_FALTA_VALOR_ESTRATOS = "Debe ingresar los valores de tarifas de tipo FIJO y consumos por estratos.";
	public static final String ERR_FALTA_VALOR_TARIFAS = "Debe ingresar los valores de tarifas de tipo FIJO y consumos.";
	public static final String ERR_SIN_INSTALACION_ACTIVA = "No existen instalaciones ACTIVAS para el presente ciclo y no se puede proceder con la prefacturación.";
	public static final String ERR_SIN_CICLO_ABIERTO = "No existen ciclos ABIERTOS para proceder con la prefacturación.";
	public static final String ERR_SIN_RANGO = "No hay números de facturación disponibles para proceder con la prefacturación.";
	public static final String ERR_LECTURAS_PENDIENTES = "Existen lecturas pendientes por ingresar. No se puede continuar con la prefacturación.";

	public static final String CAMBIO_CLAVE_ERR = "Nombre de usuario o contraseña no son correctos.";
	public static final String CAMBIO_CLAVE_OK = "La contraseña ha sido modificada de manera satisfactoria.";
	public static final String ACTUALIZACION_EXITO = "Los cambios se han almacenado con éxito.";
	public static final String ACTUALIZACION_FALLO = "Ocurrió un error al tratar de actualizar la información.";
	public static final String CODIGO_TARIFA_EXISTE = "Ya existe una tarifa con código %s.";
	public static final String ERR_RANGO_NO_DISPONIBLE = "Sólo se pueden eliminar rangos en estado DISPONIBLE";
	public static final String ERR_RANGO_NO_VALIDO = "El rango no es válido, verifique los valores inicial y final.";
	public static final String ERR_CAUSAS_EXISTE = "Ya existe una causa con esa descripción.";

	public static final String CICLO_CERRADO = "El ciclo %s ha sido cerrado.";

	public static final String ACTUALIZACION_POR_ESTRATO = "Se ha cambiado el cobro por estrato. Debe proceder a actualizar los valores fijos en Tarifas y en Consumos. ";

	public static final String FACTURACION_NORMAL = "1";

	public static final String FACTURA_NO_EXISTE = "No existe la factura %s.";

	public static final String FACTURA_YA_CANCELADA = "La factura número %s, fue cancelada en la fecha %s.";
	public static final String ABONO_CANCELADO = "El abono número %s, fue cancelado en la fecha %s.";
	public static final String USUARIO_INCORRECTO = "El usuario o la contraseña no son correctos.";
	public static final String BIENVENIDO = "Bienvenido %s.";
	public static final String PREFACTURACION_EXITO = "El proceso de prefacturación ha finalizado de manera correcta. Se generaron %s registros.";
	public static final String USUARIO_INACTIVO = "El usuario no se encuentra activo.";
	public static final String ERR_ESTRUCTURA_ARCHIVO = "La estructura del archivo subido es incorrecta.";
	public static final String RUTA_ARCHIVO_PAGO = "RUTA_ARCHIVO_PAGO";
	public static final String ERR_SIN_CODIGO_CUENTA = "Debe indicar un número de cuenta.";
	public static final String ERR_LECTURA_ARCHIVO = "Ocurrio un error al tratar de leer el archivo.";
	public static final String ERR_NO_HAY_CREDITOS = "La instalación %s no tiene créditos vigentes.";
	public static final String ERR_SIN_INSTALACION = "La instalación ingresada no existe.";
	public static final String USUARIO_SISTEMA_NO_EXISTE = "No existe usuario con la identificación %s.";
	public static final String ERR_CONSULTAR_IP = "No se pudo consultar la dirección IP del servidor. ";
	public static final String FORMATO_RECAUDO = "FORMATO_RECAUDO";
	public static final String NUMERACION_CREDITO = "NUMERACION_CRED";
	public static final String SIN_FECHA = "SIN_FECHA";
	public static final String CREDITO_NO_EXISTE = "No existe el crédito %s.";
	public static final String CREDITO_CANCELADO = "El crédito %s fue cancelado en la fecha %s.";
	public static final String COMPROBANTE_CANCELADO = "El comprobante %s, fue cancelado en la fecha %s.";
	public static final String ERR_FALTA_COMPROBANTE = "No existe el comprobante número %s.";
	public static final String ERR_COMPROBANTE_NO_MATRICULA = "El comprobante número %s no corresponde a una matrícula.";
	public static final String ERR_COMPROBANTE_VALOR = "El valor del comprobante de pago (%s) es diferente al valor ingresado (%s).";
	public static final String ERR_RAMAL_DIFERENTE = "El ramal de la instalación %s es diferente al ramal de la instalación %s.";
	public static final String ERR_GENERACION_CREDITO = "ERROR buscando credito instalación %s.";
	public static final String ERR_CREAR_BANCO = "Ocurrió un error al guardar el banco.";
	public static final String ERR_MIEMBRO_JUNTA_EXISTE = "El usuario que está tratando de ingresar ya existe.";

	public static final Long ID_EMPRESA = 1L;
	public static final Long SIN_ORDEN = 99999L;
	public static final String ERR_CUENTA_EXISTE = "Ya existe una cuenta con el nombre %s.";
	public static final String ERR_CUENTA_NUMERO_EXISTE = "Ya existe una cuenta con el número %s.";
	public static final String CREACION_CUENTA = "Se creó la cuenta %s %s.";
	public static final String ERR_COD_RAMAL_EXISTE = "El código de ramal ingresado ya existe.";
	public static final String ERR_NOMBRE_RAMAL_EXISTE = "El nombre del ramal ingresado ya existe.";
	public static final String ERR_ELIMINAR_NOVEDAD = "Ocurrió un error al tratar de eliminar la novedad. ";
	public static final String ELIMINAR_NOVEDAD_OK = "Se eliminó la novedad de manera correcta.";
	public static final String ERR_APLIQUE_FACTURA = "Ocurrió un error al generar la factura para la instalación %s. ";
	public static final String ERR_CONCEPTO_DUPLICADO = "Se está ingresando un código duplicado (%s) para la instalación %s.";
	public static final String ERROR_HISTORICO = "Ocurrió un error al obtener el histórico de la factura %s.";
	public static final String FORMATO_HISTORICO = "%d-%s;";
	public static final String VACIO = "";
	public static final String ERR_CREAR_NOTA_CREDITO = "Ocurrió un error al crear la nota a crédito. ";
	public static final String VALOR_VENCIDO = "La instalación tiene un saldo pendiente de $%s pesos.";
	public static final String SALDO_FAVOR = "La instalación tiene un saldo a favor de $%s pesos.";
	public static final String ACCION_CUENTAS_VENCIDAS = "ACCION_CUENTAS_VENCIDAS";
	public static final String ERR_ELIMINAR_CREDITO = "No se puede eliminar créditos con cuotas generadas ni refinanciados.";
	public static final String ERR_CONSULTA = "No existen resultados con los criterios de búsqueda seleccionados.";
	public static final String ERR_NO_HAY_TARIFA = "No se han asignado las tarifas de consumos.";
	public static final String PATTERN_EMAIL = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
	public static final String EMAIL_NO_VALIDO = "El email ingresado no es válido.";
	public static final String ERR_CREAR_PQR = "Ocurrió un error al crear la PQR.";
	public static final String PQR_ESTADO = "PQR_ESTADO";
	public static final String ERR_ENVIO_NOTIFICACION = "Error enviando el correo de notificación.";
	public static final String EMAIL_ENVIO_CORREO = "EMAIL_ENVIO_CORREO";
	public static final String PASS_ENVIO_CORREO = "PASS_ENVIO_CORREO";
	public static final String ALIAS_ENVIO_CORREO = "ALIAS_ENVIO_CORREO";
	public static final String NOTIFICACION_ASUNTO = "Se ha asignado un incidente";
	public static final String ERR_PQR_NUMERO_INSTALACION = "Debe especificar un número de instalación.";
	public static final String ERR_PQR_USUARIO_CREACION = "Debe indicar un usuario de creación.";
	public static final String ERR_PQR_USUARIO_ASIGNACION = "Debe especificar el usuario a quien va a asignar el incidente.";
	public static final String ERR_PQR_DESCRIPCION = "Debe ingresar una descripción.";
	public static final String ERR_PQR_ESTADO = "Debe indicar en que estado queda el incidente.";
	public static final String ERR_PQR_ACCION = "Debe indicar una acción a realizar.";
	public static final String ERR_PROVEEDOR_NO_EXISTE = "No existe el proveedor.";
	public static final String ERR_ARTICULO_NO_EXISTE = "No existe el artículo.";
	public static final String UNIDAD_ARTICULO = "UNIDAD_ARTICULO";
	public static final String ERR_ARTICULO_NOMBRE_DUPLICADO = "Ya existe un artículo con el mismo nombre.";
	public static final String ENTRADA = "ENTRADA";
	public static final String TIPO_KARDEX = "TIPO_KARDEX";
	public static final String SALIDA = "SALIDA";
	public static final String INICIAL = "INICIAL";
	public static final String FINAL = "FINAL";
	public static final String ERR_ENTRADA_EXISTE = "Ya existe una entrada con número de factura %s y fecha %s.";
	public static final String MEDIDOR = "MEDIDOR";
	public static final String ERR_ARTICULO_SIN_KARDEX = "No se encontró información en el Kardex para el artículo %s.";
	public static final String SALIDA_ORDEN_EXITO = "Se generó la orden de salida número %s.";
	public static final String ERR_FECHA_POSTERIOR = "La fecha ingresada no puede ser posterior a la fecha actual.";
	public static final String ERR_ARTICULO_EXISTE = "Ya existe un artículo con el código ingresado.";
	public static final String ERR_ARTICULO_CODIGO_CONTABLE = "Ya existe una tarifa con el código contable ingresado.";
	public static final String ERR_ARTICULO_CODIGO_CONTABLE_DUPLICADO = "Ya existe un artículo con el código contable ingresado.";
	public static final String FACTURA_SOLO_FISICO = "Se procede a elaborar %d facturas.";
	public static final String FACTURA_CONSULTA = "Se procede a realizar la facturación para el ciclo %d. "
			+ "Total facturas: %d. "
			+ "Total facturas impresas: %d. "
			+ "Total enviadas por correo electrónico %d.";

	public static final String ERR_CONSUMO_NO_ENCONTRADO = "No se encontró un registro de consumo para el ciclo, instalación y serie de medidor ingresados.";
	public static final String OK = "OK";
	public static final String ERR_ACTUALIZACION_TARIFAS = "Ocurrió un error al tratar de actualizar los códigos de las tarifas.";

	public static final String ERR_CONSUMO_NEGATIVO = "Existen consumos negativos. Ingrese a la opción de inconsistencias para corregirlas.";
	public static final String USUARIO_OPERACION_NO_ENCONTRADO = "No ha sido posible encontrar el usuario de la operación con los datos indicados.";
	public static final String DESTINO_INSTALACION = "1";
	public static final String ERR_INSTALACION_NO_INFO = "No se encontró información para la instalación seleccionada";
	public static final String TIPO_FACTURA = "FACTURA";
	public static final String TIPO_CREDITO = "CREDITO";
	public static final String TIPO_MATRICULA = "MATRICULA";
	public static final String TIPO_ABONO = "ABONO";
	public static final String TIPO_EXCEDENTE = "EXCEDENTE";
	public static final String OPCION_NO_DISPONIBLE = "Esta opción ha sido deshabilitada.";
	public static final String COMPROBANTE_NO_EXISTE = "Comprobante no existe.";
	public static final String PAGO_ERROR = "Ocurrió un error registrando el pago %s.";
	public static final String ERROR_VENCIDA = "================= ERROR =================  Se produjo un error al tratar de marcar una cuenta vencida para la instalacion ";
	public static final String CODIGO_CONCEPTO_IVA_VENTAS = "23450101";
	public static final String DESACTIVACION_FALLO = "No se puede desactivar una instalación inactiva.";
	public static final String CODIGO_CIERRE_SALDO_FAVOR = "3";

	public static String getMensaje(String mensaje, Long codigo) {
		return getMensaje(mensaje, String.valueOf(codigo));
	}

	public static String getMensaje(String mensaje, Long param1, Long param2) {
		return getMensaje(mensaje, String.valueOf(param1),
				String.valueOf(param2));
	}

	public static String getMensaje(String mensaje, String codigo) {
		return String.format(mensaje, codigo);
	}

	public static String getMensaje(String mensaje, String param1, String param2) {
		return String.format(mensaje, param1, param2);
	}

	public static String getMensaje(String mensaje, String param1, Long param2) {
		return getMensaje(mensaje, param1, String.valueOf(param2));
	}

	public static String getMensaje(String mensaje, Long param1, Date param2) {
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		String strDate = dateFormat.format(param2);
		return getMensaje(mensaje, String.valueOf(param1), strDate);
	}

}
