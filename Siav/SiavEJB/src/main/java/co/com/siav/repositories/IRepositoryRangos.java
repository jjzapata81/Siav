package co.com.siav.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import co.com.siav.entities.Rango;

public interface IRepositoryRangos extends JpaRepository<Rango, Long>{

	Rango findByEstado(String estado);

	@Query("SELECT r FROM Rango r WHERE r.limiteInicial = (SELECT MIN(limiteInicial) FROM Rango WHERE limiteInicial > :limiteFinal AND estado = 'DISPONIBLE')")
	Rango findSiguienteRango(@Param("limiteFinal") Long limiteFinal);

	@Query("SELECT r FROM Rango r WHERE r.limiteInicial = (SELECT MIN(limiteInicial) FROM Rango WHERE estado = 'DISPONIBLE')")
	Rango findNextDisponible();


}
