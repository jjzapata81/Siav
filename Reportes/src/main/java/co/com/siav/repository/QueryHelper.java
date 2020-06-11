package co.com.siav.repository;
import co.com.siav.pdf.dto.InstalacionPDFBase;
import co.com.siav.reports.filters.Comprobante;
import co.com.siav.reports.filters.Filter;
import co.com.siav.rest.request.MatriculaRequest;
import co.com.siav.utility.Constantes;
import co.com.siav.utility.Formatter;

public class QueryHelper {
	
	public static String getCuentasVencidas(Filter filter) {
		StringBuilder sb = new StringBuilder();
		sb.append("select fma.nminstalacion as instalacion, fma.nombres, fdetalle.valorfac as total, fma.cuentasvencidas ");
		sb.append("from ta_factura_maestro fma inner join (select (COALESCE(sum(fde.valor),0) + COALESCE(sum(fde.saldo),0)) as valorfac, fde.nmfactura as fac  from ta_factura_detalle fde group by fde.nmfactura) ");
		sb.append("as fdetalle on fma.nmfactura = fdetalle.fac ");
		sb.append("where fma.ciclo = ");
		sb.append(filter.getCiclo());
		if(null != filter.getValorDesde()){
			sb.append(" and fma.cuentasvencidas >= ");
			sb.append(filter.getValorDesde());
		}else{
			sb.append(" and fma.cuentasvencidas >= 0");
		}
		if(null != filter.getValorHasta()){
			sb.append(" and fma.cuentasvencidas <= ");
			sb.append(filter.getValorHasta());
		}
		sb.append(" order by 4 desc, 1, 2");
		return sb.toString();
	}

//	public static String getVentas(Filter filter) {
//		StringBuilder sb = new StringBuilder();
//		sb.append("SELECT cdconcepto concepto, nombre, sum(valor) venta, sum(saldo) cartera ");
//		sb.append("FROM ta_factura_maestro fe, ta_factura_detalle fd ");
//		sb.append("where fe.nmfactura = fd.nmfactura and ");
//		sb.append("ciclo =  ");
//		sb.append(filter.getCiclo());
//		sb.append(" and cdconcepto in ('412515', '412520', '402514', '431001','431002','431003') ");
//		sb.append("group by cdconcepto, nombre; ");
//		return sb.toString();
//	}

	public static String getPrefacturaEncabezado(Filter filter) {
		StringBuilder sb = new StringBuilder();
		sb.append("SELECT fm.nminstalacion AS numero, ");
		sb.append("fm.nombres AS nombres, ");
		sb.append("fm.cedula AS cedula, ");
		sb.append("fm.seriemedidor AS serialMedidor, ");
		sb.append("fm.nmfactura AS factura, ");
		sb.append("fm.cuentasvencidas AS cuentasVencidas, ");
		sb.append("fm.leactual AS lecturaActual, ");
		sb.append("fm.leanterior AS lecturaAnterior, ");
		sb.append("fm.consumodefinitivo AS consumo FROM ta_factura_maestro fm where fm.ciclo = ");
		sb.append(filter.getCiclo());
		sb.append(" order by 1;");
		return sb.toString();
	}

	public static String getPrefacturaDetalle(InstalacionPDFBase instalacion) {
		StringBuilder sb = new StringBuilder();
		sb.append("SELECT fd.cdconcepto AS codigo, ");
		sb.append("fd.nombreconcepto AS concepto, ");
		sb.append("fd.descripcion AS descripcion, ");
		sb.append("(COALESCE(fd.valor,0)) AS valor, ");
		sb.append("(COALESCE(fd.saldo,0)) AS saldo FROM ta_factura_detalle fd ");
		sb.append("WHERE fd.nmfactura =  ");
		sb.append(instalacion.getFactura());
		sb.append(" AND fd.nminstalacion =  ");
		sb.append(instalacion.getNumero());
		return sb.toString();
	}
	
	public static String getFacturaEncabezado(Filter filter){
		StringBuilder sb = new StringBuilder();
		sb.append("SELECT ");
		sb.append("fm.ciclo AS ciclo, ");
		sb.append("fm.cedula AS cedula, ");
		sb.append("fm.nombres AS nombre, ");
		sb.append("fm.estrato AS estrato, ");
		sb.append("fm.nminstalacion AS instalacion, ");
		sb.append("fm.direccion AS direccion, ");
		sb.append("fm.nombrevereda AS vereda, ");
		sb.append("fm.seriemedidor AS medidor, ");
		sb.append("fm.nmfactura AS numeroFactura, ");
		sb.append("fm.consumodefinitivo AS consumoActual, ");
		sb.append("fm.consumopromedio AS promedioConsumo, ");
		sb.append("fm.consumoanterior AS consumoAnterior, ");
		sb.append("fm.hria_consumos AS historicoConsumo, ");
		sb.append("fm.fedesde AS fechaAnterior, ");
		sb.append("fm.fehasta AS fechaActual, ");
		sb.append("fm.feultimopago AS fechaUltimoPago, ");
		sb.append("fm.valorultimopago AS valorMesAnterior, ");
		sb.append("COALESCE(fm.cuentasvencidas,0) AS cuentasVencidas, ");
		sb.append("EXTRACT(DAY FROM fm.fehasta - fm.fedesde) AS diasConsumo, ");
		sb.append("fm.leactual AS lecturaActual, ");
		sb.append("fm.leanterior AS lecturaAnterior FROM ta_factura_maestro fm, ta_instalacion ti ");
		sb.append("WHERE fm.nminstalacion = ti.nminstalacion ");
		sb.append("AND fm.ciclo =  ");
		sb.append(filter.getCiclo());
		if(null != filter.getCedula()){
			sb.append("AND ti.cedula = '");
			sb.append(filter.getCedula().trim());
			sb.append("' ");
		}
		sb.append(" ORDER BY ti.cdramal, ti.nmorden");
		return sb.toString();
	}
	
	public static String getFacturaDetalle(String numeroFactura){
		StringBuilder sb = new StringBuilder();
		sb.append("SELECT "); 
		sb.append("cdconcepto AS codigoConcepto, ");
		sb.append("nombreconcepto AS nombreConcepto, ");
		sb.append("metros AS metros, ");
		sb.append("valor AS valor, ");
		sb.append("saldo AS saldo, ");
		sb.append("descripcion AS descripcion, ");
		sb.append("idcredito AS codigoCredito ");
		sb.append("FROM ta_factura_detalle where nmfactura =  ");
		sb.append(numeroFactura);
		return sb.toString();
	}
	
	public static String getCreditos(String numeroInstalacion){
		StringBuilder sb = new StringBuilder();
		sb.append("SELECT ");
		sb.append("cm.nmcredito AS numero, ");
		sb.append("tarifa.descripcion AS nombre, ");
		sb.append("(cm.interes/100) AS tasa, ");
		sb.append("cm.actual AS cuotaActual, ");
		sb.append("(cm.cuotas - cm.actual) AS cuotasPendientes, ");
		sb.append("FLOOR(cm.saldo * (cm.interes/100)) AS interes, ");
		sb.append("FLOOR((cm.valor - cm.inicial) / cm.cuotas) AS valor, ");
		sb.append("cm.saldo AS saldo ");
		sb.append("FROM ta_credito_maestro cm ");
		sb.append("INNER JOIN (SELECT ta.descripcion, ta.cdconcepto FROM ta_tarifas ta) AS tarifa ON tarifa.cdconcepto = cm.cdconcepto ");
		sb.append("WHERE cm.nminstalacion =  ");
		sb.append(numeroInstalacion);
		sb.append(" AND cm.saldo > 0 ORDER BY 1");
		return sb.toString();
	}

