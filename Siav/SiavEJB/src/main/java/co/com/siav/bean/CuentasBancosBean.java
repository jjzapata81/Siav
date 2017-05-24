package co.com.siav.bean;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import org.springframework.beans.BeanUtils;

import co.com.siav.dto.CuentaBancoDTO;
import co.com.siav.entities.CuentaBanco;
import co.com.siav.repositories.IRepositoryCuentasBancos;
import co.com.siav.response.EstadoEnum;
import co.com.siav.response.MensajeResponse;
import co.com.siav.utils.Constantes;

@Stateless
public class CuentasBancosBean {
	
	@Inject
	private IRepositoryCuentasBancos cuentasRep;
	
	public List<CuentaBanco> consultar() {
		return cuentasRep.findAll();
	}
	
	public MensajeResponse crear(CuentaBancoDTO request) {
		if(!cuentasRep.findByNombre(request.getNombre().toUpperCase().trim()).isEmpty()){
			return new MensajeResponse(EstadoEnum.ERROR, "Ya existe una cuenta con el nombre " + request.getNombre().trim() + ".");
		}else if(!cuentasRep.findByNumeroCuenta(request.getNumeroCuenta().trim()).isEmpty()){
			return new MensajeResponse(EstadoEnum.ERROR, "Ya existe una cuenta con el número " + request.getNumeroCuenta().trim() + ".");
		}
		cuentasRep.save(crearCuenta(request));
		return new MensajeResponse("Se creó la cuenta " + request.getNombre() + " " + request.getNumeroCuenta() + ".");
		
	}
	
	public MensajeResponse editar(CuentaBancoDTO request) {
		CuentaBanco cuentaBD = cuentasRep.findOne(request.getCodigo());
		cuentaBD.setNombre(request.getNombre());
		cuentaBD.setNumeroCuenta(request.getNumeroCuenta());
		cuentasRep.save(cuentaBD);
		return new MensajeResponse(Constantes.ACTUALIZACION_EXITO);
	}

	private CuentaBanco crearCuenta(CuentaBancoDTO cuenta) {
		CuentaBanco cuentaBD = new CuentaBanco();
		BeanUtils.copyProperties(cuenta, cuentaBD);
		return cuentaBD;
	}

	

}
