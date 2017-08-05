package co.com.siav.repositories;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import co.com.siav.entities.Pago;

public interface IRepositoryPago extends JpaRepository<Pago, Long>{

	List<Pago> findByFechaBetween(Date fechaDesde, Date fechaHasta);

}
