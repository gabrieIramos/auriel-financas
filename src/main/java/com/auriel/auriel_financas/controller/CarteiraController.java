package com.auriel.auriel_financas.controller;

import com.auriel.auriel_financas.dto.CarteiraCreateDTO;
import com.auriel.auriel_financas.repository.CarteiraRepository;
import com.auriel.auriel_financas.service.CarteiraService;
import com.auriel.auriel_financas.model.Carteira;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/carteiras")
public class CarteiraController {

    private final CarteiraService carteiraService;

    public CarteiraController(CarteiraService carteiraService) {
        this.carteiraService = carteiraService;
    }

    @PostMapping
    public ResponseEntity<Carteira> criarCarteira(@RequestBody CarteiraCreateDTO carteiraCreateDTO) {
        Carteira novaCarteira = carteiraService.criarCarteira(carteiraCreateDTO);
        return ResponseEntity.ok(novaCarteira);
    }
}
