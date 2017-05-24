package co.com.siav.bean;

import java.net.InetAddress;
import java.net.UnknownHostException;

import javax.ejb.Stateless;

import co.com.siav.exception.ExcepcionNegocio;
import co.com.siav.response.IpResponse;
import co.com.siav.utils.Constantes;

@Stateless
public class SistemaBean {
	
	public IpResponse consultarIp() {
		try {
			IpResponse response = new IpResponse();
			response.setIp(InetAddress.getLocalHost().getHostAddress());
			return response;
		} catch (UnknownHostException e) {
			throw new ExcepcionNegocio(Constantes.ERR_CONSULTAR_IP + e.getMessage());
		}
	}

}
