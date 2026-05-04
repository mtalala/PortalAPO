package br.project.portalapo.model;

import jakarta.persistence.Entity;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Aluno extends Pessoa {

    private String ra;
    private String curso;
    private Integer periodo;
}