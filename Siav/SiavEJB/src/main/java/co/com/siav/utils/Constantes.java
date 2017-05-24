package co.com.siav.utils;

public class Constantes {
	
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

	public static final Object ACTUALIZACION_POR_ESTRATO = "Se ha cambiado el cobro por estrato. Debe proceder a actualizar los valores fijos en Tarifas y en Consumos. ";

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
	
	public static String getMensaje(String mensaje, Long codigo) {
		return getMensaje(mensaje, String.valueOf(codigo));
	}
	
	public static String getMensaje(String mensaje, String codigo) {
		return String.format(mensaje, codigo);
	}





	
}