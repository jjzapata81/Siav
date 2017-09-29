package co.com.siav.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import co.com.siav.entities.Usuario;

public interface IRepositoryUsuarios extends JpaRepository<Usuario, String>{

	@Query("select count(distinct u) from Usuario u, Instalacion i where i.vereda.codigo = :codigoVereda AND u.cedula = i.usuario.cedula")
	Long countByVeredaCodigoAndUsuario(@Param("codigoVereda") Long codigoVereda);

	@Query("select count(distinct u) from Usuario u, Instalacion i where i.ramal = :codigoRamal AND u.cedula = i.usuario.cedula")
	Long countByRamalAndUsuario(@Param("codigoRamal") String codigoRamal);

	List<Usuario> findByNombresLikeAndApellidosLike(String nombres, String apellidos);

	List<Usuario> findByEnvioMail(String envioMail);

}
