package co.com.siav.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import co.com.siav.entities.Pqr;

public interface IRepositoryPqr extends JpaRepository<Pqr, Long>{
	
	@Query("SELECT COALESCE(MAX(p.id), '0') FROM Pqr p")
	Long findMaxPqr();

}
