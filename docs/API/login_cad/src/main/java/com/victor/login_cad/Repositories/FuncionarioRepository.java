package com.victor.login_cad.Repositories;

import com.victor.login_cad.Entities.Funcionario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FuncionarioRepository  extends JpaRepository<Funcionario, Long> {

    List<Funcionario> findByUsuarioId(Long userId);
}
