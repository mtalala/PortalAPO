package br.project.portalapo.service;

import br.project.portalapo.model.VisualColor;
import br.project.portalapo.repository.VisualColorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VisualColorService {

    @Autowired
    private VisualColorRepository visualColorRepository;

    public List<VisualColor> getAllVisualColors() {
        return visualColorRepository.findAll();
    }

    public Optional<VisualColor> getVisualColorById(Long id) {
        return visualColorRepository.findById(id);
    }

    public VisualColor createVisualColor(VisualColor visualColor) {
        return visualColorRepository.save(visualColor);
    }

    public VisualColor updateVisualColor(Long id, VisualColor visualColorDetails) {
        VisualColor visualColor = visualColorRepository.findById(id).orElseThrow(() -> new RuntimeException("VisualColor not found"));
        visualColor.setName(visualColorDetails.getName());
        visualColor.setColor(visualColorDetails.getColor());
        visualColor.setDescription(visualColorDetails.getDescription());
        return visualColorRepository.save(visualColor);
    }

    public void deleteVisualColor(Long id) {
        visualColorRepository.deleteById(id);
    }
}