	public static String getEmpresa() {
		StringBuilder sb = new StringBuilder();
		sb.append("SELECT nmempresa AS empresa, ");
		sb.append("nit AS nit, ");
		sb.append("nombrecorto AS nombreCorto, ");
		sb.append("nombrelargo AS nombreLargo, ");
		sb.append("direccion AS direccion, ");
		sb.append("telefono AS telefono, "); 
		sb.append("ciudad AS ciudad ");
		sb.append("FROM ta_empresa");
		return  sb.toString();
	}

	public static String getCiclo(Filter filter) {
		StringBuilder sb = new StringBuilder();
		sb.append("SELECT ciclo, fecha, felectura AS feFactura, fesinrecargo, feconrecargo, snestado, mensaje, ");
		sb.append("mensajereclamo AS mensajeReclamo, mensajepuntopago AS mensajePuntoPago, mensajecorte AS mensajeCorte ");
		sb.append("FROM ta_ciclos ");
		sb.append("WHERE ciclo =  ");
		sb.append(filter.getCiclo());
		return sb.toString();
	}
	
	public static String getCicloPorFecha(Filter filter) {
		StringBuilder sb = new StringBuilder();
		sb.append("SELECT ciclo, fecha, felectura AS feFactura, fesinrecargo, feconrecargo, snestado, mensaje, ");
		sb.append("mensajereclamo AS mensajeReclamo, mensajepuntopago AS mensajePuntoPago, mensajecorte AS mensajeCorte ");
		sb.append("FROM ta_ciclos ");
		sb.append("WHERE fecha =  '");
		sb.append(Formatter.createStringFromDate(filter.getFechaDesde(), Constantes.YYYY_MM_DD));
		sb.append("'");
		return sb.toString();
	}
	
	public static String getCicloPorEstado(String estado) {
		StringBuilder sb = new StringBuilder();
		sb.append("SELECT ciclo, fecha, felectura AS feFactura, fesinrecargo, feconrecargo, snestado, mensaje, ");
		sb.append("mensajereclamo AS mensajeReclamo, mensajepuntopago AS mensajePuntoPago, mensajecorte AS mensajeCorte ");
		sb.append("FROM ta_ciclos ");
		sb.append("WHERE ciclo =  ");
		sb.append("(SELECT COALESCE(MAX(c.ciclo), '0') FROM ta_ciclos c WHERE c.snestado = '");
		sb.append(estado);
		sb.append("')");
		return sb.toString();
	}
	
	public static String getSistema(){
		StringBuilder sb = new StringBuilder();
		sb.append("SELECT idcargofijo AS cargoFijo, ");
		sb.append("idbasico AS basico, ");
		sb.append("idcomplementario AS complementario, ");
		sb.append("idsuntuario AS suntuario, ");
		sb.append("snenviofactura AS envioFactura, ");
		sb.append("cuentasvencidas AS cuentasVencidas, ");
		sb.append("cuentascorte AS cuentasCorte, ");
		sb.append("iva AS iva ");
		sb.append("FROM ta_sistema ");
		return sb.toString();
	}
	
	public static String getParametro(IConsultaParametro parametro){
		StringBuilder sb = new StringBuilder();
		sb.append("SELECT ");
		sb.append(parametro.getColumna());
		sb.append(" AS parametro ");
		sb.append("FROM ");
		sb.append(parametro.getTabla());
		sb.append(" WHERE ");
		sb.append(parametro.getCondicion());
		sb.append(" = '");
		sb.append(parametro.getValor());
		sb.append("'");
		return sb.toString();
	}

	public static String getFacturaAbono(Long numeroInstalacion) {
		StringBuilder sb = new StringBuilder();
		sb.append("SELECT "); 
		sb.append("(COALESCE(u.nombres,''))|| ' ' || (COALESCE(u.apellidos,'')) AS nombre, ");
		sb.append("v.nombre AS vereda, ");
		sb.append("i.direccion, ");
		sb.append("i.estrato ");
		sb.append("from ta_instalacion i, ta_usuarios u, ta_veredas v ");
		sb.append("WHERE i.nminstalacion = ");
		sb.append(numeroInstalacion);
		sb.append(" AND i.cedula = u.cedula AND i.cdvereda = v.cdvereda ");
		return sb.toString();
	}

	public static String getUsuarios(Filter filtro) {
		StringBuilder sb = new StringBuilder();
		sb.append("SELECT ");
		sb.append("tu.cedula AS cedula, ");
		sb.append("tu.nombres AS nombres, ");
		sb.append("tu.apellidos AS apellidos, ");
		sb.append("tu.telefono AS telefono, ");
		sb.append("tu.celular AS celular, ");
		sb.append("tu.email AS email ");
		sb.append("FROM ta_usuarios tu ");
		if(null != filtro.getCedula()){
			sb.append("WHERE tu.cedula = '");
			sb.append(filtro.getCedula());
			sb.append("' ");
		}else{
			sb.append("WHERE tu.nombres LIKE '%");
			sb.append(null == filtro.getNombres() ? "" : filtro.getNombres().toUpperCase());
			sb.append("%' ");
			sb.append("AND tu.apellidos LIKE '%");
			sb.append(null == filtro.getApellidos() ? "" : filtro.getApellidos().toUpperCase());
			sb.append("%' ");
		}
		sb.append("ORDER BY tu.nombres");
		return sb.toString();
	}
	
	public static String getInstalaciones(String cedula) {
		StringBuilder sb = new StringBuilder();
		sb.append("SELECT ");
		sb.append("ti.nminstalacion AS instalacion, ");
		sb.append("ti.cvencidas AS cuentasVencidas, ");
		sb.append("ti.cdramal AS ramal, ");
		sb.append("ti.seriemedidor AS medidor ");
		sb.append("FROM ta_instalacion ti ");
		sb.append("WHERE ti.cedula = '");
		sb.append(cedula);
		sb.append("' ");
		sb.append("ORDER BY ti.nminstalacion");
		return sb.toString();
	}
	
	public static String getUsuariosExcel(Filter filtro) {
		StringBuilder sb = new StringBuilder();
		sb.append("SELECT ");
		sb.append("tu.cedula AS cedula, ");
		sb.append("tu.nombres AS nombres, ");
		sb.append("tu.apellidos AS apellidos, ");
		sb.append("tu.telefono AS telefono, ");
		sb.append("tu.celular AS celular, ");
		sb.append("tu.email AS email, ");
		sb.append("ti.nminstalacion AS instalacion, ");
		sb.append("ti.cvencidas AS cuentasVencidas, ");
		sb.append("ti.cdramal AS ramal, ");
		sb.append("ti.seriemedidor AS medidor ");
		sb.append("FROM ta_usuarios tu, ta_instalacion ti ");
		sb.append("WHERE tu.cedula = ti.cedula ");
		if(null != filtro.getCedula()){
			sb.append("AND tu.cedula = '");
			sb.append(filtro.getCedula());
			sb.append("' ");
		}else{
			sb.append("AND tu.nombres LIKE '%");
			sb.append(null == filtro.getNombres() ? "" : filtro.getNombres().toUpperCase());
			sb.append("%' ");
			sb.append("AND tu.apellidos LIKE '%");
			sb.append(null == filtro.getApellidos() ? "" : filtro.getApellidos().toUpperCase());
			sb.append("%' ");
		}
		sb.append("ORDER BY tu.nombres, ti.nminstalacion");
		return sb.toString();
	}

