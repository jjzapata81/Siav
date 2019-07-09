package co.com.siav.utility;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import co.com.siav.exception.TechnicalException;
import co.com.siav.pdf.dto.Articulo;
import co.com.siav.pdf.dto.CobroPDF;
import co.com.siav.pdf.dto.ConsumoPDF;
import co.com.siav.pdf.dto.CreditoPDF;
import co.com.siav.pdf.dto.FacturaDetalleBD;
import co.com.siav.pdf.dto.MaterialesPDF;
import co.com.siav.pdf.dto.ValoresFacturados;
import co.com.siav.repository.QueryHelper;
import co.com.siav.repository.ReportBDFactory;
import co.com.siav.repository.entities.Sistema;

public final class FacturaUtil {

	private FacturaUtil(){}
	
	public static List<ValoresFacturados> getValoresFacturados(List<FacturaDetalleBD> detalles, Sistema sistema) {
		List<ValoresFacturados> valores = new ArrayList<ValoresFacturados>();
		ValoresFacturados item = detalles.stream().filter(detalle -> 
			sistema.getCargoFijo().equals(detalle.getCodigoConcepto())).findFirst().map(FacturaUtil::crearValorFacturado).orElse(null);
		if(null!=item)
			valores.add(item);
		item = detalles.stream().filter(detalle -> 
			sistema.getBasico().equals(detalle.getCodigoConcepto())).findFirst().map(FacturaUtil::crearValorFacturado).orElse(null);
		if(null!=item)
			valores.add(item);
		item = detalles.stream().filter(detalle -> 
			sistema.getComplementario().equals(detalle.getCodigoConcepto())).findFirst().map(FacturaUtil::crearValorFacturado).orElse(null);
		if(null!=item)
			valores.add(item);
		item = detalles.stream().filter(detalle -> 
			sistema.getSuntuario().equals(detalle.getCodigoConcepto())).findFirst().map(FacturaUtil::crearValorFacturado).orElse(null);
		if(null!=item)
		valores.add(item);
		return valores;
	}
	
	private static ValoresFacturados crearValorFacturado(FacturaDetalleBD detalle) {
		try{
			ValoresFacturados vf = new ValoresFacturados();
			vf.setConcepto(detalle.getNombreConcepto());
			vf.setValor(detalle.getValor());
			if(null != detalle.getMetros() && detalle.getMetros()>0L){
				vf.setM3(detalle.getMetros());
				vf.setTarifa(getTarifa(detalle.getValor(), detalle.getMetros()));
			}
			return vf;
		}catch (Exception e){
			throw new TechnicalException(Constantes.ERR_VALORES_FACTURADOS + e.getMessage());
		}
	}
	
	private static Long getTarifa(Long valor, Long metros) {
		Double total = valor.doubleValue() / metros;
		return total.longValue();
	}
	
	public static List<CobroPDF> getOtrosCobros(List<FacturaDetalleBD> detalles, Sistema sistema, List<Articulo> articulos) {
		return detalles.stream().filter(detalle -> 
			!sistema.getCargoFijo().equals(detalle.getCodigoConcepto()) &&
			!sistema.getBasico().equals(detalle.getCodigoConcepto()) &&
			!sistema.getComplementario().equals(detalle.getCodigoConcepto()) &&
			!sistema.getSuntuario().equals(detalle.getCodigoConcepto()) &&
			!Constantes.CONCEPTO_IVA_VENTAS.equals(detalle.getCodigoConcepto()) &&
			!contains(detalle.getCodigoConcepto(), articulos) &&
			detalle.getCodigoCredito() == null &&
			detalle.getValor() != 0L).map(FacturaUtil::getCobro).collect(Collectors.toList());
	}
	
	public static List<MaterialesPDF> getMateriales(List<FacturaDetalleBD> detalles, Sistema sistema, List<Articulo> articulos) {
		return detalles.stream()
				.filter(detalle -> contains(detalle.getCodigoConcepto(), articulos))
				.filter(detalle -> detalle.getValor()!=0)
				.map(item -> getMaterial(item, sistema)).collect(Collectors.toList());
	}
	
	private static MaterialesPDF getMaterial(FacturaDetalleBD detalle, Sistema sistema){
		MaterialesPDF material = new MaterialesPDF();
		material.setDetalle(detalle.getNombreConcepto());
		material.setValor(detalle.getValor());
		material.setIva(sistema.getIva());
		return material;
	}
	
	public static boolean contains(String codigoConcepto, List<Articulo> articulos) {
		return articulos.stream().anyMatch(item-> item.getCodigoContable().equals(codigoConcepto));
	}

	private static CobroPDF getCobro(FacturaDetalleBD detalle){
		CobroPDF cobro = new CobroPDF();
		cobro.setDetalle(detalle.getNombreConcepto());
		cobro.setValor(detalle.getValor());
		return cobro;
	}
	
	public static List<ConsumoPDF> getConsumos(String historicoConsumos, String instalacion) {
		try{
			if(null != historicoConsumos){
				List<String> historico = Arrays.asList(historicoConsumos.split(";"));
				return historico.stream().map(ConsumoPDF::new).collect(Collectors.toList());
			}
			return new ArrayList<ConsumoPDF>();
		}catch(Exception e){
			throw new TechnicalException(Constantes.ERR_HISTORICO_CONSUMOS + instalacion);
		}
	}
	
	public static List<CreditoPDF> getCreditos(String numeroInstalacion) {
		String query = QueryHelper.getCreditos(numeroInstalacion);
		ReportBDFactory<CreditoPDF> factory = new ReportBDFactory<>();
		return factory.getReportResult(CreditoPDF.class, query);
	}

	public static double getIva(List<FacturaDetalleBD> detalles, Sistema sistema, List<Articulo> articulos) {
		return detalles.stream().filter(detalle -> contains(detalle.getCodigoConcepto(), articulos)).mapToLong(FacturaDetalleBD::getValor).sum() * sistema.getIva();
	}
	
	
}
