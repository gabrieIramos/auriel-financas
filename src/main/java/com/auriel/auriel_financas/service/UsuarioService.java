package com.auriel.auriel_financas.service;

import com.auriel.auriel_financas.dto.UsuarioCreateDTO;
import com.auriel.auriel_financas.dto.UsuarioDTO;
import com.auriel.auriel_financas.model.Usuario;
import com.auriel.auriel_financas.repository.UsuarioRepository;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.password.PasswordEncoder;
import java.util.List;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;

    public UsuarioService(UsuarioRepository usuarioRepository, PasswordEncoder passwordEncoder) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public UsuarioDTO criarUsuario(UsuarioCreateDTO usuarioCreateDTO) {
        
        if (usuarioRepository.existsByEmail(usuarioCreateDTO.getEmail())) {
            throw new Error("Email já cadastrado");
        }


        Usuario usuario = new Usuario();
        usuario.setNome(usuarioCreateDTO.getNome());
        usuario.setEmail(usuarioCreateDTO.getEmail());
        usuario.setSenha(passwordEncoder.encode(usuarioCreateDTO.getSenha()));

        Usuario novoUsuario = usuarioRepository.save(usuario);

        // Retorna DTO sem senha
        return new UsuarioDTO(novoUsuario.getId(), novoUsuario.getNome(), novoUsuario.getEmail());
    }

    public List<UsuarioDTO> listarUsuarios() {
        List<Usuario> usuarios = usuarioRepository.findAll();
        return usuarios.stream()
                .map(usuario -> new UsuarioDTO(usuario.getId(), usuario.getNome(), usuario.getEmail()))
                .toList();
    }
}
