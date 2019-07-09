package co.com.siav.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import co.com.siav.entities.Material;

public interface IRepositoryMaterial extends JpaRepository<Material, Long>{
	
	List<Material> findByInstalacionAndCiclo(Long instalacion, Long ciclo);

	Material findByInstalacionAndCicloAndCodigo(Long instalacion, Long ciclo, String codigo);

}
