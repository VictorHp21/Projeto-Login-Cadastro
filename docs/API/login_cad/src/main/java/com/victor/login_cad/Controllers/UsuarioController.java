package com.victor.login_cad.Controllers;

import com.victor.login_cad.DTOs.CadastroRequest;
import com.victor.login_cad.DTOs.LoginRequest;
import com.victor.login_cad.DTOs.UsuarioDTO;
import com.victor.login_cad.Entities.Usuarios;
import com.victor.login_cad.Services.UsuarioService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UsuarioController {

    private final UsuarioService service;

    public UsuarioController(UsuarioService service){
        this.service = service;
    }

    @PostMapping("/cadastro")
    public ResponseEntity<Usuarios> cadastro (@RequestBody CadastroRequest request){
        return ResponseEntity.ok(service.cadastro(request));
    }

    @PostMapping("/login")
    public ResponseEntity<?> login (@RequestBody LoginRequest request){

        try {
            Usuarios user = service.login(request);

            UsuarioDTO dto = new UsuarioDTO(
                    user.getId(),
                    user.getNome(),
                    user.getEmail()
            );

            return ResponseEntity.ok(dto);
        } catch (RuntimeException e) {

            return ResponseEntity
                    .badRequest()
                    .body(e.getMessage());
        }

    }

}
