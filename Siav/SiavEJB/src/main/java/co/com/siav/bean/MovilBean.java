package co.com.siav.bean;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.ejb.Stateless;
import javax.inject.Inject;

import co.com.siav.dto.ConsumoDTO;
import co.com.siav.dto.Macromedidor;
import co.com.siav.entities.CausaNoLectura;
import co.com.siav.entities.Consumo;
import co.com.siav.entities.ConsumoPK;
import co.com.siav.entities.Instalacion;
import co.com.siav.entities.Ramal;
import co.com.siav.entities.UsuarioRamal;
import co.com.siav.repositories.IRepositoryCausasNoLectura;
import co.com.siav.repositories.IRepositoryCiclos;
import co.com.siav.repositories.IRepositoryConsumos;
import co.com.siav.repositories.IRepositoryInstalaciones;
import co.com.siav.repositories.IRepositoryRamal;
import co.com.siav.repositories.IRepositoryUsuarioRamal;
import co.com.siav.request.ConsumosRequest;
import co.com.siav.response.MacrosResponse;
import co.com.siav.utils.Constantes;
import co.com.siav.utils.Utilidades;

@Stateless
public class MovilBean {
	
	@Inject
	private IRepositoryConsumos consumosRep;
	
	@Inject
	private IRepositoryCausasNoLectura causasRep;
	
	@Inject
	private IRepositoryCiclos ciclosRep;
	
	@Inject
	private IRepositoryRamal ramalesRep;
	
	@Inject
	private IRepositoryInstalaciones instalacionesRep;
	
	@Inject
	private IRepositoryUsuarioRamal usuarioRamalRep;
	
	private Long ciclo;
	
	private Long cicloAbierto;

	public List<ConsumoDTO> consultarConsumos() { 
		return crearLista(consumosRep.findByIdCiclo(ciclosRep.findFirstByEstadoOrderByCicloDesc(Constantes.CERRADO).getCiclo()));
	}

	private List<ConsumoDTO> crearLista(List<Consumo> consumosBD) {
		return consumosBD.stream().map(this::crearConsumo).collect(Collectors.toList());
	}
	
	private ConsumoDTO crearConsumo(Consumo consumoBD) {
		ConsumoDTO consumo = new ConsumoDTO();
		consumo.setNumeroInstalacion(consumoBD.getId().getInstalacion());
		consumo.setCiclo(consumoBD.getId().getCiclo());
		consumo.setRamal(consumoBD.getInstalacion().getRamal());
		consumo.setLecturaAnterior(consumoBD.getLecturaActual());
		consumo.setNombre(consumoBD.getInstalacion().getUsuario().getNombreCompleto());
		consumo.setDireccion(consumoBD.getInstalacion().getDireccion());
		consumo.setFechaDesde(Utilidades.dateToString(Utilidades.fechaMasDias(consumoBD.getFechaHasta(), 1)));
		consumo.setConsumoCorregido(consumoBD.getConsumoCorregido());
		consumo.setSerialMedidor(consumoBD.getInstalacion().getSerieMedidor());
		return consumo;
	}
	
	public void guardarConsumos(String usuario, ConsumosRequest request) {
		request.getConsumos().stream().forEach(consumo -> guardarConsumo(consumo, usuario));
	}
	
