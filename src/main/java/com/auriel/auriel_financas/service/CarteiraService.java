package com.auriel.auriel_financas.service;

import org.springframework.stereotype.Service;
import com.auriel.auriel_financas.model.Carteira;
import com.auriel.auriel_financas.repository.CarteiraRepository;
import com.auriel.auriel_financas.dto.CarteiraCreateDTO;
import java.util.List;
import com.auriel.auriel_financas.model.Ativo;
import com.auriel.auriel_financas.repository.AtivoRepository;
import com.auriel.auriel_financas.model.Usuario;
import com.auriel.auriel_financas.repository.UsuarioRepository;
import com.auriel.auriel_financas.exception.GlobalException;

@Service
public class CarteiraService {

    private final CarteiraRepository carteiraRepository;
    private final AtivoRepository ativoRepository;
    private final UsuarioRepository usuarioRepository;

    public CarteiraService(CarteiraRepository carteiraRepository, AtivoRepository ativoRepository, UsuarioRepository usuarioRepository) {
        this.carteiraRepository = carteiraRepository;
        this.ativoRepository = ativoRepository;
        this.usuarioRepository = usuarioRepository;
    }

    public Carteira criarCarteira(CarteiraCreateDTO carteiraCreateDTO) {
        Carteira novaCarteira = new Carteira();
        novaCarteira.setPrecoMedio(carteiraCreateDTO.getPreco_medio());
        novaCarteira.setQuantidade(carteiraCreateDTO.getQuantidade());

        Ativo ativo = ativoRepository.findById(carteiraCreateDTO.getAtivo_id())
            .orElseThrow(() -> GlobalException.ativoNaoEncontrado());
        Usuario usuario = usuarioRepository.findById(carteiraCreateDTO.getUsuario_id())
            .orElseThrow(() -> GlobalException.usuarioNaoEncontrado());
        novaCarteira.setAtivo(ativo);
        novaCarteira.setUsuario(usuario);

        return carteiraRepository.save(novaCarteira);
    }
    
}
