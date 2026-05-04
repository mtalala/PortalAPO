package br.project.portalapo.controller;

import br.project.portalapo.model.APO;
import br.project.portalapo.service.APOService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/apos")
public class APOController {

    private final APOService apoService;

    public APOController(APOService apoService) {
        this.apoService = apoService;
    }

    // =========================
    // CRUD
    // =========================

    @GetMapping
    public ResponseEntity<List<APO>> getAll() {
        return ResponseEntity.ok(apoService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<APO> getById(@PathVariable Long id) {
        return ResponseEntity.ok(apoService.findById(id));
    }

    @PostMapping
    public ResponseEntity<APO> create(@RequestBody APO apo) {
        return ResponseEntity.ok(apoService.create(apo));
    }

    @PutMapping("/{id}")
    public ResponseEntity<APO> update(@PathVariable Long id,
                                       @RequestBody APO apo) {
        return ResponseEntity.ok(apoService.update(id, apo));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        apoService.delete(id);
        return ResponseEntity.noContent().build();
    }

    // =========================
    // FLUXO
    // =========================

    @PostMapping("/{id}/aprovar-orientador")
    public ResponseEntity<APO> aprovarOrientador(@PathVariable Long id) {
        return ResponseEntity.ok(apoService.aprovarOrientador(id));
    }

    @PostMapping("/{id}/aprovar-coordenador")
    public ResponseEntity<APO> aprovarCoordenador(@PathVariable Long id) {
        return ResponseEntity.ok(apoService.aprovarCoordenador(id));
    }

    @PostMapping("/{id}/aprovar-comissao")
    public ResponseEntity<APO> aprovarComissao(@PathVariable Long id) {
        return ResponseEntity.ok(apoService.aprovarComissao(id));
    }

    @PostMapping("/{id}/rejeitar")
    public ResponseEntity<APO> rejeitar(@PathVariable Long id) {
        return ResponseEntity.ok(apoService.rejeitar(id));
    }
}