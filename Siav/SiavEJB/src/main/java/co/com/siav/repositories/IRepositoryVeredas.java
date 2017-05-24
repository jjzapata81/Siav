package co.com.siav.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import co.com.siav.entities.Vereda;

public interface IRepositoryVeredas extends JpaRepository<Vereda, Long>{

	Vereda findByNombre(String nombre);

}
