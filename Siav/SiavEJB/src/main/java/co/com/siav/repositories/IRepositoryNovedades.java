package co.com.siav.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import co.com.siav.entities.Novedad;
import co.com.siav.entities.NovedadPK;

public interface IRepositoryNovedades extends JpaRepository<Novedad, NovedadPK>{

	List<Novedad> findByIdInstalacionAndIdCiclo(Long instalacion, Long ciclo);


}
