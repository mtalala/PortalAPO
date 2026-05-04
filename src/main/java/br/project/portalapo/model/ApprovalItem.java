package br.project.portalapo.model;

import br.project.portalapo.enums.RoleAprovacao;
import jakarta.persistence.Embeddable;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.*;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ApprovalItem {

    private String userId;

    @Enumerated(EnumType.STRING)
    private RoleAprovacao role;

    private boolean approved;
}