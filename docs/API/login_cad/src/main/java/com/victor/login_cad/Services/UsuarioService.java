package com.victor.login_cad.Services;

import com.victor.login_cad.DTOs.CadastroRequest;
import com.victor.login_cad.DTOs.LoginRequest;
import com.victor.login_cad.Entities.Usuarios;
import com.victor.login_cad.Repositories.UsuarioRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;

    public UsuarioService(UsuarioRepository usuarioRepository, PasswordEncoder passwordEncoder){
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
    }

    // cadastro

    public Usuarios cadastro (CadastroRequest request){

        if (usuarioRepository.findByEmail(request.getEmail()).isPresent()){
            throw new RuntimeException("Email já cadastrado");
        }

        Usuarios user = new Usuarios();

        user.setNome(request.getNome());
        user.setEmail(request.getEmail());
        user.setSenha(
                passwordEncoder.encode(request.getSenha())
        );

        return usuarioRepository.save(user);

    }

    // login

    public Usuarios login (LoginRequest request){
        Usuarios user = usuarioRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        if(!passwordEncoder.matches(request.getSenha(), user.getSenha())){
            throw new RuntimeException("Senha inválida");
        }

        return user;
    }


}