	public static String getPrefacturaExcel(Filter filter) {
		StringBuilder sb = new StringBuilder();
		sb.append("SELECT ");
		sb.append("fm.nmfactura AS numerofactura, ");
		sb.append("fm.nminstalacion AS instalacion, ");
		sb.append("fm.nombres AS nombre, ");
		sb.append("fm.cedula AS cedula, ");
		sb.append("fm.consumodefinitivo AS consumo, ");
		sb.append("fm.cuentasvencidas AS cuentasVencidas, ");
		sb.append("fd.cdconcepto AS codigoConcepto, ");
		sb.append("fd.nombreconcepto AS nombreConcepto, ");
		sb.append("fd.metros AS metros,  ");
		sb.append("fd.valor AS valor,  ");
		sb.append("fd.saldo AS saldo,  ");
		sb.append("fd.descripcion AS descripcion, ");
		sb.append("fd.idcredito AS codigoCredito ");
		sb.append("FROM ta_factura_maestro fm, ta_factura_detalle fd  ");
		sb.append("WHERE fm.nmfactura = fd.nmfactura ");
		sb.append("AND fm.ciclo = ");
		sb.append(filter.getCiclo());
		sb.append(" ORDER BY fm.nminstalacion ");
		return sb.toString();
	}
	
	public static String getVariacionConsumo(Filter filter){
		StringBuilder sb = new StringBuilder();
		sb.append("select i.nminstalacion as instalacion, ");
		sb.append("(COALESCE(u.nombres,''))|| ' ' || u.apellidos as nombre, ");
		sb.append("consumodefinitivo as consumoActual, ");
		sb.append("(SELECT consumodefinitivo from ta_consumos ant where ant.ciclo = c.ciclo-1 and ant.nminstalacion = c.nminstalacion) as consumoAnterior, ");
		sb.append("trunc( ");
		sb.append("((consumodefinitivo-(SELECT consumodefinitivo from ta_consumos ant where ant.ciclo = c.ciclo-1 and ant.nminstalacion = c.nminstalacion)) ");
		sb.append("/(SELECT consumodefinitivo from ta_consumos ant where ant.ciclo = c.ciclo-1 and ant.nminstalacion = c.nminstalacion)*100),1) as difPorcentaje, ");
		sb.append("consumodefinitivo-(SELECT consumodefinitivo from ta_consumos ant where ant.ciclo = c.ciclo-1 and ant.nminstalacion = c.nminstalacion) as difMetros ");
		sb.append("from  ta_consumos c, ta_instalacion i, ta_usuarios u ");
		sb.append("WHERE i.nminstalacion = c.nminstalacion and i.cedula = u.cedula ");
		sb.append("and c.ciclo = ");
		sb.append(filter.getCiclo());
		sb.append(" and (SELECT consumodefinitivo from ta_consumos ant where ant.ciclo = c.ciclo-1 and ant.nminstalacion = c.nminstalacion) <> 0 ");
		sb.append("and (consumodefinitivo-(SELECT consumodefinitivo from ta_consumos ant where ant.ciclo = c.ciclo-1 and ant.nminstalacion = c.nminstalacion)) ");
		sb.append("/(SELECT consumodefinitivo from ta_consumos ant where ant.ciclo = c.ciclo-1 and ant.nminstalacion = c.nminstalacion)*100 > ");
		sb.append(filter.getValorDesde());
		sb.append(" and ");
		sb.append("(consumodefinitivo-(SELECT consumodefinitivo from ta_consumos ant where ant.ciclo = c.ciclo-1 and ant.nminstalacion = c.nminstalacion)) ");
		sb.append("/(SELECT consumodefinitivo from ta_consumos ant where ant.ciclo = c.ciclo-1 and ant.nminstalacion = c.nminstalacion)*100 < ");
		sb.append(filter.getValorHasta());
		sb.append(" order by abs (trunc( ");
		sb.append("((consumodefinitivo-(SELECT consumodefinitivo from ta_consumos ant where ant.ciclo = c.ciclo-1 and ant.nminstalacion = c.nminstalacion)) ");
		sb.append("/(SELECT consumodefinitivo from ta_consumos ant where ant.ciclo = c.ciclo-1 and ant.nminstalacion = c.nminstalacion)*100),1)) desc, i.nminstalacion ");
		return sb.toString();
	}

	public static String getLecturasConsumos(Filter filter) {
		StringBuilder sb = new StringBuilder();
		sb.append("select i.nminstalacion AS instalacion, ");
		sb.append("(COALESCE(u.nombres,''))|| ' ' || u.apellidos as nombres, ");
		sb.append("c.leanterior, c.leactual, c.consumodefinitivo, c.consumopromedio, ");
		sb.append("(CASE WHEN i.cdtipofacturacion = '2' THEN 'N' ELSE '' END) AS paga ");
		sb.append("from  ta_consumos c, ta_instalacion i, ta_usuarios u ");
		sb.append("WHERE i.nminstalacion = c.nminstalacion and i.cedula = u.cedula ");
		sb.append("and c.ciclo = ");
		sb.append(filter.getCiclo());
		if(null != filter.getValorDesde()){
			sb.append(" and c.consumodefinitivo >= ");
			sb.append(filter.getValorDesde());
		}
		if(null != filter.getValorHasta()){
			sb.append(" and c.consumodefinitivo <= ");
			sb.append(filter.getValorHasta());
		}
		sb.append(" order by c.consumodefinitivo, i.nminstalacion ");
		return sb.toString();
	}
	
	public static String getRutas(Filter filter){
		StringBuilder sb = new StringBuilder();
		sb.append("SELECT i.cdramal AS ruta, i.nminstalacion AS instalacion, ");
		sb.append("(COALESCE(u.nombres,''))|| ' ' || (COALESCE(u.apellidos,'')) AS nombre, i.snmedidor AS tieneMedidor, i.seriemedidor AS serieMedidor, ");
		sb.append("CASE i.cdtipofacturacion WHEN '1' then 'S' ELSE 'N' END factura, ");
		sb.append("(SELECT nombre FROM ta_veredas WHERE cdvereda = i.cdvereda) AS vereda, i.direccion, i.telefono ");
		sb.append("FROM ta_instalacion i, ta_usuarios u ");
		sb.append("WHERE i.cedula = u.cedula AND snactivo = 'S' ");
		if(null != filter.getCriterio() && !filter.getCriterio().isEmpty()){
			sb.append("AND i.cdramal = '");
			sb.append(filter.getCriterio().toUpperCase());
			sb.append("' ");
		}
		sb.append("ORDER BY i.cdramal, nmorden ");
		return sb.toString();
	}

	public static String actualizar() {
		StringBuilder sb = new StringBuilder();
		sb.append("update ta_instalacion set seriemedidor = '12345' where nminstalacion = 4096");
		return sb.toString();
	}
	
	public static String saveComprobante(Comprobante filter){
		StringBuilder sb = new StringBuilder();
		sb.append("INSERT INTO ta_comprobante_pago ");
		sb.append("(nmcomprobante, nminstalacion, cedula, valor, usuario, fecha, nmcredito, snmatricula, snabono, sncancelado) ");
		sb.append("VALUES (");
		sb.append(filter.getComprobante());
		sb.append(", ");
		sb.append(filter.getInstalacion());
		sb.append(", '");
		sb.append(filter.getCedula());
		sb.append("', ");
		sb.append(filter.getValor());
		sb.append(", '");
		sb.append(filter.getUsuario());
		sb.append("', '");
		sb.append(filter.getFecha());
		sb.append("', ");
		sb.append(filter.getCredito());
		sb.append(", '");
		sb.append(null == filter.getEsMatricula() ? "N" : filter.getEsMatricula());
		sb.append("', '");
		sb.append(null == filter.getEsAbono() ? "N" : filter.getEsAbono());
		sb.append("', 'N')");
		return sb.toString();
	}

