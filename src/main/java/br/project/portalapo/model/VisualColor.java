package br.project.portalapo.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "visual_colors")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class VisualColor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String color; // e.g., #FF0000

    private String description;




}