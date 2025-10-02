package com.auriel.auriel_financas.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "ativo")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Ativo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_ativo") 
    private Long idAtivo;

    @Column(nullable = false, unique = true)
    private String ticker; // PETR4, HGLG11

    @Column(nullable = false)
    private String nome; // PETROBRAS, BANCO DO BRASIL, VALE S.A.

    @Column()
    private String tipo; // AÇÃO, FUNDO IMOBILIARIO etc

    @Column(nullable = false)
    private BigDecimal preco_atual; // Ultima cotação conhecida do ativo

    @Column(updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @CreationTimestamp
    private LocalDateTime data_criacao; // Data de criação do ativo
}
