package br.project.portalapo.service;

import br.project.portalapo.model.MembroComite;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MembroComiteService {

    private final List<MembroComite> membros = new ArrayList<>();

    public List<MembroComite> findAll() {
        return membros;
    }

    public MembroComite save(MembroComite m) {
        membros.add(m);
        return m;
    }
}