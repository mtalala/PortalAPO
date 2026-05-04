package br.project.portalapo.service;

import br.project.portalapo.model.Secretaria;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SecretariaService {

    private final List<Secretaria> secretarias = new ArrayList<>();

    public List<Secretaria> findAll() {
        return secretarias;
    }

    public Secretaria save(Secretaria s) {
        secretarias.add(s);
        return s;
    }
}