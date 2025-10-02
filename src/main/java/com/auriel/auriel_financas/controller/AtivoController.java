package com.auriel.auriel_financas.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import com.auriel.auriel_financas.dtos.AtivoDTO.AtivoResponseDTO;
import com.auriel.auriel_financas.service.AtivoService;

@RestController
@RequestMapping("/ativo")
public class AtivoController {

    private final AtivoService ativoService;

    public AtivoController(AtivoService ativoService) {
        this.ativoService = ativoService;
    }

    @GetMapping("/{ticker}")
    public ResponseEntity<AtivoResponseDTO> buscarPorTicker(@PathVariable String ticker) {
        AtivoResponseDTO ativo = ativoService.buscarPorTicker(ticker);
        return ResponseEntity.ok().body(ativo);
    }

    @GetMapping
    public ResponseEntity<List<AtivoResponseDTO>> listarTodosAtivos() {
        List<AtivoResponseDTO> ativos = ativoService.listarTodos();
        return ResponseEntity.ok().body(ativos);
    }
}
