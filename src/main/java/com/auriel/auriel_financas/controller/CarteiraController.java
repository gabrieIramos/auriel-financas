package com.auriel.auriel_financas.controller;

import com.auriel.auriel_financas.dtos.CarteiraDTO.CarteiraCreateDTO;
import com.auriel.auriel_financas.dtos.CarteiraDTO.CarteiraResponseDTO;
import com.auriel.auriel_financas.dtos.CarteiraDTO.CarteiraUpdateDTO;
import com.auriel.auriel_financas.service.CarteiraService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/carteiras")
public class CarteiraController {

    private final CarteiraService carteiraService;

    public CarteiraController(CarteiraService carteiraService) {
        this.carteiraService = carteiraService;
    }

    @PostMapping
    public ResponseEntity<CarteiraResponseDTO> criarCarteira(@RequestBody CarteiraCreateDTO carteiraCreateDTO) {
        CarteiraResponseDTO novaCarteira = carteiraService.criarCarteira(carteiraCreateDTO);
        return ResponseEntity.status(201).body(novaCarteira);
    }

    @GetMapping("/usuario/{idUsuario}")
    public ResponseEntity<java.util.List<CarteiraResponseDTO>> listarCarteirasPorUsuario(@PathVariable Long idUsuario) {
        java.util.List<CarteiraResponseDTO> carteiras = carteiraService.listarCarteirasPorUsuario(idUsuario);
        return ResponseEntity.ok().body(carteiras);
    }

    @PatchMapping("/{idCarteira}")
    public ResponseEntity<CarteiraResponseDTO> atualizarCarteira(@PathVariable Long idCarteira, @RequestBody CarteiraUpdateDTO carteiraUpdateDTO) {
        CarteiraResponseDTO carteiraAtualizada = carteiraService.atualizarCarteira(idCarteira, carteiraUpdateDTO);
        return ResponseEntity.ok().body(carteiraAtualizada);
    }

}
