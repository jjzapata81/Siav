package co.com.siav.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import co.com.siav.entities.Consumo;
import co.com.siav.entities.ConsumoPK;

public interface IRepositoryConsumos extends JpaRepository<Consumo, ConsumoPK>{

	List<Consumo> findByIdCiclo(Long ciclo);

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

}
