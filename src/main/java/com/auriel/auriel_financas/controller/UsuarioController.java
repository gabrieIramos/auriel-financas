package com.auriel.auriel_financas.controller;

import com.auriel.auriel_financas.dtos.UsuarioDTO.UsuarioCreateDTO;
import com.auriel.auriel_financas.dtos.UsuarioDTO.UsuarioResponseDTO;
import com.auriel.auriel_financas.dtos.UsuarioDTO.UsuarioUpdateDTO;
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
    public ResponseEntity<UsuarioResponseDTO> criarUsuario(@RequestBody UsuarioCreateDTO usuarioCreateDTO) {
        UsuarioResponseDTO novoUsuario = usuarioService.registrar(usuarioCreateDTO);
        return ResponseEntity.status(201).body(novoUsuario);
    }

    @PostMapping("/autenticar")
    public ResponseEntity<String> autenticarUsuario(@RequestParam String email, @RequestParam String senha) {
        String token = usuarioService.autenticar(email, senha);
        return ResponseEntity.ok().body(token);
    }

    @PatchMapping("/{idUsuario}")
    public ResponseEntity<UsuarioResponseDTO> atualizarUsuario(@PathVariable Long idUsuario, @RequestBody UsuarioUpdateDTO usuarioResponseDTO)
    {
        UsuarioResponseDTO usuarioAtualizado = usuarioService.atualizarPerfil(idUsuario, usuarioResponseDTO);
        return ResponseEntity.ok().body(usuarioAtualizado);
    }



    

   
}
