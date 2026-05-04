package br.project.portalapo.model;

import jakarta.persistence.Entity;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Orientador extends Pessoa {

    private String areaAtuacao;
    private String titulacao;
}