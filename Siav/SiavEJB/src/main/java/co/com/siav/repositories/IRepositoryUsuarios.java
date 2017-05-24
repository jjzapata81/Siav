package co.com.siav.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import co.com.siav.entities.Usuario;

public interface IRepositoryUsuarios extends JpaRepository<Usuario, String>{

}
