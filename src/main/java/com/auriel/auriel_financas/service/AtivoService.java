package com.auriel.auriel_financas.service;

import org.springframework.stereotype.Service;

import com.auriel.auriel_financas.dtos.AtivoDTO.AtivoResponseDTO;
import com.auriel.auriel_financas.dtos.AtivoDTO.AtivoUpdateDTO;
import com.auriel.auriel_financas.exception.GlobalException;
import com.auriel.auriel_financas.dtos.AtivoDTO.AtivoCreateDTO;
import com.auriel.auriel_financas.model.Ativo;
import com.auriel.auriel_financas.repository.AtivoRepository;
import java.util.List;

@Service
public class AtivoService {

    private final AtivoRepository ativoRepository;

    public AtivoService(AtivoRepository ativoRepository) {
        this.ativoRepository = ativoRepository;
    }

    public AtivoResponseDTO buscarPorTicker(String ticker) {
        Ativo ativo = ativoRepository.findByTicker(ticker)
                .orElseThrow(() -> GlobalException.ativoNaoEncontrado(ticker));

        return new AtivoResponseDTO(
                ativo.getIdAtivo(),
                ativo.getTicker(),
                ativo.getNome(),
                ativo.getTipo(),
                ativo.getPreco_atual(),
                ativo.getData_criacao());
    }

    public List<AtivoResponseDTO> listarTodos() {
        List<Ativo> ativos = ativoRepository.findAll();
        return ativos.stream()
                .map(ativo -> new AtivoResponseDTO(
                        ativo.getIdAtivo(),
                        ativo.getTicker(),
                        ativo.getNome(),
                        ativo.getTipo(),
                        ativo.getPreco_atual(),
                        ativo.getData_criacao()))
                .toList();
    }

    public AtivoResponseDTO atualizarPrecoAtual(AtivoUpdateDTO ativoUpdateDTO) {
        Ativo ativo = ativoRepository.findByTicker(ativoUpdateDTO.getTicker())
                .orElseThrow(() -> GlobalException.ativoNaoEncontrado(ativoUpdateDTO.getTicker()));

        ativo.setPreco_atual(ativoUpdateDTO.getPreco_atual());
        Ativo ativoAtualizado = ativoRepository.save(ativo);

        return new AtivoResponseDTO(
                ativoAtualizado.getIdAtivo(),
                ativoAtualizado.getTicker(),
                ativoAtualizado.getNome(),
                ativoAtualizado.getTipo(),
                ativoAtualizado.getPreco_atual(),
                ativoAtualizado.getData_criacao());
    }

}
