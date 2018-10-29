package co.com.siav.pagos;

import javax.inject.Inject;

import co.com.siav.entities.Factura;
import co.com.siav.entities.FacturaVencida;
import co.com.siav.repositories.IRepositoryFacturaVencida;
import co.com.siav.repositories.IRepositoryFacturas;
import co.com.siav.utils.Constantes;
import co.com.siav.utils.FacturaUtil;

public class Vencidas {
	
	@Inject
	private IRepositoryFacturaVencida vencidasRep;
	
	@Inject
	private IRepositoryFacturas facturasRep;

	public void calcular(Factura factura) {
		Factura anterior = facturasRep.findByNumeroInstalacionAndCiclo(factura.getNumeroInstalacion(), factura.getCiclo() - 1L);
		FacturaVencida vencida = new FacturaVencida();
		vencida.setNumeroFactura(anterior.getNumeroFactura());
		vencida.setNumeroInstalacion(anterior.getNumeroInstalacion());
		vencida.setCiclo(anterior.getCiclo());
		vencida.setValor(FacturaUtil.getValor(anterior));
		try{
			vencidasRep.save(vencida);
		}catch(Exception e){
			System.out.println(Constantes.ERROR_VENCIDA + factura.getNumeroInstalacion());
		}		
	}
}
