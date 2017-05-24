package co.com.siav.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import co.com.siav.entities.Exceso;

public interface IRepositoryExcesos extends JpaRepository<Exceso, Long>{

	Exceso findByCodigo(String codigo);

}
