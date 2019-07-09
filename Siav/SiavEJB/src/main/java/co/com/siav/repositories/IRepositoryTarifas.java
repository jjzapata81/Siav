package co.com.siav.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import co.com.siav.entities.Tarifa;

public interface IRepositoryTarifas extends JpaRepository<Tarifa, String>{

	@Modifying
	@Query("UPDATE Tarifa t SET t.codigo = :codigoNuevo WHERE t.codigo = :codigoAnterior")
	void update(@Param("codigoNuevo") String codigoNuevo, @Param("codigoAnterior") String codigoAnterior);

	@Query("SELECT t FROM Tarifa t ORDER BY t.orden")
	List<Tarifa> findAllByOrden();

	List<Tarifa> findByEsCredito(String esCredito);

	List<Tarifa> findByOrdenGreaterThan(Long orden);

	List<Tarifa> findByActivo(String activo);

}
