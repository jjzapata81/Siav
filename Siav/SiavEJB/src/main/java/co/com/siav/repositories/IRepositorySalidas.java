package co.com.siav.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import co.com.siav.entities.SalidaMaestro;

public interface IRepositorySalidas extends JpaRepository<SalidaMaestro, Long>{

	@Query("SELECT COALESCE(MAX(s.codigo), '0') FROM SalidaMaestro s")
	Long findMaxSalida();

}
