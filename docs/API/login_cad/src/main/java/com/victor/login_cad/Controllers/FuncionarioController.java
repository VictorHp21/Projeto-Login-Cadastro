package com.victor.login_cad.Controllers;

import com.victor.login_cad.DTOs.FuncionarioDTO;
import com.victor.login_cad.Entities.Funcionario;
import com.victor.login_cad.Services.FuncionarioService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/funcionarios")
public class FuncionarioController {

    private final FuncionarioService service;

    public FuncionarioController(FuncionarioService service){
        this.service = service;
    }

    @PostMapping
    public Funcionario cadastrarFuncionario(@RequestBody FuncionarioDTO dto){
        return service.cadastrarFuncionario(dto);
    }

    @GetMapping("/{id}")
    public List<FuncionarioDTO> listarFuncionariosPorUsuario(@PathVariable Long id){
        return service.listarFuncionariosPorUsuario(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Funcionario> editarFuncionario(
            @PathVariable Long id,
            @RequestBody FuncionarioDTO dto
    ){
        return ResponseEntity.ok(
                service.editarFuncionario(id, dto)
        );
    }

    @DeleteMapping("/{id}")
    public String excluirFuncionario(@PathVariable Long id){
        boolean removido = service.excluirFuncionario(id);

        return removido
                ? "Funcionário removido com sucesso"
                : "Funcionário não encontrado";
    }

}
