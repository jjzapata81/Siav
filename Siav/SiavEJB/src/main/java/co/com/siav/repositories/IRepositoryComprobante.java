package co.com.siav.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import co.com.siav.entities.Comprobante;

public interface IRepositoryComprobante extends JpaRepository<Comprobante, Long>{
	
}
