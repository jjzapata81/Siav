package co.com.siav.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import co.com.siav.entities.Exceso;

public interface IRepositoryExcesos extends JpaRepository<Exceso, Long>{

	Exceso findByCodigo(String codigo);

	@Query("SELECT e FROM Exceso e ORDER BY e.orden")
	List<Exceso> findAllByOrden();

}
