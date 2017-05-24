package co.com.siav.repository.report;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import co.com.siav.pdf.dto.CobroPDF;
import co.com.siav.pdf.dto.ConsumoPDF;
import co.com.siav.pdf.dto.CreditoPDF;
import co.com.siav.pdf.dto.DetalleInstalacionPDF;
import co.com.siav.pdf.dto.FacturaPDF;
import co.com.siav.pdf.dto.FacturacionPDF;
import co.com.siav.pdf.dto.InstalacionPDF;
import co.com.siav.pdf.dto.PrefacturaPDF;
import co.com.siav.pdf.dto.ValoresFacturados;

public class MockPDF {

	public static FacturacionPDF getReporteDummy() {
		FacturacionPDF re = new FacturacionPDF();
		re.setFacturas(getFacturas());
		return re;
	}
	
	private static List<FacturaPDF> getFacturas() {
		List<FacturaPDF> lista = new ArrayList<FacturaPDF>();
		FacturaPDF dummy;
		dummy = new FacturaPDF();
		dummy.setCiclo("220");
		dummy.setNombre("JOSE JULIAN ZAPATA ARBELAEZ");
		dummy.setCodigoBarras("123452434363546547474674641234");
		dummy.setNombreAcueducto("ASOCIACIÓN DE ACUEDUCTOS VEREDALES ACUEDUCTO GAVIRIA SAN JUAN BOSCO 1234556 5667");
		dummy.setNit("NIT 800.189.768-1");
		dummy.setInstalacion("1001");
		dummy.setVereda("Gaviria");
		dummy.setDireccion("Calle 123");
		dummy.setValoresFacturados(getFacturados());
		dummy.setOtrosCobros(getCobros());
		dummy.setConsumos(getConsumos());
		dummy.setMensajePrincipal("El agua es fuente de vida y la herencia de nuestros hijos, cuidemosla y racionemos su uso.");
		dummy.setNumeroFactura("1234567");
		dummy.setFePagoSinRecargo(new Date());
		dummy.setFePagoRecargo(new Date());
		dummy.setReferente("90800987776611");
		dummy.setValorTotal(65000L);
		dummy.setFechaUltimoPago(new Date());
		dummy.setConsumoAnterior(35L);
		dummy.setConsumoActual(33L);
		dummy.setLecturaActual(300L);
		dummy.setLecturaAnterior(267L);
		dummy.setPromedioConsumo(30L);
		dummy.setDiasConsumo(31L);
		dummy.setValorMesAnterior(58500L);
		dummy.setCuentasVencidas(0L);
		dummy.setCreditos(getCreditos());
		dummy.setResolucion("Resolución 547 del 2016/10/25");
		dummy.setMensajePuntoPago("Esta factura puede ser cancelada en Cotrafa - Cuenta de ahorros No. 67879788786");
		dummy.setMensajeReclamo("Estimado usuario: cualquier inquietud o inconformidad con su factura puede ser comunicada a través de la línea 548 3344, vía email a acueductos@veredales.com ó directamente en la oficina principal ubicada en la calle 123 # 34 – 23 local 201.");
		dummy.setEstrato("3");
		dummy.setTotalVencido(12500L);
		lista.add(dummy);
		return lista;
	}
	
	public static List<CreditoPDF> getCreditos() {
		List<CreditoPDF> lista = new ArrayList<CreditoPDF>();
		CreditoPDF credito;
		credito = new CreditoPDF();
		credito.setNumero(123L);
		credito.setNombre("Matrícula");
		credito.setTasa(0.12);
		credito.setCuotaActual(5L);
		credito.setCuotasPendientes(7L);
		credito.setInteres(12000L);
		credito.setValor(36000L);
		credito.setSaldo(840000L);
		lista.add(credito);
		credito = new CreditoPDF();
		credito.setNumero(124L);
		credito.setNombre("Reconexión");
		credito.setTasa(0.15);
		credito.setCuotaActual(1L);
		credito.setCuotasPendientes(11L);
		credito.setInteres(500L);
		credito.setValor(3500L);
		credito.setSaldo(98000L);
		lista.add(credito);
		return lista;
	}
	
