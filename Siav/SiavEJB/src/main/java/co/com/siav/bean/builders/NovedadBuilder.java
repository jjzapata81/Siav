package co.com.siav.bean.builders;

import co.com.siav.entities.Novedad;
import co.com.siav.entities.NovedadPK;

public class NovedadBuilder {
	
	public static NovedadPK crearPK(Long ciclo, Long instalacion, String codigoConcepto){
		NovedadPK pk = new NovedadPK();
		pk.setCiclo(ciclo);
		pk.setInstalacion(instalacion);
		pk.setCodigoConcepto(codigoConcepto);
		return pk;
	}
	
	public static Novedad crear(NovedadPK pk, Double valor, boolean borrable){
		return crear(pk, Math.round(valor), borrable);
	}
	
	public static Novedad crear(NovedadPK pk, Long valor, boolean borrable){
		Novedad novedad = new Novedad();
		novedad.setId(pk);
		novedad.setValor(valor);
		novedad.setBorrable(borrable);
		return novedad;
	}

}
