package br.project.portalapo.controller;

import br.project.portalapo.model.Pessoa;
import br.project.portalapo.service.PessoaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pessoas")
public class PessoaController {

    private final PessoaService pessoaService;

    public PessoaController(PessoaService pessoaService) {
        this.pessoaService = pessoaService;
    }

    @PostMapping
    public ResponseEntity<Pessoa> criar(@RequestBody Pessoa pessoa) {
        return ResponseEntity.ok(pessoaService.salvar(pessoa));
    }

    @GetMapping
    public ResponseEntity<List<Pessoa>> listar() {
        return ResponseEntity.ok(pessoaService.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pessoa> buscar(@PathVariable Long id) {
        return ResponseEntity.ok(pessoaService.buscarOuFalhar(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Pessoa> atualizar(@PathVariable Long id,
                                            @RequestBody Pessoa pessoa) {
        return ResponseEntity.ok(pessoaService.atualizar(id, pessoa));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        pessoaService.deletar(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/ativos")
    public ResponseEntity<List<Pessoa>> listarAtivos() {
        return ResponseEntity.ok(pessoaService.listarAtivos());
    }

    @GetMapping("/inativos")
    public ResponseEntity<List<Pessoa>> listarInativos() {
        return ResponseEntity.ok(pessoaService.listarInativos());
    }

    @PatchMapping("/{id}/ativar")
    public ResponseEntity<Pessoa> ativar(@PathVariable Long id) {
        return ResponseEntity.ok(pessoaService.ativar(id));
    }

    @PatchMapping("/{id}/desativar")
    public ResponseEntity<Pessoa> desativar(@PathVariable Long id) {
        return ResponseEntity.ok(pessoaService.desativar(id));
    }
}