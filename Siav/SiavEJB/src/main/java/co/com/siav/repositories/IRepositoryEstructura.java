package co.com.siav.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import co.com.siav.entities.Estructura;
import co.com.siav.entities.EstructuraPK;

public interface IRepositoryEstructura extends JpaRepository<Estructura, EstructuraPK>{

	List<Estructura> findByIdEmpresaAndActivo(Long empresa, String activo);

}
