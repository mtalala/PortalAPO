package br.project.portalapo.controller;

import br.project.portalapo.model.Aluno;
import br.project.portalapo.service.AlunoService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/alunos")
@CrossOrigin
public class AlunoController {

    private final AlunoService service;

    public AlunoController(AlunoService service) {
        this.service = service;
    }

    @GetMapping
    public List<Aluno> getAll() {
        return service.findAll();
    }

    @PostMapping
    public Aluno create(@RequestBody Aluno aluno) {
        return service.save(aluno);
    }
}