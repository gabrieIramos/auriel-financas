package com.auriel.auriel_financas.dtos.AtivoDTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AtivoCreateDTO {   

    @NotBlank(message =  "O ticker não pode ser vazio")
    private String ticker;

    @NotBlank(message =  "O nome do ativo não pode ser vazio")
    private String nome; // PETROBRAS, BANCO DO BRASIL, VALE S.A.

    private String tipo; // AÇÃO, FUNDO IMOBILIARIO etc

    @NotNull(message =  "O preço atual do ativo é obrigatório")
    private String preco_atual; // Ultima cotação conhecida do ativo    
}