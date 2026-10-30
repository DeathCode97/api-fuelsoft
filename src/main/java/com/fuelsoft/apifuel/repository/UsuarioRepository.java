package com.fuelsoft.apifuel.repository;
import org.springframework.data.jpa.repository.JpaRepository;

import com.fuelsoft.apifuel.entities.Usuario;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Long>{
	Optional<Usuario> findByUsername(String username);
	
	boolean existsByUsername(String username);
}