	public static String getComprobante() {
		StringBuilder sb = new StringBuilder();
		sb.append("SELECT MAX(nmcomprobante) AS parametro FROM ta_comprobante_pago");
		return sb.toString();
	}
	
	public static String getTotalConsumo(Filter filter) {
		StringBuilder sb = new StringBuilder();
		sb.append("SELECT SUM(c.consumodefinitivo) AS parametro ");
		sb.append(" FROM ta_consumos c, ta_instalacion i, ta_usuarios u ");
		sb.append("WHERE i.nminstalacion = c.nminstalacion and i.cedula = u.cedula ");
		sb.append("  AND c.ciclo = ");
		sb.append(filter.getCiclo());
		sb.append(" GROUP BY  c.ciclo ");
		return sb.toString();
	}

	public static String saveUsuario(MatriculaRequest filter) {
		StringBuilder sb = new StringBuilder();
		sb.append("INSERT INTO ta_usuarios ");
		sb.append("(cedula, nombres, apellidos, telefono, celular, direccion, ciudad, email, snenvioemail) ");
		sb.append("VALUES ('");
		sb.append(filter.getCedula());
		sb.append("', '");
		sb.append(filter.getNombres());
		sb.append("', '");
		sb.append(filter.getApellidos());
		sb.append("', '");
		sb.append(filter.getTelefono());
		sb.append("', '");
		sb.append(filter.getCelular());
		sb.append("', '");
		sb.append(filter.getDireccion());
		sb.append("', '");
		sb.append(filter.getCiudad());
		sb.append("', '");
		sb.append(filter.getEmail());
		sb.append("', 'N')");
		return sb.toString();
	}
	
	public static String getConsumoNoFacturado(Filter filter){
		StringBuilder sb = new StringBuilder();
		sb.append("SELECT i.nminstalacion AS instalacion, ");
		sb.append("   (CASE WHEN u.nombres IS NULL THEN u.apellidos ELSE u.nombres  || ' ' || u.apellidos END) nombre, ");
		sb.append("   c.leanterior, c.leactual, c.consumodefinitivo, c.consumopromedio ");
		sb.append(" FROM ta_consumos c, ta_instalacion i, ta_usuarios u ");
		sb.append("WHERE i.nminstalacion = c.nminstalacion and i.cedula = u.cedula and i.cdtipofacturacion='2' ");
		sb.append("  AND c.ciclo = ");
		sb.append(filter.getCiclo());
		sb.append(" ORDER BY 5, 1 ");
		return sb.toString();
	}
	
	public static String getEstadisticas(Filter filter){
		StringBuilder sb = new StringBuilder();
		sb.append("SELECT 12 AS orden, (SELECT v.nombre FROM ta_veredas v WHERE v.cdvereda = i.cdvereda) AS concepto, COUNT(1) instalaciones, SUM(c.consumodefinitivo) consumototal, ");
		sb.append("  ROUND(SUM(c.consumodefinitivo)*100/ ");
		sb.append("  ((SELECT  SUM(c.consumodefinitivo) FROM ta_consumos c, ta_instalacion i ");
		sb.append("WHERE i.nminstalacion = c.nminstalacion AND c.ciclo = ");
		sb.append(filter.getCiclo());
		sb.append(" GROUP BY c.ciclo)),2) porcentaje, ");
		sb.append("  ROUND(SUM(c.consumodefinitivo)/COUNT(1),2) consumopromedio ");
		sb.append(" FROM ta_consumos c, ta_instalacion i ");
		sb.append("WHERE i.nminstalacion = c.nminstalacion ");
		sb.append("  AND c.ciclo = ");
		sb.append(filter.getCiclo());
		sb.append(" GROUP BY c.ciclo, (SELECT v.nombre FROM ta_veredas v WHERE v.cdvereda = i.cdvereda) ");
		sb.append("UNION ");
		sb.append("SELECT 22 AS orden, (SELECT r.nombre FROM ta_ramal r WHERE r.cdramal = i.cdramal) AS concepto, COUNT(1) instalaciones, SUM(c.consumodefinitivo) consumototal, ");
		sb.append("  ROUND(SUM(c.consumodefinitivo)*100/ ");
		sb.append("  ((SELECT  SUM(c.consumodefinitivo) FROM ta_consumos c, ta_instalacion i ");
		sb.append("WHERE i.nminstalacion = c.nminstalacion AND c.ciclo = ");
		sb.append(filter.getCiclo());
		sb.append(" GROUP BY c.ciclo)),2) porcentaje, ");
		sb.append("  ROUND(SUM(c.consumodefinitivo)/COUNT(1),2) consumopromedio ");
		sb.append(" FROM ta_consumos c, ta_instalacion i ");
		sb.append("WHERE i.nminstalacion = c.nminstalacion ");
		sb.append("  AND c.ciclo = ");
		sb.append(filter.getCiclo());
		sb.append(" GROUP BY c.ciclo, (SELECT r.nombre FROM ta_ramal r WHERE r.cdramal = i.cdramal) ");
		sb.append(" ORDER BY 1, 2 ");
		return sb.toString();
	}
	
	public static String getCarteraTotal(Long ciclo){
		StringBuilder sb = new StringBuilder();
		
		sb.append("select '10' orden, u.cedula, trim(u.nombres) || ' ' || trim(u.apellidos) nombre, fm.nminstalacion instalacion, c.fecha, fm.nmfactura comprobante, sum(fd.valor+fd.saldo) valor,  "); 
		sb.append("    fm.sncancelado cancelado, fm.cuentasvencidas ");
		sb.append("from ta_factura_maestro fm, ta_factura_detalle fd, ta_usuarios u, ta_ciclos c ");
		sb.append("where fm.nmfactura = fd.nmfactura  ");
		sb.append("and fm.cedula = u.cedula ");
		sb.append("and fm.ciclo = c.ciclo ");
		sb.append("and fd.ciclo = c.ciclo ");
		sb.append("and c.ciclo = ");
		sb.append(ciclo);
		sb.append(" and fm.sncancelado = 'N' ");
		sb.append("and c.snestado = 'CERRADO' ");
		sb.append("GROUP BY c.ciclo, u.cedula, fm.nminstalacion, fm.nmfactura ");
		sb.append("UNION ALL  ");
		sb.append("SELECT '20', u.cedula, trim(u.nombres) || ' ' || trim(u.apellidos) nombre, cr.nminstalacion, cr.fecha, cr.nmcredito, ");
		sb.append("   (cr.valor-cr.inicial)- ((cr.valor-cr.inicial)/cr.cuotas) * (c.ciclo-cr.ciclo+1),  'N' Cancelado, 0 CV ");
		sb.append("FROM ta_credito_maestro cr, ta_instalacion i, ta_usuarios u, ta_ciclos c, ta_sistema s ");
		sb.append("where cr.nminstalacion = i.nminstalacion ");
		sb.append("and i.cedula = u.cedula  ");
		sb.append("and cr.ciclo is not null ");
		sb.append("and c.ciclo = ");
		sb.append(ciclo);
		sb.append(" and cr.snfinanciado = 'N'  ");
		sb.append("and c.snestado = 'CERRADO' ");
		sb.append("and cr.fechafinal is null  ");
		sb.append("and ((cr.valor-cr.inicial) - ((cr.valor-cr.inicial)/cr.cuotas)*(c.ciclo-cr.ciclo+1)) >0 ");
		sb.append("order by 2, 4, 1; ");
		return sb.toString();
	}
	
