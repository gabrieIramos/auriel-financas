package com.auriel.auriel_financas.dtos.AtivoDTO;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AtivoUpdateDTO {
    
    private Long id_ativo;

    private String ticker;

    private String nome; // PETROBRAS, BANCO DO BRASIL, VALE S.A.

    private String tipo; // AÇÃO, FUNDO IMOBILIARIO etc

    private BigDecimal preco_atual; // Ultima cotação conhecida do ativo    
}