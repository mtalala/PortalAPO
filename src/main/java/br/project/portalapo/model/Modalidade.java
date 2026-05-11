package br.project.portalapo.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "modalidades")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Modalidade {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    private String description;



}