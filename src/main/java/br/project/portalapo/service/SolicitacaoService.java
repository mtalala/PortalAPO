package br.project.portalapo.service;

import br.project.portalapo.model.Solicitacao;
import br.project.portalapo.enums.StatusSolicitacao;
import br.project.portalapo.repository.SolicitacaoRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class SolicitacaoService {

    private final SolicitacaoRepository repository;

    public SolicitacaoService(SolicitacaoRepository repository) {
        this.repository = repository;
    }

    public List<Solicitacao> listarTodos() {
        return repository.findAll();
    }

    public Optional<Solicitacao> buscarPorId(Long id) {
        return repository.findById(id);
    }

    public Solicitacao criar(Solicitacao solicitacao) {
        solicitacao.setStatus(StatusSolicitacao.PENDENTE);
        solicitacao.setCreatedAt(LocalDate.now());
        return repository.save(solicitacao);
    }

    public Solicitacao atualizar(Long id, Solicitacao novo) {
        Solicitacao sol = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Solicitação não encontrada"));

        sol.setCategory(novo.getCategory());
        sol.setTitle(novo.getTitle());
        sol.setLevel(novo.getLevel());
        sol.setColor(novo.getColor());

        sol.setUpdatedAt(LocalDate.now());

        return repository.save(sol);
    }

    public Solicitacao atualizarStatus(Long id, StatusSolicitacao status) {
        Solicitacao sol = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Solicitação não encontrada"));

        sol.setStatus(status);
        sol.setUpdatedAt(LocalDate.now());

        if (status == StatusSolicitacao.CONCLUIDA) {
            sol.setCompletedAt(LocalDate.now());
        }

        return repository.save(sol);
    }

    public void deletar(Long id) {
        repository.deleteById(id);
    }

    public List<Solicitacao> buscarPorStatus(StatusSolicitacao status) {
        return repository.findByStatus(status);
    }
}