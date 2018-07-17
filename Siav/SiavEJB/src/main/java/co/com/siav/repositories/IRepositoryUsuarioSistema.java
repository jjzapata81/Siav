package co.com.siav.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import co.com.siav.entities.UsuarioSistema;
import co.com.siav.response.UsuarioSistemaResponse;

public interface IRepositoryUsuarioSistema extends JpaRepository<UsuarioSistema, String>{

	UsuarioSistema findByNombreUsuario(String nombreUsuario);

	List<UsuarioSistema> findByPerfilCodigoPerfilAndActivo(Long perfil, String estado);

}
