package br.project.portalapo.controller;

import br.project.portalapo.model.VisualColor;
import br.project.portalapo.service.VisualColorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/visual-colors")
public class VisualColorController {

    @Autowired
    private VisualColorService visualColorService;

    @GetMapping
    public List<VisualColor> getAllVisualColors() {
        return visualColorService.getAllVisualColors();
    }

    @GetMapping("/{id}")
    public ResponseEntity<VisualColor> getVisualColorById(@PathVariable Long id) {
        return visualColorService.getVisualColorById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public VisualColor createVisualColor(@RequestBody VisualColor visualColor) {
        return visualColorService.createVisualColor(visualColor);
    }

    @PutMapping("/{id}")
    public ResponseEntity<VisualColor> updateVisualColor(@PathVariable Long id, @RequestBody VisualColor visualColorDetails) {
        try {
            VisualColor updatedVisualColor = visualColorService.updateVisualColor(id, visualColorDetails);
            return ResponseEntity.ok(updatedVisualColor);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteVisualColor(@PathVariable Long id) {
        visualColorService.deleteVisualColor(id);
        return ResponseEntity.noContent().build();
    }
}