package com.auriel.auriel_financas.dto;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CarteiraCreateDTO {

    @NotNull(message =  "O preço médio não pode ser nulo")
    @NotBlank(message =  "O preço médio não pode ser vazio")
    @Min(value = 1, message = "O preço médio deve ser maior que zero")
    private Number preco_medio;
    
    @NotNull(message =  "A quantidade não pode ser nula")
    @NotBlank(message =  "A quantidade não pode ser vazia")
    @Min(value = 1, message = "A quantidade deve ser maior que zero")
    private Number quantidade;
    
    private Long ativo_id; 

    private Long usuario_id;

    
}