	private static List<ValoresFacturados> getFacturados() {
		List<ValoresFacturados> lista = new ArrayList<ValoresFacturados>();
		ValoresFacturados val;
		val = new ValoresFacturados();
		val.setConcepto("Cargo fijo");
		val.setM3(0L);
		val.setTarifa(0L);
		val.setValor(12500L);
		lista.add(val);
		val = new ValoresFacturados();
		val.setConcepto("Básico");
		val.setM3(10L);
		val.setTarifa(500L);
		val.setValor(5000L);
		lista.add(val);
		val = new ValoresFacturados();
		val.setConcepto("Complementario");
		val.setM3(3L);
		val.setTarifa(1500L);
		val.setValor(4500L);
		lista.add(val);
		val = new ValoresFacturados();
		val.setConcepto("Suntuario");
		val.setM3(0L);
		val.setTarifa(2000L);
		val.setValor(0L);
		lista.add(val);
		return lista;
	}
	
	public static List<ConsumoPDF> getConsumos() {
		List<ConsumoPDF> list = new ArrayList<ConsumoPDF>();
		ConsumoPDF dummy;
		dummy = new ConsumoPDF();
		dummy.setCon(28);
		dummy.setMs("ene");
		list.add(dummy);
		dummy = new ConsumoPDF();
		dummy.setCon(35);
		dummy.setMs("feb");
		list.add(dummy);
		dummy = new ConsumoPDF();
		dummy.setCon(30);
		dummy.setMs("mar");
		list.add(dummy);
		dummy = new ConsumoPDF();
		dummy.setCon(37);
		dummy.setMs("abr");
		list.add(dummy);
		dummy = new ConsumoPDF();
		dummy.setCon(22);
		dummy.setMs("may");
		list.add(dummy);
		dummy = new ConsumoPDF();
		dummy.setCon(31);
		dummy.setMs("jun");
		list.add(dummy);
		return list;
	}
	
	private static List<CobroPDF> getCobros() {
		List<CobroPDF> cobros = new ArrayList<CobroPDF>();
		CobroPDF cb;
		cb = new CobroPDF();
		cb.setDetalle("Cualquier cosa");
		cb.setValor(500L);
		cobros.add(cb);
		cb = new CobroPDF();
		cb.setDetalle("Otra cosa");
		cb.setValor(1500L);
		cobros.add(cb);
		return cobros;
	}

	public static PrefacturaPDF getPrefactura() {
		PrefacturaPDF pre = new PrefacturaPDF();
		pre.setNombreAcueducto("ACUEDUCTO VEREDAL GAVIRIA - SAN JUAN BOSCO");
		pre.setNombreReporte("Prefacturación - Ciclo 220");
		pre.setInstalaciones(getInstalaciones());
		return pre;
	}

	private static List<InstalacionPDF> getInstalaciones() {
		List<InstalacionPDF> instalaciones = new ArrayList<InstalacionPDF>();
		InstalacionPDF instalacion;
		instalacion = new InstalacionPDF();
		instalacion.setNumero(1001L);
		instalacion.setNombres("JOSE JULIAN ZAPATA ARBELAEZ");
		instalacion.setCedula("70906385");
		instalacion.setFactura(12345L);
		instalacion.setConsumo(38L);
		instalacion.setLecturaActual(538L);
		instalacion.setLecturaAnterior(500L);
		instalacion.setCuentasVencidas(2L);
		instalacion.setDetalles(getDetalles());
		instalaciones.add(instalacion);
		instalaciones.add(instalacion);
		instalaciones.add(instalacion);
		instalaciones.add(instalacion);
		instalaciones.add(instalacion);
		instalaciones.add(instalacion);
		instalaciones.add(instalacion);
		instalaciones.add(instalacion);
		instalaciones.add(instalacion);
		instalaciones.add(instalacion);
		instalaciones.add(instalacion);
		instalaciones.add(instalacion);
		instalaciones.add(instalacion);
		instalaciones.add(instalacion);
		instalaciones.add(instalacion);
		instalaciones.add(instalacion);
		instalaciones.add(instalacion);
		instalaciones.add(instalacion);
		instalaciones.add(instalacion);
		instalaciones.add(instalacion);
		return instalaciones;
	}

	private static List<DetalleInstalacionPDF> getDetalles() {
		List<DetalleInstalacionPDF> detalles = new ArrayList<DetalleInstalacionPDF>();
		DetalleInstalacionPDF dt = new DetalleInstalacionPDF();
		dt.setCodigo("45202");
		dt.setConcepto("CARGO FIJO");
		dt.setValor(12500L);
		detalles.add(dt);
		detalles.add(dt);
		detalles.add(dt);
		return detalles;
	}
}
