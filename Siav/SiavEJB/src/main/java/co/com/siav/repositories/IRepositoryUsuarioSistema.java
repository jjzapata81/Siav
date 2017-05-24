package co.com.siav.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import co.com.siav.entities.UsuarioSistema;

public interface IRepositoryUsuarioSistema extends JpaRepository<UsuarioSistema, String>{

	UsuarioSistema findByNombreUsuario(String nombreUsuario);

}
