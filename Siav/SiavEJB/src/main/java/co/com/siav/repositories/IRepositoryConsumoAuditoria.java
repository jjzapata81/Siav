package co.com.siav.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import co.com.siav.entities.ConsumoAuditoria;

public interface IRepositoryConsumoAuditoria extends JpaRepository<ConsumoAuditoria, Long>{

	List<ConsumoAuditoria> findByCicloBetween(Long numeroDesde, Long numeroHasta);
	
}
