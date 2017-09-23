package co.com.siav.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import co.com.siav.entities.Articulo;

public interface IRepositoryArticulo extends JpaRepository<Articulo, Long>{

	List<Articulo> findByNombre(String nombre);
	
	@Query("SELECT COALESCE(MAX(a.codigo), '0') FROM Articulo a")
	Long findMaxArticulo();

	List<Articulo> findByActivo(String activo);

	Articulo findByCodigoContable(String codigoContable);

}
