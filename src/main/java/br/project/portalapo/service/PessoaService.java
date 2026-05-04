package br.project.portalapo.service;

import br.project.portalapo.model.Pessoa;
import br.project.portalapo.repository.PessoaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PessoaService {

    private final PessoaRepository pessoaRepository;

    public PessoaService(PessoaRepository pessoaRepository) {
        this.pessoaRepository = pessoaRepository;
    }

    // =========================
    // CREATE
    // =========================
    public Pessoa salvar(Pessoa pessoa) {
        return pessoaRepository.save(pessoa);
    }

    // =========================
    // READ
    // =========================
    public List<Pessoa> listarTodos() {
        return pessoaRepository.findAll();
    }

    public Optional<Pessoa> buscarPorId(Long id) {
        return pessoaRepository.findById(id);
    }

    public Pessoa buscarOuFalhar(Long id) {
        return pessoaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pessoa não encontrada: " + id));
    }

    // =========================
    // UPDATE
    // =========================
    public Pessoa atualizar(Long id, Pessoa atualizada) {
        Pessoa existente = buscarOuFalhar(id);

        existente.setNome(atualizada.getNome());
        existente.setEmail(atualizada.getEmail());
        existente.setAtivo(atualizada.isAtivo());

        return pessoaRepository.save(existente);
    }

    // =========================
    // DELETE
    // =========================
    public void deletar(Long id) {
        Pessoa existente = buscarOuFalhar(id);
        pessoaRepository.delete(existente);
    }

    // =========================
    // BUSINESS
    // =========================
    public List<Pessoa> listarAtivos() {
        return pessoaRepository.findByAtivoTrue();
    }

    public List<Pessoa> listarInativos() {
        return pessoaRepository.findByAtivoFalse();
    }

    public Pessoa ativar(Long id) {
        Pessoa pessoa = buscarOuFalhar(id);
        pessoa.setAtivo(true);
        return pessoaRepository.save(pessoa);
    }

    public Pessoa desativar(Long id) {
        Pessoa pessoa = buscarOuFalhar(id);
        pessoa.setAtivo(false);
        return pessoaRepository.save(pessoa);
    }
}