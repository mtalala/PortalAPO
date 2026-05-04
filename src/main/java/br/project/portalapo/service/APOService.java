package br.project.portalapo.service;

import br.project.portalapo.model.APO;
import br.project.portalapo.repository.APORepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class APOService {

    private final APORepository apoRepository;

    public APOService(APORepository apoRepository) {
        this.apoRepository = apoRepository;
    }

    // =========================
    // CRUD
    // =========================

    public List<APO> findAll() {
        return apoRepository.findAll();
    }

    public APO findById(Long id) {
        return apoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("APO não encontrada: " + id));
    }

    public APO create(APO apo) {
        apo.calcularTotalPoints();
        return apoRepository.save(apo);
    }

    public APO update(Long id, APO updated) {
        APO apo = findById(id);

        apo.setCodigoApo(updated.getCodigoApo());
        apo.setNome(updated.getNome());
        apo.setMatricula(updated.getMatricula());
        apo.setProgram(updated.getProgram());
        apo.setSemestre(updated.getSemestre());
        apo.setOrientador(updated.getOrientador());
        apo.setCoordenador(updated.getCoordenador());

        apo.setActivities(updated.getActivities());
        apo.setFiles(updated.getFiles());
        apo.setApprovals(updated.getApprovals());

        apo.calcularTotalPoints();

        return apoRepository.save(apo);
    }

    public void delete(Long id) {
        apoRepository.delete(findById(id));
    }

    // =========================
    // FLUXO
    // =========================

    public APO aprovarOrientador(Long id) {
        APO apo = findById(id);
        apo.aprovarOrientador();
        return apoRepository.save(apo);
    }

    public APO aprovarCoordenador(Long id) {
        APO apo = findById(id);
        apo.aprovarCoordenador();
        return apoRepository.save(apo);
    }

    public APO aprovarComissao(Long id) {
        APO apo = findById(id);
        apo.aprovarComissao();
        return apoRepository.save(apo);
    }

    public APO rejeitar(Long id) {
        APO apo = findById(id);
        apo.rejeitar();
        return apoRepository.save(apo);
    }
}