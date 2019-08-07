package co.com.siav.pagos;

import javax.inject.Inject;

import co.com.siav.entities.Factura;
import co.com.siav.entities.FacturaVencida;
import co.com.siav.repositories.IRepositoryFacturaVencida;
import co.com.siav.utils.Constantes;
import co.com.siav.utils.FacturaUtil;

public class Vencidas {
	
	@Inject
	private IRepositoryFacturaVencida vencidasRep;
	
	public void calcular(Factura factura) {
		FacturaVencida vencida = new FacturaVencida();
		vencida.setNumeroFactura(factura.getNumeroFactura());
		vencida.setNumeroInstalacion(factura.getNumeroInstalacion());
		vencida.setCiclo(factura.getCiclo());
		vencida.setValor(FacturaUtil.getValor(factura));
		if(vencida.getValor()>0){
			try{
				vencidasRep.save(vencida);
			}catch(Exception e){
				System.out.println(Constantes.ERROR_VENCIDA + factura.getNumeroInstalacion());
			}
		}
	}
}
