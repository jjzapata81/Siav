package co.com.siav.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import co.com.siav.entities.EntradaMaestro;

public interface IRepositoryEntradas extends JpaRepository<EntradaMaestro, Long>{

	@Query("SELECT COALESCE(MAX(e.codigo), '0') FROM EntradaMaestro e")
	Long findMaxEntrada();

}
