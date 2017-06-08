package co.com.siav.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import co.com.siav.entities.CausaNoLectura;

public interface IRepositoryCausasNoLectura extends JpaRepository<CausaNoLectura, Long>{

	CausaNoLectura findByDescripcion(String descripcion);

	@Query("select c from CausaNoLectura c  WHERE c.activo = 'S' order by c.codigo")
	List<CausaNoLectura> findAllOrdered();

	List<CausaNoLectura> findByActivo(String estado);

}
