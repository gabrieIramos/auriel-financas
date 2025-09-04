package com.auriel.auriel_financas.controller;

import com.auriel.auriel_financas.dto.UsuarioCreateDTO;
import com.auriel.auriel_financas.dto.UsuarioDTO;
import com.auriel.auriel_financas.service.UsuarioService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @PostMapping
    public ResponseEntity<UsuarioDTO> criarUsuario(@RequestBody UsuarioCreateDTO usuarioCreateDTO) {
        UsuarioDTO novoUsuario = usuarioService.criarUsuario(usuarioCreateDTO);
        return ResponseEntity.ok(novoUsuario);
    }
}
