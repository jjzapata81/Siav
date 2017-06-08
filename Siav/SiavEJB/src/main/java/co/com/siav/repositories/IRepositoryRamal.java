package co.com.siav.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import co.com.siav.entities.Ramal;

public interface IRepositoryRamal extends JpaRepository<Ramal, String>{

	List<Ramal> findByNombre(String nombre);

}
