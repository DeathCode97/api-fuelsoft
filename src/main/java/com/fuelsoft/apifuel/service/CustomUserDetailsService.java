package com.fuelsoft.apifuel.service;
//import com.fuelsoft.apifuel.usuarios.UsuarioRepository;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.fuelsoft.apifuel.repository.UsuarioRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService{
	 
	private final UsuarioRepository repo;

	    public CustomUserDetailsService(UsuarioRepository repo) {
	        this.repo = repo;
	    }

	    @Override
	    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
	        var usuario = repo.findByUsername(username)
	                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado"));

	        return User.withUsername(usuario.getUsername())
	                .password(usuario.getPassword())   // hash BCrypt de la BD
	                .roles(usuario.getRol())           // "ADMIN" -> ROLE_ADMIN
	                .disabled(!usuario.isActivo())     // activo=false -> no puede entrar
	                .build();
	    }
}
