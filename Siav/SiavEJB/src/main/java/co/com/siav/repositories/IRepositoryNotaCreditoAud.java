package co.com.siav.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import co.com.siav.entities.NotaCreditoAuditoria;

public interface IRepositoryNotaCreditoAud extends JpaRepository<NotaCreditoAuditoria, Long>{

	List<NotaCreditoAuditoria> findByInstalacionAndCiclo(Long numeroInstalacion, Long ciclo);

}
