package br.project.portalapo.service;

import br.project.portalapo.model.Administrador;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AdministradorService {

    private final List<Administrador> administradores = new ArrayList<>();

    public List<Administrador> findAll() {
        return administradores;
    }

    public Administrador save(Administrador a) {
        administradores.add(a);
        return a;
    }
}