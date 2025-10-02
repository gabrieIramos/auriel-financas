package com.auriel.auriel_financas.model;


import java.math.BigDecimal;
import java.time.LocalDateTime;
import org.hibernate.annotations.CreationTimestamp;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "Movimentacao")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Movimentacao {

    

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_mov") 
    private Long idMovimentacao;

    @ManyToOne
    @JoinColumn(name = "id_carteira", referencedColumnName = "id_carteira")
    private Carteira carteira; // Referência à carteira que possui a movimentação

    @ManyToOne
    @JoinColumn(name = "id_ativo", referencedColumnName = "id_ativo")
    private Ativo ativo; // Referência ao ativo que possui a movimentação

    @Column(nullable = false)
    private EnumMovimentacao tipo; // COMPRA, VENDA, DIVIDENDO etc

    @Column(nullable = false)
    private BigDecimal quantidade; 

    @Column(nullable = false, name = "preco_unitario")
    private BigDecimal precoUnitario; 

    @Column(nullable = false, name = "data_movimentacao", updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @CreationTimestamp
    private LocalDateTime dataMovimentacao; 

    
}
