package co.com.siav.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import co.com.siav.entities.CreditoDetalle;
import co.com.siav.entities.CreditoDetallePK;

public interface IRepositoryCreditoDetalle extends JpaRepository<CreditoDetalle, CreditoDetallePK>{

	void deleteByIdCiclo(Long ciclo);


}
