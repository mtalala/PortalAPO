package br.project.portalapo.service;

import br.project.portalapo.model.Modalidade;
import br.project.portalapo.repository.ModalidadeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ModalidadeService {

    @Autowired
    private ModalidadeRepository modalidadeRepository;

    public List<Modalidade> getAllModalidades() {
        return modalidadeRepository.findAll();
    }

    public Optional<Modalidade> getModalidadeById(Long id) {
        return modalidadeRepository.findById(id);
    }

    public Modalidade createModalidade(Modalidade modalidade) {
        return modalidadeRepository.save(modalidade);
    }

    public Modalidade updateModalidade(Long id, Modalidade modalidadeDetails) {
        Modalidade modalidade = modalidadeRepository.findById(id).orElseThrow(() -> new RuntimeException("Modalidade not found"));
        modalidade.setName(modalidadeDetails.getName());
        modalidade.setDescription(modalidadeDetails.getDescription());
        return modalidadeRepository.save(modalidade);
    }

    public void deleteModalidade(Long id) {
        modalidadeRepository.deleteById(id);
    }
}