	public static String getConsolidadoConcepto(Filter filter){
		StringBuilder sb = new StringBuilder();
		sb.append(" SELECT 10 as orden, fd.ciclo, fd.cdconcepto codigo, fd.nombreconcepto, sum(valor) venta, sum(saldo) cartera");
		sb.append("   FROM ta_factura_detalle fd");
		sb.append("  WHERE fd.cdconcepto IN (SELECT idcargofijo FROM ta_sistema)");
		sb.append("   AND fd.ciclo = ");
		sb.append(filter.getCiclo());
		sb.append(" GROUP BY fd.ciclo, fd.cdconcepto, fd.nombreconcepto");
		sb.append(" UNION ALL");
		sb.append(" SELECT 20, fd.ciclo, fd.cdconcepto, fd.nombreconcepto, sum(valor) venta, sum(saldo) cartera");
		sb.append("   FROM ta_factura_detalle fd");
		sb.append("  WHERE fd.cdconcepto IN (SELECT idbasico FROM ta_sistema)");
		sb.append("   AND fd.ciclo = ");
		sb.append(filter.getCiclo());
		sb.append(" GROUP BY fd.ciclo, fd.cdconcepto, fd.nombreconcepto");
		sb.append(" UNION ALL");
		sb.append(" SELECT 30, fd.ciclo, fd.cdconcepto, fd.nombreconcepto, sum(valor) venta, sum(saldo) cartera");
		sb.append("   FROM ta_factura_detalle fd");
		sb.append("  WHERE fd.cdconcepto IN (SELECT idcomplementario FROM ta_sistema)");
		sb.append("   AND fd.ciclo = ");
		sb.append(filter.getCiclo());
		sb.append(" GROUP BY fd.ciclo, fd.cdconcepto, fd.nombreconcepto");
		sb.append(" UNION ALL");
		sb.append(" SELECT 40, fd.ciclo, fd.cdconcepto, fd.nombreconcepto, sum(valor) venta, sum(saldo) cartera");
		sb.append("   FROM ta_factura_detalle fd");
		sb.append("  WHERE fd.cdconcepto IN (SELECT idsuntuario FROM ta_sistema)");
		sb.append("   AND fd.ciclo = ");
		sb.append(filter.getCiclo());
		sb.append(" GROUP BY fd.ciclo, fd.cdconcepto, fd.nombreconcepto");
		sb.append(" UNION ALL");
		sb.append(" SELECT 50, fd.ciclo, fd.cdconcepto, fd.nombreconcepto, sum(valor) venta, sum(saldo) cartera");
		sb.append("   FROM ta_factura_detalle fd");
		sb.append("  WHERE fd.cdconcepto IN (SELECT idexceso FROM ta_sistema)");
		sb.append("   AND fd.ciclo = ");
		sb.append(filter.getCiclo());
		sb.append(" GROUP BY fd.ciclo, fd.cdconcepto, fd.nombreconcepto");
		sb.append(" UNION ALL");
		sb.append(" select 60, c.ciclo, t.cdconcepto, t.descripcion || ' VENTA DE CONTADO', sum(lp.valor) venta, 0 cartera");
		sb.append(" from ta_comprobante_pago cp, ta_log_pagos lp, ta_usuarios u, ta_ciclos c, ta_tarifas t, ta_sistema s");
		sb.append(" where cp.cedula = u.cedula");
		sb.append(" and cp.nmcomprobante = lp.nmfactura");
		sb.append(" and t.cdconcepto = s.idderecho");
		sb.append(" and lp.fehasta between c.fecha and c.fecha+30");
		sb.append(" and snmatricula = 'S' and sncancelado = 'S'");
		sb.append(" and lp.valor = t.estrato0");
		sb.append(" and lp.snerror = 'N'");
		sb.append("   AND c.ciclo = ");
		sb.append(filter.getCiclo());
		sb.append(" GROUP BY c.ciclo, t.cdconcepto, t.descripcion");
		sb.append(" UNION ALL");
		sb.append(" SELECT 60, c.ciclo, t.cdconcepto, t.descripcion || ' VENTA A CRÉDITO CON INICIAL', sum(cr.valor) venta, 0 cartera ");
		sb.append("   FROM ta_comprobante_pago cp, ta_log_pagos lp, ta_credito_maestro cr, ta_instalacion i, ta_usuarios u, ta_ciclos c, ta_tarifas t, ta_sistema s");
		sb.append("   where cp.cedula = u.cedula");
		sb.append("   and cr.nminstalacion = i.nminstalacion");
		sb.append("   and i.cedula = u.cedula");
		sb.append("   and cp.nmcomprobante = lp.nmfactura");
		sb.append("   and t.cdconcepto = s.idderecho");
		sb.append("   and cr.cdconcepto = s.idderecho");
		sb.append("   and c.ciclo = cr.ciclo");
		sb.append("   and snmatricula = 'S' and sncancelado = 'S'");
		sb.append("   and cr.fecha between c.fecha and c.fecha+30");
		sb.append("   and lp.valor <> t.estrato0");
		sb.append("   and lp.valor = cr.inicial ");
		sb.append("   and lp.snerror = 'N'");
		sb.append("   AND c.ciclo = ");
		sb.append(filter.getCiclo());
		sb.append(" group by c.ciclo, t.cdconcepto, t.descripcion");
		sb.append(" UNION ALL");
		sb.append(" SELECT 60, c.ciclo, t.cdconcepto, t.descripcion || ' VENTA A CRÉDITO SIN INICIAL', sum(cr.valor) venta, 0 cartera ");
		sb.append("   FROM  ta_credito_maestro cr, ta_instalacion i, ta_usuarios u, ta_ciclos c, ta_tarifas t, ta_sistema s");
		sb.append("   where cr.nminstalacion = i.nminstalacion");
		sb.append("   and i.cedula = u.cedula");
		sb.append("   and t.cdconcepto = s.idderecho");
		sb.append("   and cr.cdconcepto = s.idderecho");
		sb.append("   and c.ciclo = cr.ciclo");
		sb.append("   AND c.ciclo = ");
		sb.append(filter.getCiclo());
		sb.append("   and cr.inicial = 0");
		sb.append("   group by c.ciclo, t.cdconcepto, t.descripcion");
		sb.append(" UNION ALL");
		sb.append(" SELECT 65, fd.ciclo, fd.cdconcepto, fd.nombreconcepto || ' RECUPERADA EN LAS CUOTAS', sum(valor) venta, sum(saldo) cartera");
		sb.append("   FROM ta_factura_detalle fd");
		sb.append("  WHERE fd.cdconcepto IN (SELECT idderecho FROM ta_sistema) ");
		sb.append("   AND fd.ciclo = ");
		sb.append(filter.getCiclo());
		sb.append(" GROUP BY fd.ciclo, fd.cdconcepto, fd.nombreconcepto");
		sb.append(" UNION ALL");
		sb.append(" SELECT 70, fd.ciclo, fd.cdconcepto, fd.nombreconcepto, sum(valor) venta, sum(saldo) cartera");
		sb.append("   FROM ta_factura_detalle fd");
		sb.append("  WHERE fd.cdconcepto IN (SELECT idinteres FROM ta_sistema) ");
		sb.append("   AND fd.ciclo = ");
		sb.append(filter.getCiclo());
		sb.append(" GROUP BY fd.ciclo, fd.cdconcepto, fd.nombreconcepto");
		sb.append(" UNION ALL");
		sb.append(" SELECT 80, fd.ciclo, fd.cdconcepto, fd.nombreconcepto, sum(valor) venta, sum(saldo) cartera");
		sb.append("   FROM ta_factura_detalle fd");
		sb.append("  WHERE fd.cdconcepto IN (SELECT idrecargo FROM ta_sistema) ");
		sb.append("   AND fd.ciclo = ");
		sb.append(filter.getCiclo());
		sb.append(" GROUP BY fd.ciclo, fd.cdconcepto, fd.nombreconcepto");
		sb.append(" UNION ALL");
		sb.append(" SELECT 90, fd.ciclo, fd.cdconcepto, fd.nombreconcepto, sum(valor) venta, sum(saldo) cartera");
		sb.append("   FROM ta_factura_detalle fd");
		sb.append("  WHERE fd.cdconcepto IN (SELECT idreconexion FROM ta_sistema)");
		sb.append("   AND fd.ciclo = ");
		sb.append(filter.getCiclo());
		sb.append(" GROUP BY fd.ciclo, fd.cdconcepto, fd.nombreconcepto");
		sb.append(" UNION ALL");
		sb.append(" SELECT 150, fd.ciclo, fd.cdconcepto, fd.nombreconcepto, sum(valor) venta, sum(saldo) cartera");
		sb.append("   FROM ta_factura_detalle fd");
		sb.append("  WHERE fd.cdconcepto NOT IN (SELECT idcargofijo FROM ta_sistema)");
		sb.append("    AND fd.cdconcepto NOT IN (SELECT idbasico FROM ta_sistema)");
		sb.append("    AND fd.cdconcepto NOT IN (SELECT idcomplementario FROM ta_sistema)");
		sb.append("    AND fd.cdconcepto NOT IN (SELECT idsuntuario FROM ta_sistema)");
		sb.append("    AND fd.cdconcepto NOT IN (SELECT idexceso FROM ta_sistema)");
		sb.append("    AND fd.cdconcepto NOT IN (SELECT idderecho FROM ta_sistema)");
		sb.append("    AND fd.cdconcepto NOT IN (SELECT idinteres FROM ta_sistema)");
		sb.append("    AND fd.cdconcepto NOT IN (SELECT idrecargo FROM ta_sistema)");
		sb.append("    AND fd.cdconcepto NOT IN (SELECT idreconexion FROM ta_sistema)");
		sb.append("   AND fd.ciclo = ");
		sb.append(filter.getCiclo());
		sb.append(" GROUP BY fd.ciclo, fd.cdconcepto, fd.nombreconcepto");
		sb.append(" ORDER BY 1, 2, 3, 4;");
		
		/*
		sb.append("SELECT 10 orden, fd.ciclo, fd.cdconcepto codigo, fd.nombreconcepto, sum(valor) venta, sum(saldo) cartera ");
		sb.append("  FROM ta_factura_detalle fd ");
		sb.append(" WHERE fd.cdconcepto IN (SELECT idcargofijo FROM ta_sistema) ");
		sb.append("   AND fd.ciclo = ");
		sb.append(filter.getCiclo());
		sb.append(" GROUP BY fd.ciclo, fd.cdconcepto, fd.nombreconcepto ");
		sb.append("UNION ALL ");
		sb.append("SELECT 20 orden, fd.ciclo, fd.cdconcepto, fd.nombreconcepto, sum(valor) venta, sum(saldo) cartera ");
		sb.append("  FROM ta_factura_detalle fd ");
		sb.append(" WHERE fd.cdconcepto IN (SELECT idbasico FROM ta_sistema) ");
		sb.append("   AND fd.ciclo = ");
		sb.append(filter.getCiclo());
		sb.append(" GROUP BY fd.ciclo, fd.cdconcepto, fd.nombreconcepto ");
		sb.append("UNION ALL ");
		sb.append("SELECT 30 orden, fd.ciclo, fd.cdconcepto, fd.nombreconcepto, sum(valor) venta, sum(saldo) cartera ");
		sb.append("  FROM ta_factura_detalle fd ");
		sb.append(" WHERE fd.cdconcepto IN (SELECT idcomplementario FROM ta_sistema) ");
		sb.append("   AND fd.ciclo = ");
		sb.append(filter.getCiclo());
		sb.append(" GROUP BY fd.ciclo, fd.cdconcepto, fd.nombreconcepto ");
		sb.append("UNION ALL ");
		sb.append("SELECT 40 orden, fd.ciclo, fd.cdconcepto, fd.nombreconcepto, sum(valor) venta, sum(saldo) cartera ");
		sb.append("  FROM ta_factura_detalle fd ");
		sb.append(" WHERE fd.cdconcepto IN (SELECT idsuntuario FROM ta_sistema) ");
		sb.append("   AND fd.ciclo = ");
		sb.append(filter.getCiclo());
		sb.append(" GROUP BY fd.ciclo, fd.cdconcepto, fd.nombreconcepto ");
		sb.append("UNION ALL ");
		sb.append("SELECT 50 orden, fd.ciclo, fd.cdconcepto, fd.nombreconcepto, sum(valor) venta, sum(saldo) cartera ");
		sb.append("  FROM ta_factura_detalle fd ");
		sb.append(" WHERE fd.cdconcepto IN (SELECT idexceso FROM ta_sistema) ");
		sb.append("   AND fd.ciclo = ");
		sb.append(filter.getCiclo());
		sb.append(" GROUP BY fd.ciclo, fd.cdconcepto, fd.nombreconcepto ");
		sb.append("UNION ALL ");
		sb.append("select 60 orden, c.ciclo, t.cdconcepto, t.descripcion || ' VENTA DE CONTADO', sum(lp.valor) venta, 0 cartera ");
		sb.append("from ta_comprobante_pago cp, ta_log_pagos lp, ta_usuarios u, ta_ciclos c, ta_tarifas t, ta_sistema s ");
		sb.append("where cp.cedula = u.cedula ");
		sb.append("and cp.nmcomprobante = lp.nmfactura ");
		sb.append("and t.cdconcepto = s.idderecho ");
		sb.append("and lp.fehasta between c.fecha and c.fecha+30 ");
		sb.append("and snmatricula = 'S' and sncancelado = 'S' ");
		sb.append("and lp.valor = t.estrato0 ");
		sb.append("and lp.snerror = 'N' ");
		sb.append("and c.ciclo = ");
		sb.append(filter.getCiclo());
		sb.append(" GROUP BY c.ciclo, t.cdconcepto, t.descripcion ");
		sb.append("UNION ALL ");
		sb.append("SELECT 60 orden, c.ciclo, t.cdconcepto, t.descripcion || ' VENTA A CRÉDITO CON INICIAL', sum(cr.valor) venta, 0 cartera ");
		sb.append("  FROM ta_comprobante_pago cp, ta_log_pagos lp, ta_credito_maestro cr, ta_instalacion i, ta_usuarios u, ta_ciclos c, ta_tarifas t, ta_sistema s ");
		sb.append("  where cp.cedula = u.cedula ");
		sb.append("  and cr.nminstalacion = i.nminstalacion ");
		sb.append("  and i.cedula = u.cedula ");
		sb.append("  and cp.nmcomprobante = lp.nmfactura ");
		sb.append("  and t.cdconcepto = s.idderecho ");
		sb.append("  and cr.cdconcepto = s.idderecho ");
		sb.append("  and c.ciclo = cr.ciclo ");
		sb.append("  and snmatricula = 'S' and sncancelado = 'S' ");
		sb.append("  and cr.fecha between c.fecha and c.fecha+30 ");
		sb.append("  and lp.valor <> t.estrato0 ");
		sb.append("  and lp.valor = cr.inicial ");
		sb.append("and lp.snerror = 'N' ");
		sb.append("  and c.ciclo = ");
		sb.append(filter.getCiclo());
		sb.append(" group by c.ciclo, t.cdconcepto, t.descripcion ");
		sb.append("UNION ALL ");
		sb.append("SELECT 60 orden, c.ciclo, t.cdconcepto, t.descripcion || ' VENTA A CRÉDITO SIN INICIAL', sum(cr.valor) venta, 0 cartera ");
		sb.append("  FROM  ta_credito_maestro cr, ta_instalacion i, ta_usuarios u, ta_ciclos c, ta_tarifas t, ta_sistema s ");
		sb.append("  where cr.nminstalacion = i.nminstalacion ");
		sb.append("  and i.cedula = u.cedula ");
		sb.append("  and t.cdconcepto = s.idderecho ");
		sb.append("  and cr.cdconcepto = s.idderecho ");
		sb.append("  and c.ciclo = cr.ciclo ");
		sb.append("  and c.ciclo = ");
		sb.append(filter.getCiclo());
		sb.append("  and cr.inicial = 0 ");
		sb.append("  group by c.ciclo, t.cdconcepto, t.descripcion ");
		sb.append("UNION ALL ");
		sb.append("SELECT 70 orden, fd.ciclo, fd.cdconcepto, fd.nombreconcepto, sum(valor) venta, sum(saldo) cartera ");
		sb.append("  FROM ta_factura_detalle fd ");
		sb.append(" WHERE fd.cdconcepto IN (SELECT idinteres FROM ta_sistema) ");
		sb.append("   AND fd.ciclo = ");
		sb.append(filter.getCiclo());
		sb.append(" GROUP BY fd.ciclo, fd.cdconcepto, fd.nombreconcepto ");
		sb.append("UNION ALL ");
		sb.append("SELECT 80 orden, fd.ciclo, fd.cdconcepto, fd.nombreconcepto, sum(valor) venta, sum(saldo) cartera ");
		sb.append("  FROM ta_factura_detalle fd ");
		sb.append(" WHERE fd.cdconcepto IN (SELECT idrecargo FROM ta_sistema) ");
		sb.append("   AND fd.ciclo = ");
		sb.append(filter.getCiclo());
		sb.append(" GROUP BY fd.ciclo, fd.cdconcepto, fd.nombreconcepto ");
		sb.append("UNION ALL ");
		sb.append("SELECT 90 orden, fd.ciclo, fd.cdconcepto, fd.nombreconcepto, sum(valor) venta, sum(saldo) cartera ");
		sb.append("  FROM ta_factura_detalle fd ");
		sb.append(" WHERE fd.cdconcepto IN (SELECT idreconexion FROM ta_sistema) ");
		sb.append("   AND fd.ciclo = ");
		sb.append(filter.getCiclo());
		sb.append(" GROUP BY fd.ciclo, fd.cdconcepto, fd.nombreconcepto ");
		sb.append("UNION ALL ");
		sb.append("SELECT 150 orden, fd.ciclo, fd.cdconcepto, fd.nombreconcepto, sum(valor) venta, sum(saldo) cartera ");
		sb.append("  FROM ta_factura_detalle fd ");
		sb.append(" WHERE fd.cdconcepto NOT IN (SELECT idcargofijo FROM ta_sistema) ");
		sb.append("   AND fd.cdconcepto NOT IN (SELECT idbasico FROM ta_sistema) ");
		sb.append("   AND fd.cdconcepto NOT IN (SELECT idcomplementario FROM ta_sistema)   ");
		sb.append("   AND fd.cdconcepto NOT IN (SELECT idsuntuario FROM ta_sistema) ");
		sb.append("   AND fd.cdconcepto NOT IN (SELECT idexceso FROM ta_sistema) ");
		sb.append("   AND fd.cdconcepto NOT IN (SELECT idderecho FROM ta_sistema) ");
		sb.append("   AND fd.cdconcepto NOT IN (SELECT idinteres FROM ta_sistema) ");
		sb.append("   AND fd.cdconcepto NOT IN (SELECT idrecargo FROM ta_sistema) ");
		sb.append("   AND fd.cdconcepto NOT IN (SELECT idreconexion FROM ta_sistema) ");
		sb.append("   AND fd.ciclo = ");
		sb.append(filter.getCiclo());
		sb.append(" GROUP BY fd.ciclo, fd.cdconcepto, fd.nombreconcepto ");
		sb.append("ORDER BY 1, 2, 3, 4; ");*/
		return sb.toString();
	}
	
