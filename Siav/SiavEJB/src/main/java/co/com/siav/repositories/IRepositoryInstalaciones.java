package co.com.siav.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import co.com.siav.entities.Instalacion;

public interface IRepositoryInstalaciones extends JpaRepository<Instalacion, Long>{

	@Query("select coalesce(max(i.numeroInstalacion), '0') from Instalacion i where i.vereda.codigo = :codigoVereda")
	Long getMaxId(@Param("codigoVereda") Long codigoVereda);
	
	
	@Query("select i from Instalacion i where i.numeroInstalacion not in (select c.id.instalacion from Consumo c)")
	List<Instalacion> findNewInstalacion();

	Long countByVeredaCodigo(Long codigoVereda);

	Long countByActivo(String activo);

	List<Instalacion> findByActivoAndRamal(String activo, String ramal);

	@Modifying
	@Query("UPDATE Instalacion i SET i.cuentasVencidas = (SELECT f.cuentasVencidas from Factura f WHERE f.numeroInstalacion = i.numeroInstalacion AND f.ciclo = :ciclo)")
	void updateCuentasVencidas(@Param("ciclo") Long ciclo);
	
	@Modifying
	@Query("UPDATE Instalacion i SET i.cuentasVencidas = 0 WHERE i.numeroInstalacion = :numeroInstalacion)")
	void updateCuentasVencidasInstalacion(@Param("numeroInstalacion") Long numeroInstalacion);


	List<Instalacion> findByOrden(Long orden);

	@Query("SELECT i FROM Instalacion i ORDER BY i.orden")
	List<Instalacion> findFirstOrderByOrden();

	@Query("SELECT i FROM Instalacion i WHERE i.orden > :orden ORDER BY i.orden")
	List<Instalacion> findNextByOrden(@Param("orden") Long orden);
	
}