	private void guardarConsumo(ConsumoDTO consumo, String usuario) {
		ciclo = ciclosRep.findFirstByEstadoOrderByCicloDesc(Constantes.CERRADO).getCiclo();
		cicloAbierto = ciclosRep.findFirstByEstadoOrderByCicloDesc(Constantes.ABIERTO).getCiclo();
		List<CausaNoLectura> causasAplica = causasRep.findAll().stream().filter(causa -> causa.getAplicaPromedio()).collect(Collectors.toList());
		ConsumoPK consumoPk = crearId(consumo);
		if(!consumosRep.exists(consumoPk)){
			Consumo consumoBD = new Consumo();
			consumoBD.setId(consumoPk);
			consumoBD.setAjustado(false);
			consumoBD.setCodigoCausaNoLectura(consumo.getCodigoCausaNoLectura());
			consumoBD.setLecturaActual(consumo.getLecturaActual());
			consumoBD.setLecturaAnterior(consumo.getLecturaAnterior());
			consumoBD.setConsumoMes(Utilidades.extract(consumo.getLecturaActual(), consumo.getLecturaAnterior()));
			consumoBD.setConsumoPromedio(getConsumoPromedio(consumo.getNumeroInstalacion()));
			consumoBD.setConsumoDefinitivo(getConsumoDefinitivo(consumoBD, causasAplica));
			consumoBD.setConsumoCorregido(0L);
			consumoBD.setSincronizado(true);
			consumoBD.setInstalacion(buscarInstalacion(consumo.getNumeroInstalacion()));
			consumoBD.setFechaDesde(Utilidades.stringToDate(consumo.getFechaDesde()));
			consumoBD.setFechaHasta(Utilidades.stringToDate(consumo.getFechaHasta()));
			consumoBD.setUsuarioLectura(usuario);
			consumosRep.save(consumoBD);
		}
	}

	private Long getConsumoDefinitivo(Consumo consumo, List<CausaNoLectura> causasAplica) {
		if(null == consumo.getCodigoCausaNoLectura() || consumo.getCodigoCausaNoLectura() == 0L){
			return consumo.getConsumoMes();
		}
		return causasAplica.stream().map(CausaNoLectura::getCodigo).collect(Collectors.toList()).contains(consumo.getCodigoCausaNoLectura())
				? consumo.getConsumoPromedio()
						: consumo.getConsumoMes();
	}

	private Long getConsumoPromedio(Long instalacion) {
		List<Consumo> historico = consumosRep.findTop6ByInstalacionNumeroInstalacionOrderByIdCicloDesc(instalacion);
		return  Math.round(historico.stream().mapToLong(Consumo::getConsumoDefinitivo).average().orElse(0));
	}

	private Instalacion buscarInstalacion(Long numeroInstalcion) {
		return instalacionesRep.findOne(numeroInstalcion);
	}

	private ConsumoPK crearId(ConsumoDTO consumo) {
		ConsumoPK pk = new ConsumoPK();
		pk.setCiclo(cicloAbierto);
		pk.setInstalacion(consumo.getNumeroInstalacion());
		pk.setSerieMedidor(consumo.getSerialMedidor());
		return pk;
	}

	public MacrosResponse consultarMacrosPorUsuario(String usuario) {
		ciclo = ciclosRep.findFirstByEstadoOrderByCicloDesc(Constantes.CERRADO).getCiclo();
		cicloAbierto = ciclosRep.findFirstByEstadoOrderByCicloDesc(Constantes.ABIERTO).getCiclo();
		MacrosResponse response = new MacrosResponse();
		response.setMacros(consultarMacros(usuario));
		response.setConsumos(consultarConsumos(response.getMacros()));
		response.setCausasNoLectura(consultarCausasNoLectura()); 
		return response;
	}

	private List<CausaNoLectura> consultarCausasNoLectura() {
		return causasRep.findAllOrdered();
	}

	private List<ConsumoDTO> consultarConsumos(List<Macromedidor> macros) {
		List<Consumo> consumosBD = new ArrayList<Consumo>();
		macros.stream().forEach(macro-> consumosBD.addAll(consultar(macro)));
		return crearLista(consumosBD);
	}

	private List<Consumo> consultar(Macromedidor macro) {
		return consumosRep.findConsumo(Constantes.SI, macro.getCodigoRamal(), ciclo, cicloAbierto);
	}

	private List<Macromedidor> consultarMacros(String usuario) {
		List<UsuarioRamal> ramalesUsuario = usuarioRamalRep.findMacrosPorUsuario(usuario, new Date());
		return ramalesUsuario.stream().map(this::crearMacro).collect(Collectors.toList());
	}

	private Macromedidor crearMacro(UsuarioRamal ramalUsuario) {
		Ramal ramal = ramalesRep.findOne(ramalUsuario.getUsuarioRamalPK().getCodigoRamal());
		Macromedidor macro = new Macromedidor();
		macro.setCodigoRamal(ramal.getCodigoRamal());
		macro.setNombre(ramal.getNombre());
		return macro;
	}
	
}
