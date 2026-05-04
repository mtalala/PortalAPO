package br.project.portalapo.model;

import jakarta.persistence.Entity;
import lombok.*;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Coordenador extends Pessoa {

    private LocalDate mandatoInicio;
    private LocalDate mandatoFim;
}