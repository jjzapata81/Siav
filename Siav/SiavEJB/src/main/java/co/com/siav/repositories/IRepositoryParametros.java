package co.com.siav.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import co.com.siav.entities.Parametro;

public interface IRepositoryParametros extends JpaRepository<Parametro, Long>{

	Parametro findByCdParametro(String parametro);

}
