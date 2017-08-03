package co.com.siav.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import co.com.siav.entities.Maestros;

public interface IRepositoryMaestros extends JpaRepository<Maestros, Long>{

	List<Maestros> findByGrupo(String nombreMaestro);

	Maestros findByCodigo(String codigo);

	Maestros findByCodigoAndGrupo(String codigo, String grupo);

}
