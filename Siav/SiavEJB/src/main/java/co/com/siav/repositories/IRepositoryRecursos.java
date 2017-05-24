package co.com.siav.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import co.com.siav.entities.Recurso;

public interface IRepositoryRecursos extends JpaRepository<Recurso, Long>{

	//List<Recurso> findByNumeroPerfilOrderByNumeroRecurso(Long perfil);

}
