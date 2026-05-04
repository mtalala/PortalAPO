package br.project.portalapo.controller;

import br.project.portalapo.model.Program;
import br.project.portalapo.service.ProgramService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/programs")
@CrossOrigin
public class ProgramController {

    private final ProgramService service;

    public ProgramController(ProgramService service) {
        this.service = service;
    }

    @GetMapping
    public List<Program> getAll() {
        return service.findAll();
    }
}