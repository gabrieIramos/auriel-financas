package com.auriel.auriel_financas.service;

import com.auriel.auriel_financas.model.Usuario;
import com.auriel.auriel_financas.repository.UsuarioRepository;
import com.auriel.auriel_financas.util.SenhaUtil;
import com.auriel.auriel_financas.dtos.UsuarioDTO.UsuarioCreateDTO;
import com.auriel.auriel_financas.dtos.UsuarioDTO.UsuarioResponseDTO;
import com.auriel.auriel_financas.dtos.UsuarioDTO.UsuarioUpdateDTO;
import com.auriel.auriel_financas.exception.GlobalException;
import com.auriel.auriel_financas.jwt.JwtService; 
import com.auriel.auriel_financas.model.UserDetailsImpl; 
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import org.springframework.security.authentication.AuthenticationManager; 
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication; 
import org.springframework.security.crypto.password.PasswordEncoder;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager; 
    private final JwtService jwtService; 

    public UsuarioService(UsuarioRepository usuarioRepository, PasswordEncoder passwordEncoder, AuthenticationManager authenticationManager, JwtService jwtService) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
    }

    public UsuarioResponseDTO registrar(UsuarioCreateDTO usuarioCreateDTO) {
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
        usuario.setTelefone(usuarioCreateDTO.getTelefone());

        Usuario usuarioSalvo = usuarioRepository.save(usuario);

        return new UsuarioResponseDTO(
                usuarioSalvo.getIdUsuario(),
                usuarioSalvo.getNome(),
                usuarioSalvo.getEmail(),
                usuarioSalvo.getTelefone());
    }

    public String autenticar(String email, String senha) {
        
        Authentication authentication;
        try {
            // Tenta autenticar
            authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(email, senha)
            );
        } catch (Exception e) {            
            throw GlobalException.loginIncorreto(); 
        }

        if (authentication.isAuthenticated()) {
            
            // Atualiza o último login
            Usuario usuario = usuarioRepository.findByEmail(email);
            LocalDateTime horaAtual = LocalDateTime.now();
            usuario.setUltimoLogin(horaAtual);
            usuarioRepository.save(usuario);

            // Gera e retorna o token JWT
            UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
            return jwtService.generateToken(userDetails);

        } else {
            throw GlobalException.loginIncorreto();
        }
    }

    public UsuarioResponseDTO atualizarPerfil(Long idUsuario, UsuarioUpdateDTO usuarioUpdateDTO) {
        Usuario usuario = usuarioRepository.findById(idUsuario)
                .orElseThrow(() -> GlobalException.usuarioNaoEncontrado());

        if (usuarioUpdateDTO.getNome() != null && !usuarioUpdateDTO.getNome().isEmpty()
                && usuarioUpdateDTO.getNome() != usuario.getNome()) {
            usuario.setNome(usuarioUpdateDTO.getNome());
        }

        if (usuarioUpdateDTO.getEmail() != null && !usuarioUpdateDTO.getEmail().isEmpty()
                && usuarioUpdateDTO.getEmail() != usuario.getEmail()) {
            if (usuarioRepository.existsByEmail(usuarioUpdateDTO.getEmail())) {
                throw GlobalException.emailJaCadastrado();
            }
            usuario.setEmail(usuarioUpdateDTO.getEmail());
        }

        if (usuarioUpdateDTO.getTelefone() != null && !usuarioUpdateDTO.getTelefone().isEmpty()
                && usuarioUpdateDTO.getTelefone() != usuario.getTelefone()) {
            usuario.setTelefone(usuarioUpdateDTO.getTelefone());
        }

        Usuario usuarioAtualizado = usuarioRepository.save(usuario);

        return new UsuarioResponseDTO(
                usuarioAtualizado.getIdUsuario(),
                usuarioAtualizado.getNome(),
                usuarioAtualizado.getEmail(),
                usuarioAtualizado.getTelefone());
    }

    public String trocarSenha(Long idUsuario, String senhaAtual, String novaSenha) {
        Usuario usuario = usuarioRepository.findById(idUsuario)
                .orElseThrow(() -> GlobalException.usuarioNaoEncontrado());

        if (!passwordEncoder.matches(senhaAtual, usuario.getSenha())) {
            throw GlobalException.senhaAtualIncorreta();
        }

        usuario.setSenha(passwordEncoder.encode(novaSenha));
        usuarioRepository.save(usuario);
        return "Senha alterada com sucesso";
    }

}
