package com.auriel.auriel_financas.model;


import java.time.LocalDateTime;
import org.hibernate.annotations.CreationTimestamp;
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
    @Column(name = "id_carteira") 
    private Long idCarteira;

    @ManyToOne
    @JoinColumn(name = "id_usuario", referencedColumnName = "id_usuario")
    private Usuario usuario; // Referência ao usuário que possui a carteira    

    @Column(nullable = false)
    private String nome; // Nome da carteira

    @Column()
    private String descricao; // Descrição da carteira

    @Column(nullable = false, name = "data_criacao", updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @CreationTimestamp
    private LocalDateTime dataCriacao; 

    
}
