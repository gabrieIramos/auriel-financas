package com.auriel.auriel_financas.dtos.UsuarioDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioResponseDTO {

    private Long id_usuario;

    private String nome;

    private String email;

    private String telefone;
    
}
