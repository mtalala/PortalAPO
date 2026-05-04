package br.project.portalapo.model;

import br.project.portalapo.enums.StatusSolicitacao;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "solicitacoes")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Solicitacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String category;
    private String title;
    private String level;
    private String color;

    @Enumerated(EnumType.STRING)
    private StatusSolicitacao status;

    private LocalDate createdAt;
    private LocalDate updatedAt;
    private LocalDate completedAt;
}