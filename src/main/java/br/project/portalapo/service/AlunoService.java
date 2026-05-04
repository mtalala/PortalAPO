package br.project.portalapo.service;

import br.project.portalapo.model.Aluno;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AlunoService {

    private final List<Aluno> alunos = new ArrayList<>();

    public List<Aluno> findAll() {
        return alunos;
    }

    public Aluno save(Aluno aluno) {
        alunos.add(aluno);
        return aluno;
    }
}