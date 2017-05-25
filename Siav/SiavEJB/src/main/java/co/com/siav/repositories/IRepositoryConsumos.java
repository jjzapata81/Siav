package co.com.siav.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import co.com.siav.entities.Consumo;
import co.com.siav.entities.ConsumoPK;

public interface IRepositoryConsumos extends JpaRepository<Consumo, ConsumoPK>{

	List<Consumo> findByIdCiclo(Long ciclo);
	
	Consumo findByIdInstalacionAndIdCiclo(Long instalacion, Long ciclo);

	List<Consumo> findByIdCicloAndInstalacionActivoAndInstalacionFacturacion(Long ciclo, String activo, String facturacion);
	
	@Query("select c from Consumo c where c.id.ciclo = :ciclo and c.sincronizado = 'S' and c.instalacion.activo = :activo and c.instalacion.facturacion = :facturacion order by c.id.instalacion")
	List<Consumo> findConsumosPrefactura(@Param(value="ciclo") Long ciclo, @Param(value="activo") String activo, @Param(value="facturacion") String facturacion);
	
	@Query("SELECT c FROM Consumo c WHERE c.instalacion.activo = :activo AND c.instalacion.ramal = :ramal AND c.id.ciclo = :ciclo "
			+ "AND c.instalacion.numeroInstalacion NOT IN (SELECT ac.instalacion.numeroInstalacion FROM Consumo ac WHERE ac.id.ciclo = :cicloAbierto) "
			+ "ORDER BY c.instalacion.orden")
	List<Consumo> findConsumo(@Param(value="activo") String activo, @Param(value="ramal") String ramal, @Param(value="ciclo") Long ciclo, @Param(value="cicloAbierto") Long cicloAbierto);

	List<Consumo>findTop6ByInstalacionNumeroInstalacionOrderByIdCicloDesc(Long numeroInstalacion);

	@Query("SELECT c FROM Consumo c WHERE c.id.ciclo = :ciclo AND (c.consumoDefinitivo IS NULL OR c.consumoDefinitivo < 0)")
	List<Consumo> findByCicloAndIncompletos(@Param(value="ciclo") Long ciclo);

	@Query("SELECT c FROM Consumo c WHERE c.id.ciclo = :ciclo AND (c.consumoDefinitivo = 0 OR c.consumoDefinitivo > 0) AND("
			+ "c.consumoDefinitivo - (SELECT ca.consumoDefinitivo from Consumo ca where ca.id.ciclo = c.id.ciclo-1 and ca.id.instalacion = c.id.instalacion) < :consumoDesde "
			+ "OR c.consumoDefinitivo - (SELECT ca.consumoDefinitivo from Consumo ca where ca.id.ciclo = c.id.ciclo-1 and ca.id.instalacion = c.id.instalacion) > :consumoHasta)")
	List<Consumo> findByRango(@Param(value="ciclo") Long ciclo, @Param(value="consumoDesde")Long desde, @Param(value="consumoHasta")Long hasta);

	@Modifying
	@Query("UPDATE Consumo c SET c.id.serieMedidor = :nuevoMedidor WHERE c.id.ciclo = :ciclo AND c.id.instalacion = :instalacion")
	void updateMedidor(@Param(value="ciclo") Long ciclo, @Param(value="instalacion") Long instalacion, @Param("nuevoMedidor")String nuevoMedidor);

}
