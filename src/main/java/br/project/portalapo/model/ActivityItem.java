package br.project.portalapo.model;

import jakarta.persistence.Embeddable;
import lombok.*;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ActivityItem {

    private String label;
    private Integer points;
}