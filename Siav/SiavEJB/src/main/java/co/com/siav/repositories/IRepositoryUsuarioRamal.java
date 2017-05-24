package co.com.siav.repositories;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import co.com.siav.entities.UsuarioRamal;
import co.com.siav.entities.UsuarioRamalPK;

public interface IRepositoryUsuarioRamal extends JpaRepository<UsuarioRamal, UsuarioRamalPK>{

	@Query("SELECT r FROM UsuarioRamal r WHERE r.usuarioRamalPK.usuario = :usuario AND r.usuarioRamalPK.fechaInicial <= :fechaConsulta AND (r.fechaFinal >= :fechaConsulta OR r.fechaFinal is null)")
	List<UsuarioRamal> findMacrosPorUsuario(@Param(value="usuario") String usuario, @Param(value="fechaConsulta")Date fechaConsulta);

}
