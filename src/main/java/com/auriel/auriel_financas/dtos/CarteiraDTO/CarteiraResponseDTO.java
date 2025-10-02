package com.auriel.auriel_financas.dtos.CarteiraDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CarteiraResponseDTO {
    
    private Long id_carteira;

    private Long id_usuario;

    private String nome;

    private String descricao;

    private LocalDateTime data_criacao;    
}
