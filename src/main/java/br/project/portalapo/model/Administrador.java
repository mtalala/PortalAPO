package br.project.portalapo.model;

import jakarta.persistence.Entity;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Administrador extends Pessoa {

    private String nivelAcesso;
}