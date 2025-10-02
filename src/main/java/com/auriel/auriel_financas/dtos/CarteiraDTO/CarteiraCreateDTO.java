package com.auriel.auriel_financas.dtos.CarteiraDTO;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CarteiraCreateDTO {

    @NotBlank(message = "O nome da carteira é obrigatório")
    @NotNull(message = "O nome da carteira não pode ser nulo")
    private Long id_usuario;

    @NotBlank(message = "O nome da carteira é obrigatório")
    @NotNull(message = "O nome da carteira não pode ser nulo")
    private String nome;

    private String descricao;    
}