	public static String getDetalleRecaudo(Filter filter){
		StringBuilder sb = new StringBuilder();
		sb.append("SELECT ci.fecha AS fechafactura, b.nombre banco, c.numerocuenta, lp.fehasta, fm.nminstalacion AS instalacion, fm.nombres, lp.valor ");
		sb.append(" FROM ta_log_pagos lp, ta_factura_maestro fm, ta_cuentas c, ta_bancos b, ta_ciclos ci ");
		sb.append("WHERE lp.nmfactura = fm.nmfactura ");
		sb.append("  AND c.codigo = to_number(lp.cdcuenta,'99') ");
		sb.append("  AND c.nmbanco = b.nmbanco ");
		sb.append("  AND fm.ciclo = ci.ciclo ");
		sb.append("  AND (lp.snerror <> 'S' OR lp.snerror IS NULL) ");
		sb.append("  AND fm.ciclo = ");
		sb.append(filter.getCiclo());
		sb.append("  AND lp.fehasta >= '");
		sb.append(Formatter.createStringFromDate(filter.getFechaDesde(), Constantes.YYYY_MM_DD));
		sb.append("' AND lp.fehasta <= '");
		sb.append(Formatter.createStringFromDate(filter.getFechaHasta(), Constantes.YYYY_MM_DD));
		sb.append("' UNION ");
		sb.append("SELECT DATE((DATE_TRUNC('month', lp.fehasta) +INTERVAL '-1 MONTH')) fe_factura, b.nombre banco, c.numerocuenta, lp.fehasta, cp.nminstalacion, ");
		sb.append("  (CASE WHEN u.nombres IS NULL THEN u.apellidos ELSE u.nombres  || ' ' || u.apellidos END) nombre, ");
		sb.append("      lp.valor ");
		sb.append(" FROM ta_log_pagos lp, ta_comprobante_pago cp,  ta_usuarios u, ta_cuentas c, ta_bancos b ");
		sb.append("WHERE lp.nmfactura = cp.nmcomprobante ");
		sb.append("  AND c.codigo = to_number(lp.cdcuenta,'99') ");
		sb.append("  AND c.nmbanco = b.nmbanco ");
		sb.append("  AND cp.cedula = u.cedula ");
		sb.append("  AND cp.snmatricula = 'N' AND cp.sncancelado = 'S' ");
		sb.append("  AND (lp.snerror <> 'S' OR lp.snerror IS NULL) ");
		sb.append("  AND cp.nminstalacion IS NOT NULL ");
		sb.append("  AND lp.fehasta >= '");
		sb.append(Formatter.createStringFromDate(filter.getFechaDesde(), Constantes.YYYY_MM_DD));
		sb.append("' AND lp.fehasta <= '");
		sb.append(Formatter.createStringFromDate(filter.getFechaHasta(), Constantes.YYYY_MM_DD));
		sb.append("' UNION ");
		sb.append("SELECT DATE((DATE_TRUNC('month', lp.fehasta) +INTERVAL '-1 MONTH')) fe_factura, b.nombre banco, c.numerocuenta, lp.fehasta, 9999, ");
		sb.append("  (CASE WHEN u.nombres IS NULL THEN u.apellidos ELSE u.nombres  || ' ' || u.apellidos END) nombre, ");
		sb.append("      lp.valor ");
		sb.append(" FROM ta_log_pagos lp, ta_comprobante_pago cp,  ta_usuarios u, ta_cuentas c, ta_bancos b ");
		sb.append("WHERE lp.nmfactura = cp.nmcomprobante ");
		sb.append("  AND c.codigo = to_number(lp.cdcuenta,'99') ");
		sb.append("  AND c.nmbanco = b.nmbanco ");
		sb.append("  AND cp.cedula = u.cedula ");
		sb.append("  AND cp.snmatricula = 'S' AND cp.sncancelado = 'S' ");
		sb.append("  AND (lp.snerror <> 'S' OR lp.snerror IS NULL) ");
		sb.append("  AND cp.nminstalacion IS NULL ");
		sb.append("  AND lp.fehasta >= '");
		sb.append(Formatter.createStringFromDate(filter.getFechaDesde(), Constantes.YYYY_MM_DD));
		sb.append("' AND lp.fehasta <= '");
		sb.append(Formatter.createStringFromDate(filter.getFechaHasta(), Constantes.YYYY_MM_DD));
		sb.append("' ORDER BY 1, 2, 3, 4, 5 ");
		return sb.toString();
	}
	
