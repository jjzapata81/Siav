package co.com.siav.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import co.com.siav.entities.Banco;

public interface IRepositoryBancos extends JpaRepository<Banco, Long>{

	List<Banco> findByNombre(String banco);

}
