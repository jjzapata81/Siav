package co.com.siav.utils;

public final class Constantes {
	
	private Constantes(){}
	
//	public static final Long REGISTRO_NUMERO_FACTURA = 1L;

	public static final String INSTALACION_NO_EXISTE = "La instalación %s no existe.";
	public static final String INSTALACION_EXISTE = "La instalación %s ya existe.";
	public static final String INSTALACION_CREADA = "Se ha creado la instalación %s.";
	
	public static final String VEREDA_INSTALACION_NO_EXISTE = "No se encontró una vereda para la instalación %s.";
	public static final String CODIGO_TARIFA_NO_EXISTE = "La tarifa con código %s no existe.";
	
	//Sirve para los estados de pago de las facturas
	public static final String PENDIENTE = "P";
	public static final String CANCELADO = "C";

	
	public static final String SI = "S";
	public static final String NO = "N";
	public static final String FIJO = "1";
	public static final String VARIABLE = "2";
	
	public static final String ABIERTO = "ABIERTO";
	public static final String CERRADO = "CERRADO";
	public static final String VENCIDO = "VENCIDO";
	
	//Estados en los rangos de la facturación
	public static final String DISPONIBLE = "DISPONIBLE"; //Rango que se encuentra disponible para su uso
	public static final String EMITIDO = "EMITIDO"; // Rango que se ha utilizado por completo
	public static final String VIGENTE = "VIGENTE"; // Rango empleado acualmente
	public static final String ESPACIO = " ";
	
	public static final String USUARIO_ACTIVO = "ACTIVO";
	public static final String USUARIO_EXISTE = "Ya existe un usuario con la cédula %s.";
	public static final String USUARIO_NO_EXISTE = "No existen usuarios con la cédula %s.";
	
	//Prefacturacion
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

	public static final String FACTURA_YA_CANCELADA = "La factura número %s se encuentra en estado CANCELADO.";
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
	public static final String CREDITO_CANCELADO = "El crédito %s se encuentra cancelado.";
	public static final String COMPROBANTE_CANCELADO = "El comprobante %s ha sido previamente registrado.";
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
	public static final String ERR_APLIQUE_FACTURA = "Ocurrió un error al generar la factura %s. ";
	public static final String ERR_CONCEPTO_DUPLICADO = "Se está ingresando un código duplicado (%s) para la instalación %s.";
	public static final String ERROR_HISTORICO = "Ocurrió un error al obtener el histórico de la factura %s.";
	public static final String FORMATO_HISTORICO = "%d-%s;";
	public static final String VACIO = "";
	
	public static String getMensaje(String mensaje, Long codigo) {
		return getMensaje(mensaje, String.valueOf(codigo));
	}
	
	public static String getMensaje(String mensaje, Long param1, Long param2) {
		return getMensaje(mensaje, String.valueOf(param1), String.valueOf(param2));
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





	
}