	public static String getCartera(){
		StringBuilder sb = new StringBuilder();
		sb.append("SELECT cm.nminstalacion AS instalacion, ");
		sb.append("       (CASE WHEN u.nombres IS NULL THEN u.apellidos ELSE u.nombres  || ' ' || u.apellidos END) AS nombre, ");
		sb.append("       cm.cdconcepto AS codigo, ");
		sb.append("       (SELECT t.descripcion FROM ta_tarifas t WHERE t.cdconcepto = cm.cdconcepto) concepto, ");
		sb.append("       cm.saldo ");
		sb.append("  FROM ta_credito_maestro cm, ta_instalacion i, ta_usuarios u ");
		sb.append(" WHERE i.nminstalacion = cm.nminstalacion ");
		sb.append("   AND i.cedula = u.cedula ");
		sb.append("   AND cm.fechafinal IS NULL AND cm.saldo <> 0 ");
		sb.append("ORDER BY 3, 1 ");
		return sb.toString();
	}

	public static String getUsuariosMail() {
		StringBuilder sb = new StringBuilder();
		sb.append("SELECT u.cedula, u.email ");
		sb.append("  FROM ta_usuarios u ");
		sb.append("WHERE u.snenvioemail = 'S'");
		sb.append("AND u.cedula IN (SELECT i.cedula FROM ta_instalacion i)");
		return sb.toString();
	}

