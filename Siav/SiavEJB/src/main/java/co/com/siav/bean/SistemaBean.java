package co.com.siav.bean;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.ejb.Stateless;
import javax.inject.Inject;

import org.springframework.beans.BeanUtils;

import co.com.siav.entities.Empresa;
import co.com.siav.entities.Estructura;
import co.com.siav.entities.EstructuraPK;
import co.com.siav.entities.Usuario;
import co.com.siav.exception.ExcepcionNegocio;
import co.com.siav.repositories.IRepositoryEmpresa;
import co.com.siav.repositories.IRepositoryEstructura;
import co.com.siav.repositories.IRepositoryUsuarios;
import co.com.siav.request.EstructuraRequest;
import co.com.siav.response.EstadoEnum;
import co.com.siav.response.EstructuraResponse;
import co.com.siav.response.IpResponse;
import co.com.siav.response.MensajeResponse;
import co.com.siav.utils.Constantes;

@Stateless
public class SistemaBean {
	
	@Inject
	private IRepositoryEmpresa empresaRep;
	
	@Inject
	private IRepositoryUsuarios usuariosRep;
	
	@Inject
	private IRepositoryEstructura estructuraRep;
	
	public IpResponse consultarIp() {
		try {
			IpResponse response = new IpResponse();
			response.setIp(InetAddress.getLocalHost().getHostAddress());
			return response;
		} catch (UnknownHostException e) {
			throw new ExcepcionNegocio(Constantes.ERR_CONSULTAR_IP + e.getMessage());
		}
	}

	public Empresa consultarEmpresa() {
		return empresaRep.findOne(Constantes.ID_EMPRESA);
	}

	public MensajeResponse actualizarEmpresa(Empresa request) {
		try{
			Empresa empresa = empresaRep.findOne(request.getId());
			BeanUtils.copyProperties(request, empresa);
			empresaRep.save(empresa);
		}catch(Exception e){
			return new MensajeResponse(EstadoEnum.ERROR, Constantes.ACTUALIZACION_FALLO);
		}
		return new MensajeResponse(Constantes.ACTUALIZACION_EXITO);
	}

	public List<EstructuraResponse> consultarEstructura() {
		return estructuraRep.findByIdEmpresaAndActivo(Constantes.ID_EMPRESA, Constantes.SI)
		.stream().map(this::transform).collect(Collectors.toList());
	}
	
	private EstructuraResponse transform(Estructura estructura){
		EstructuraResponse response = new EstructuraResponse();
		Usuario usuario = usuariosRep.findOne(estructura.getId().getCedula());
		response.setCedula(usuario.getCedula());
		response.setNombre(usuario.getNombreCompleto());
		response.setEmail(usuario.getEmail());
		response.setTelefono(usuario.getCelular());
		response.setCargo(estructura.getId().getCargo());
		response.setActa(estructura.getActa());
		response.setEmpresa(estructura.getId().getEmpresa());
		response.setFecha(estructura.getId().getFecha());
		return response;
	}

	public MensajeResponse agregarJunta(Estructura request) {
		try{
			request.setId(construirPK(request.getId().getCedula(), request.getId().getCargo(), request.getId().getFecha()));
			if(estructuraRep.exists(request.getId())){
				return new MensajeResponse(EstadoEnum.ERROR, Constantes.ACTUALIZACION_FALLO);
			}
			request.setActivo(true);
			estructuraRep.save(request);
		}catch(Exception e){
			return new MensajeResponse(EstadoEnum.ERROR, Constantes.ACTUALIZACION_FALLO);
		}
		return new MensajeResponse(Constantes.ACTUALIZACION_EXITO);
	}

	private EstructuraPK construirPK(String cedula, String cargo, Date fecha) {
		EstructuraPK pk = new EstructuraPK();
		pk.setCedula(cedula);
		pk.setCargo(cargo);
		pk.setEmpresa(Constantes.ID_EMPRESA);
		pk.setFecha(fecha);
		return pk;
	}

	public MensajeResponse actualizarJunta(EstructuraRequest request) {
		try{
			Estructura estructura = estructuraRep.findOne(request.getId());
			if(estructura.getId().getCargo().equals(request.getNuevoCargo())){
				estructura.setActa(request.getActa());
				estructura.setActivo(request.getActivo());
				if(!estructura.getActivo()){
					estructura.setFechaBaja(new Date());
				}
				guardar(estructura);
			}else{
				Estructura estructuraNueva = new Estructura();
				estructuraNueva.setId(new EstructuraPK());
				estructuraNueva.getId().setCedula(request.getId().getCedula());
				estructuraNueva.getId().setEmpresa(request.getId().getEmpresa());
				estructuraNueva.getId().setFecha(request.getId().getFecha());
				estructuraNueva.getId().setCargo(request.getNuevoCargo());
				estructuraNueva.setActa(request.getActa());
				estructuraNueva.setActivo(request.getActivo());
				if(!request.getActivo()){
					estructuraNueva.setFechaBaja(new Date());
				}
				estructuraRep.delete(estructura);
				guardar(estructuraNueva);
			}
			
			
		}catch(Exception e){
			return new MensajeResponse(EstadoEnum.ERROR, Constantes.ACTUALIZACION_FALLO);
		}
		return new MensajeResponse(Constantes.ACTUALIZACION_EXITO);
	}
	
	private void guardar(Estructura estructura){
		estructuraRep.save(estructura);
	}

}
