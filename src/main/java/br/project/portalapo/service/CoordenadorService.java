package br.project.portalapo.service;

import br.project.portalapo.model.Coordenador;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CoordenadorService {

    private final List<Coordenador> coordenadores = new ArrayList<>();

    public List<Coordenador> findAll() {
        return coordenadores;
    }

    public Coordenador save(Coordenador c) {
        coordenadores.add(c);
        return c;
    }
}