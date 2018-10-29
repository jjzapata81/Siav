package co.com.siav.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import co.com.siav.entities.FacturaVencida;

public interface IRepositoryFacturaVencida extends JpaRepository<FacturaVencida, Long>{

	List<FacturaVencida> findByNumeroInstalacionOrderByCicloAsc(Long instalacion);

	List<FacturaVencida> findByNumeroInstalacion(Long instalacion);
	
	long countByNumeroInstalacion(Long numeroInstalacion);
	
	Long deleteByNumeroInstalacion(Long numeroInstalacion);
	
}
