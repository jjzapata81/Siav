package co.com.siav.repositories;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import co.com.siav.entities.Factura;

public interface IRepositoryFacturas extends JpaRepository<Factura, Long>{

	@Query("SELECT f FROM Factura f WHERE f.numeroInstalacion = :numeroInstalacion AND f.ciclo = :ciclo AND (f.cancelado = 'N' OR (f.cancelado = 'S' AND f.abono = 'S'))")
	Factura findByFacturaCuentasVencidas(@Param("numeroInstalacion") Long instalacion, @Param("ciclo") Long ciclo);

	Long deleteByCiclo(Long ciclo);
	
	@Query("SELECT COALESCE(MAX(f.numeroFactura), '0') FROM Factura f WHERE f.ciclo = :ciclo")
	Long findNumeroFacturaByCiclo(@Param("ciclo") Long ciclo);

	Factura findByNumeroFacturaAndNumeroInstalacion(Long numeroFactura, Long numeroInstalacion);
	
	@Modifying
	@Query("UPDATE Factura f SET f.cuentasVencidas = (f.cuentasVencidas + 1) WHERE f.ciclo = :ciclo AND f.cancelado = :cancelado")
	void updateFacturaVencida(@Param("ciclo") Long ciclo, @Param("cancelado") String cancelado);

	Factura findByNumeroInstalacionAndCiclo(Long numeroInstalacion, Long ciclo);

	List<Factura> findByCiclo(Long numeroCiclo);
	
	@Query("SELECT MAX(f.fechaPagoReal) from Factura f where f.numeroInstalacion = :numeroInstalacion")
	Date findByUltimaFechaPago(@Param("numeroInstalacion") Long instalacion);

}
