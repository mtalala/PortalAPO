package br.project.portalapo.controller;

import br.project.portalapo.model.Modalidade;
import br.project.portalapo.service.ModalidadeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/modalidades")
public class ModalidadeController {

    @Autowired
    private ModalidadeService modalidadeService;

    @GetMapping
    public List<Modalidade> getAllModalidades() {
        return modalidadeService.getAllModalidades();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Modalidade> getModalidadeById(@PathVariable Long id) {
        return modalidadeService.getModalidadeById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Modalidade createModalidade(@RequestBody Modalidade modalidade) {
        return modalidadeService.createModalidade(modalidade);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Modalidade> updateModalidade(@PathVariable Long id, @RequestBody Modalidade modalidadeDetails) {
        try {
            Modalidade updatedModalidade = modalidadeService.updateModalidade(id, modalidadeDetails);
            return ResponseEntity.ok(updatedModalidade);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteModalidade(@PathVariable Long id) {
        modalidadeService.deleteModalidade(id);
        return ResponseEntity.noContent().build();
    }
}