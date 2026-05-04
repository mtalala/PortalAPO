package br.project.portalapo.controller;

import br.project.portalapo.model.Solicitacao;
import br.project.portalapo.enums.StatusSolicitacao;
import br.project.portalapo.service.SolicitacaoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/solicitacoes")
@CrossOrigin(origins = "*")
public class SolicitacaoController {

    private final SolicitacaoService service;

    public SolicitacaoController(SolicitacaoService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<Solicitacao>> listar() {
        return ResponseEntity.ok(service.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Solicitacao> buscarPorId(@PathVariable Long id) {
        return service.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Solicitacao> criar(@RequestBody Solicitacao solicitacao) {
        return ResponseEntity.ok(service.criar(solicitacao));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Solicitacao> atualizar(
            @PathVariable Long id,
            @RequestBody Solicitacao solicitacao
    ) {
        return ResponseEntity.ok(service.atualizar(id, solicitacao));
    }

    @PatchMapping("/{id}/status")
    public ResponseEntity<Solicitacao> atualizarStatus(
            @PathVariable Long id,
            @RequestParam StatusSolicitacao status
    ) {
        return ResponseEntity.ok(service.atualizarStatus(id, status));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/status/{status}")
    public ResponseEntity<List<Solicitacao>> porStatus(@PathVariable StatusSolicitacao status) {
        return ResponseEntity.ok(service.buscarPorStatus(status));
    }
}