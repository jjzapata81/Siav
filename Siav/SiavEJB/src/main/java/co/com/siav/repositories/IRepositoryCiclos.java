package co.com.siav.repositories;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import co.com.siav.entities.Ciclo;

public interface IRepositoryCiclos extends JpaRepository<Ciclo, Long>{
	
	List<Ciclo> findByFecha(Date fecha);
		
	@Query("SELECT COALESCE(MAX(c.ciclo), '0') FROM Ciclo c WHERE c.estado = :estado")
	Long findMaximoCicloPorEstado(@Param(value="estado") String estado);

	Ciclo findFirstByEstadoOrderByCicloDesc(String estado);

}
