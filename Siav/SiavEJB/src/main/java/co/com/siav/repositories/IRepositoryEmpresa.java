package co.com.siav.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import co.com.siav.entities.Empresa;

public interface IRepositoryEmpresa extends JpaRepository<Empresa, Long>{

}
