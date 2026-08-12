package com.victor.login_cad.Services;

import com.victor.login_cad.DTOs.FuncionarioDTO;
import com.victor.login_cad.Entities.Funcionario;
import com.victor.login_cad.Entities.Usuarios;
import com.victor.login_cad.Repositories.FuncionarioRepository;
import com.victor.login_cad.Repositories.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FuncionarioService {

    private final FuncionarioRepository funcionarioRepository;
    private final UsuarioRepository usuarioRepository;

    public FuncionarioService(FuncionarioRepository funcionarioRepository, UsuarioRepository usuarioRepository){
        this.funcionarioRepository = funcionarioRepository;
        this.usuarioRepository = usuarioRepository;
    }

    //mapper

    public FuncionarioDTO toDTO(Funcionario f){
        FuncionarioDTO dto = new FuncionarioDTO();

        dto.setId(f.getId());
        dto.setNome(f.getNome());
        dto.setIdade(f.getIdade());

        dto.setDepartamento(f.getDepartamento() != null ? f.getDepartamento() : null);

        return dto;
    }


    public List<FuncionarioDTO> listarFuncionariosPorUsuario(Long userId) {
        return funcionarioRepository.findByUsuarioId(userId)
                .stream()
                .map(this::toDTO)
                .toList();
    }

    public Optional<Funcionario> buscarFuncionarioPorId(Long id){
        return funcionarioRepository.findById(id);
    }

    private Funcionario buscarFuncionario(Long id) {
        return funcionarioRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Funcionário não encontrado"));
    }

    public Funcionario cadastrarFuncionario(FuncionarioDTO dto){

        Funcionario funcionario = new Funcionario();

        funcionario.setNome(dto.getNome());
        funcionario.setIdade(dto.getIdade());
        funcionario.setDepartamento(dto.getDepartamento());

        Usuarios usuario = usuarioRepository.findById(dto.getUsuarioId())
                        .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        funcionario.setUsuario(usuario);

        return  funcionarioRepository.save(funcionario);
    }

    public Funcionario editarFuncionario(Long id, FuncionarioDTO dto){
        
    }

}
