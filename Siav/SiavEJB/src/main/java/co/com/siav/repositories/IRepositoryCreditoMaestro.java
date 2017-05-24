package co.com.siav.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import co.com.siav.entities.CreditoMaestro;

public interface IRepositoryCreditoMaestro extends JpaRepository<CreditoMaestro, Long>{

	List<CreditoMaestro> findByInstalacion(Long instalacion);

	@Query("select c from CreditoMaestro c where c.instalacion = :instalacion AND c.saldo > 0")
	List<CreditoMaestro> findByInstalacionSinCancelar(@Param(value="instalacion")Long instalacion);
	
	@Query("select c from CreditoMaestro c where c.saldo > 0")
	List<CreditoMaestro> findAllSinCancelar();

}
