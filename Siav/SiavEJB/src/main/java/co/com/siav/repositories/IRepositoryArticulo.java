package co.com.siav.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import co.com.siav.entities.Articulo;

public interface IRepositoryArticulo extends JpaRepository<Articulo, Long>{

	List<Articulo> findByNombre(String nombre);

}
