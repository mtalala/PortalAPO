package br.project.portalapo.model;

import jakarta.persistence.Entity;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Secretaria extends Pessoa {

    private String setor;

}