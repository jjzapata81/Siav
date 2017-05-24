package co.com.siav.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import co.com.siav.entities.CuentaBanco;

public interface IRepositoryCuentasBancos extends JpaRepository<CuentaBanco, Long>{

	List<CuentaBanco> findByNombre(String nombre);

	List<CuentaBanco> findByNumeroCuenta(String numeroCuenta);


}
