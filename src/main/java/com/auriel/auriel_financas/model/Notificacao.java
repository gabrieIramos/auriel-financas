package com.auriel.auriel_financas.model;



import java.time.LocalDateTime;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "notificacao")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Notificacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_notificacao") 
    private Long idNotificacao;

    @ManyToOne
    @JoinColumn(name = "id_usuario", referencedColumnName = "id_usuario")
    private Usuario usuario; // Referência ao usuário que possui a notificação

    @ManyToOne
    @JoinColumn(name = "id_ativo", referencedColumnName = "id_ativo")
    private Ativo ativo; // Referência ao ativo que possui a notificação

    @Column(nullable = false, name = "data_envio", updatable = false)
    @Temporal(TemporalType.TIMESTAMP)    
    private LocalDateTime dataEnvio;

}
