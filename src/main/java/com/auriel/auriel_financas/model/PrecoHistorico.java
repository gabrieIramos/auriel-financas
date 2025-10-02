package com.auriel.auriel_financas.model;


import java.math.BigDecimal;
import java.time.LocalDateTime;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "preco_historico")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PrecoHistorico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_preco_historico") 
    private Long idPrecoHistorico;


    @ManyToOne
    @JoinColumn(name = "id_ativo", referencedColumnName = "id_ativo")
    private Ativo ativo; // Referência ao ativo que possui o preço histórico

    @Column(nullable = false)
    private BigDecimal preco; // Nome do preço histórico    

    @Column(nullable = false, name = "data_referencia", updatable = false)
    @Temporal(TemporalType.TIMESTAMP)    
    private LocalDateTime dataReferencia;

}
