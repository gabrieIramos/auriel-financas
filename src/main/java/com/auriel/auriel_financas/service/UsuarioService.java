package com.auriel.auriel_financas.service;

import com.auriel.auriel_financas.dto.UsuarioCreateDTO;
import com.auriel.auriel_financas.dto.UsuarioDTO;
import com.auriel.auriel_financas.model.Usuario;
import com.auriel.auriel_financas.repository.UsuarioRepository;
import com.auriel.auriel_financas.util.SenhaUtil;
import com.auriel.auriel_financas.exception.GlobalException;
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
            throw GlobalException.emailJaCadastrado();
        }

        if (!SenhaUtil.validarCaracteresSenha(usuarioCreateDTO.getSenha())) {
            throw GlobalException.senhaInvalida();            
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

    public UsuarioDTO atualizarUsuario(Long id, UsuarioCreateDTO usuarioCreateDTO) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> GlobalException.usuarioNaoEncontrado());

        //A PRINCIPIO A SENHA NÃO DEVERIA SER ATUALIZADA AQUI, MAS VAMOS DEIXAR ASSIM POR ENQUANTO
        if (usuarioCreateDTO.getSenha() != null) {
            if (!SenhaUtil.validarCaracteresSenha(usuarioCreateDTO.getSenha())) {
                throw GlobalException.senhaInvalida();
            }
            usuario.setSenha(passwordEncoder.encode(usuarioCreateDTO.getSenha()));
        }

        if (usuarioCreateDTO.getNome() != null) {
            usuario.setNome(usuarioCreateDTO.getNome());
        }
        if (usuarioCreateDTO.getEmail() != null) {
            if (!usuario.getEmail().equals(usuarioCreateDTO.getEmail()) &&
                usuarioRepository.existsByEmail(usuarioCreateDTO.getEmail())) {
                throw GlobalException.emailJaCadastrado();
            }
            usuario.setEmail(usuarioCreateDTO.getEmail());
        }

        Usuario usuarioAtualizado = usuarioRepository.save(usuario);

        return new UsuarioDTO(usuarioAtualizado.getId(), usuarioAtualizado.getNome(), usuarioAtualizado.getEmail());
    }

    public void deletarUsuario(Long id) {
        if (!usuarioRepository.existsById(id)) {
            throw GlobalException.usuarioNaoEncontrado();
        }
        usuarioRepository.deleteById(id);
    }
}
