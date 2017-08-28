package co.com.siav.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import co.com.siav.entities.Kardex;

public interface IRepositoryKardex extends JpaRepository<Kardex, Long>{

}
