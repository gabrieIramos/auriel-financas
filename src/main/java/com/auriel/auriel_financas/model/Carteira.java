package com.auriel.auriel_financas.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "carteira")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Carteira {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "usuario_id", referencedColumnName = "id")
    private Usuario usuario; // Referência ao usuário que possui a carteira

    @ManyToOne
    @JoinColumn(name = "ativo_id", referencedColumnName = "id")
    private Ativo ativo; // Referência ao ativo que está na carteira

    @Column()
    private Number quantidade; // Quantidade do ativo na carteira

    @Column()
    private Number precoMedio; // Preço médio de compra do ativo na carteira

    public Number getTotalInvestido() {
        if (quantidade != null && precoMedio != null) {
            return quantidade.doubleValue() * precoMedio.doubleValue();
        }
        return 0;
    }
}
