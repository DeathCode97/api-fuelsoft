package com.fuelsoft.apifuel.service;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import com.fuelsoft.apifuel.entities.Usuario;
import com.fuelsoft.apifuel.repository.UsuarioRepository;
import com.fuelsoft.apifuel.requests.RegistroUsuarioRequest;
import com.fuelsoft.apifuel.responses.UsuarioResponse;

@Service
public class UsuarioService {
	private final UsuarioRepository repo;
    private final PasswordEncoder encoder;

    public UsuarioService(UsuarioRepository repo, PasswordEncoder encoder) {
        this.repo = repo;
        this.encoder = encoder;
    }

    @Transactional
    public UsuarioResponse crear(RegistroUsuarioRequest req) {
        if (repo.existsByUsername(req.username())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "El usuario ya existe");
        }

        Usuario u = new Usuario();
        u.setUsername(req.username());
        u.setPassword(encoder.encode(req.password()));          // se guarda el hash
        u.setRol(req.rol() == null ? "USER" : req.rol());
        u.setActivo(true);

        Usuario guardado = repo.save(u);
        return new UsuarioResponse(guardado.getId(), guardado.getUsername(),
                guardado.getRol(), guardado.isActivo());
    }
}
