package com.auriel.auriel_financas.dtos.CarteiraDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CarteiraUpdateDTO {
    
    private Long id_carteira;

    private Long id_usuario;

    private String nome;

    private String descricao;
}
