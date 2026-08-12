package com.victor.login_cad.Repositories;

import com.victor.login_cad.Entities.Usuarios;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuarios, Long> {
    Optional<Usuarios> findByEmail(String email);
}
