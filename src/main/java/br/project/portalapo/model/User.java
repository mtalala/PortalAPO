package br.project.portalapo.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String role; // e.g., ADMIN, COORDENADOR, ALUNO

    @Column(nullable = false)
    private boolean ativo = true;

    // Relacionamento com Pessoa se necessário
    // @OneToOne
    // @JoinColumn(name = "pessoa_id")
    // private Pessoa pessoa;





    public boolean isAtivo() { return ativo; }

}