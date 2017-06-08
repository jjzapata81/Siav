package co.com.siav.bean;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import org.springframework.beans.BeanUtils;

import co.com.siav.dto.CuentaBancoDTO;
import co.com.siav.entities.Banco;
import co.com.siav.entities.CuentaBanco;
import co.com.siav.exception.ExcepcionNegocio;
import co.com.siav.repositories.IRepositoryBancos;
import co.com.siav.repositories.IRepositoryCuentasBancos;
import co.com.siav.response.EstadoEnum;
import co.com.siav.response.MensajeResponse;
import co.com.siav.utils.Constantes;

@Stateless
public class CuentasBancosBean {
	
	@Inject
	private IRepositoryCuentasBancos cuentasRep;
	
	@Inject
	private IRepositoryBancos bancosRep;
	
	public List<CuentaBanco> consultar() {
		return cuentasRep.findAll();
	}
	
	public MensajeResponse crear(CuentaBancoDTO request) {
		if(!cuentasRep.findByNombre(request.getNombre().toUpperCase().trim()).isEmpty()){
			return new MensajeResponse(EstadoEnum.ERROR, Constantes.getMensaje(Constantes.ERR_CUENTA_EXISTE, request.getNombre().trim()));
		}else if(!cuentasRep.findByNumeroCuenta(request.getNumeroCuenta().trim()).isEmpty()){
			return new MensajeResponse(EstadoEnum.ERROR, Constantes.getMensaje(Constantes.ERR_CUENTA_NUMERO_EXISTE, request.getNumeroCuenta().trim()));
		}
		if(bancosRep.findByNombre(request.getNombreBanco().toUpperCase().trim()).isEmpty()){
			Banco banco = new Banco();
			banco.setNombre(request.getNombreBanco().toUpperCase().trim());
			bancosRep.save(banco);
			Banco bancoBD = bancosRep.findByNombre(request.getNombreBanco().toUpperCase().trim()).stream().findFirst().orElseThrow(()-> new ExcepcionNegocio(Constantes.ERR_CREAR_BANCO));
			request.setCodigoBanco(bancoBD.getCodigo());
		}
		cuentasRep.save(crearCuenta(request));
		return new MensajeResponse(Constantes.getMensaje(Constantes.CREACION_CUENTA, request.getNombre(),request.getNumeroCuenta()));
		
	}
	
	public MensajeResponse editar(CuentaBancoDTO request) {
		if(bancosRep.findByNombre(request.getNombreBanco().toUpperCase().trim()).isEmpty()){
			Banco banco = new Banco();
			banco.setNombre(request.getNombreBanco().toUpperCase().trim());
			bancosRep.save(banco);
			Banco bancoBD = bancosRep.findByNombre(request.getNombreBanco().toUpperCase().trim()).stream().findFirst().orElseThrow(()-> new ExcepcionNegocio(Constantes.ERR_CREAR_BANCO));
			request.setCodigoBanco(bancoBD.getCodigo());
		}
		CuentaBanco cuentaBD = cuentasRep.findOne(request.getCodigo());
		cuentaBD.setNombre(request.getNombre());
		cuentaBD.setNumeroCuenta(request.getNumeroCuenta());
		cuentaBD.setCodigoBanco(request.getCodigoBanco());
		cuentasRep.save(cuentaBD);
		return new MensajeResponse(Constantes.ACTUALIZACION_EXITO);
	}

	private CuentaBanco crearCuenta(CuentaBancoDTO cuenta) {
		CuentaBanco cuentaBD = new CuentaBanco();
		BeanUtils.copyProperties(cuenta, cuentaBD);
		return cuentaBD;
	}

	public List<Banco> consultarBancos() {
		return bancosRep.findAll();
	}

	

}
