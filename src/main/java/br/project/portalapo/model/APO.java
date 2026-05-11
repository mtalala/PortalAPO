package br.project.portalapo.model;

import br.project.portalapo.enums.StatusAPO;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class APO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String codigoApo;

    private String nome;
    private String matricula;

    private String program;
    private String semestre;

    private String orientador;
    private String coordenador;

    @Enumerated(EnumType.STRING)
    private StatusAPO status;

    private LocalDate dataSubmissao;
    private LocalDate completedAt;
    @Builder.Default
    private Integer totalPoints = 0;
    @Builder.Default
    private Integer requiredCommissionApprovals = 3;

    // =========================
    // LISTAS DO JSON
    // =========================

    @ElementCollection
    @CollectionTable(name = "apo_activities", joinColumns = @JoinColumn(name = "apo_id"))
    @Builder.Default
    private List<ActivityItem> activities = new ArrayList<>();

    @ElementCollection
    @CollectionTable(name = "apo_files", joinColumns = @JoinColumn(name = "apo_id"))
    @Builder.Default
    private List<FileItem> files = new ArrayList<>();

    @ElementCollection
    @CollectionTable(name = "apo_approvals", joinColumns = @JoinColumn(name = "apo_id"))
    @Builder.Default
    private List<ApprovalItem> approvals = new ArrayList<>();

















    // =========================
    // REGRAS
    // =========================

    public void calcularTotalPoints() {
        int total = 0;

        if (activities != null) {
            for (ActivityItem a : activities) {
                if (a != null && a.getPoints() != null) {
                    total += a.getPoints();
                }
            }
        }

        this.totalPoints = total;
    }

    public void aprovarOrientador() {
        this.status = StatusAPO.PENDENTE_COORDENACAO;
    }

    public void aprovarCoordenador() {
        this.status = StatusAPO.PENDENTE_COMISSAO;
    }

    public void aprovarComissao() {
        this.status = StatusAPO.APROVADA;
        this.completedAt = LocalDate.now();
    }

    public void rejeitar() {
        this.status = StatusAPO.REJEITADA;
        this.completedAt = LocalDate.now();
    }
}