	public static String getArticulos() {
		StringBuilder sb = new StringBuilder();
		sb.append("SELECT a.codigocontable ");
		sb.append("  FROM ta_articulo a ");
		return sb.toString();
	}
	
	public static String getKardex(Long ciclo){
		StringBuilder sb = new StringBuilder();
		sb.append("SELECT kx.ciclo, kx.feingreso fecha, kx.nmarticulo codigo, a.nombre articulo, kx.saldoanterior saldoInicial, ");
		sb.append("cantidadentrada entrada, ");
		sb.append("CASE cantidadentrada WHEN 0 then 0 ELSE preciounitario END precioEntrada, ");
		sb.append("cantidadsalida salida, ");
		sb.append("CASE cantidadsalida WHEN 0 then 0 ELSE preciounitario END precioSalida, ");
		sb.append("saldoactual saldoFinal, saldoactual*preciounitario valorSaldo ");
		sb.append("FROM ta_kardex kx, ta_articulo a ");
		sb.append("WHERE kx.nmarticulo = a.nmarticulo AND kx.ciclo = ");
		sb.append(ciclo);
		sb.append(" AND tipo in ('2','3') ");
		sb.append("ORDER BY kx.nmarticulo, kx.nmkardex;");
		return sb.toString();
	}
	
	public static String getVentasMatricula(Long ciclo) {
		StringBuilder sb = new StringBuilder();
		sb.append("select c.ciclo, lp.fehasta fechapago, cp.cedula, trim(u.nombres) || ' ' || trim(u.apellidos) nombre, trim(t.descripcion) || ' VENTA DE CONTADO' concepto, lp.valor, 0 inicial ");  
		sb.append("from ta_comprobante_pago cp, ta_log_pagos lp, ta_usuarios u, ta_ciclos c, ta_tarifas t, ta_sistema s ");
		sb.append("where cp.cedula = u.cedula ");
		sb.append("and cp.nmcomprobante = lp.nmfactura ");
		sb.append("and t.cdconcepto = s.idderecho ");
		sb.append("and lp.fehasta between c.fecha and c.fecha+30 ");
		sb.append("and snmatricula = 'S' and sncancelado = 'S' ");
		sb.append("and lp.valor = t.estrato0 ");
		sb.append("and c.ciclo = ");
		sb.append(ciclo);
		sb.append(" UNION ");
		sb.append("SELECT c.ciclo, lp.fehasta fechapago, u.cedula, trim(u.nombres) || ' ' || trim(u.apellidos) nombre, trim(t.descripcion) || ' VENTA A CRÉDITO' concepto, cr.valor, cr.inicial ");
		sb.append("  FROM ta_comprobante_pago cp, ta_log_pagos lp, ta_credito_maestro cr, ta_instalacion i, ta_usuarios u, ta_ciclos c, ta_tarifas t, ta_sistema s ");
		sb.append("  where cp.cedula = u.cedula ");
		sb.append("  and cr.nminstalacion = i.nminstalacion ");
		sb.append("  and i.cedula = u.cedula ");
		sb.append("  and cp.nmcomprobante = lp.nmfactura ");
		sb.append("  and t.cdconcepto = s.idderecho ");
		sb.append("  and cr.cdconcepto = s.idderecho ");
		sb.append("  and c.ciclo = cr.ciclo ");
		sb.append("  and snmatricula = 'S' and sncancelado = 'S' ");
		sb.append("  and cr.fecha between c.fecha and c.fecha+30 ");
		sb.append("  and lp.valor <> t.estrato0 ");
		sb.append("  and lp.valor = cr.inicial ");
		sb.append("  and c.ciclo = ");
		sb.append(ciclo);
		sb.append(" UNION ");
		sb.append("SELECT c.ciclo, cr.fecha fechapago, u.cedula, trim(u.nombres) || ' ' || trim(u.apellidos) nombre, trim(t.descripcion) || ' VENTA A CRÉDITO' concepto, cr.valor, cr.inicial ");
		sb.append("  FROM  ta_credito_maestro cr, ta_instalacion i, ta_usuarios u, ta_ciclos c, ta_tarifas t, ta_sistema s ");
		sb.append("  where cr.nminstalacion = i.nminstalacion ");
		sb.append("  and i.cedula = u.cedula ");
		sb.append("  and t.cdconcepto = s.idderecho ");
		sb.append("  and cr.cdconcepto = s.idderecho ");
		sb.append("  and c.ciclo = cr.ciclo ");
		sb.append("  and c.ciclo = ");
		sb.append(ciclo);
		sb.append("  and cr.inicial = 0 ");
		sb.append(" order by 1, 2, 3, 4;");
		return sb.toString();
	}

}
