package com.auriel.auriel_financas.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "ativos")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Ativo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column()
    private String nome;  //PETROBRAS SA, BANCO DO BRASIL, VALE SA ETC    

    @Column()
    private String tipo; // AÇÃO, FUNDO IMOBILIARIO etc
    
    @Column(unique = true)
    private String codigo; //PETR4, HGLG11
}
