package br.project.portalapo.repository;

import br.project.portalapo.model.Solicitacao;
import br.project.portalapo.enums.StatusSolicitacao;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SolicitacaoRepository extends JpaRepository<Solicitacao, Long> {

    List<Solicitacao> findByStatus(StatusSolicitacao status);

    List<Solicitacao> findByCategory(String category);
}