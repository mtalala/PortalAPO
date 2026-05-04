package br.project.portalapo.controller;

import br.project.portalapo.model.Coordenador;
import br.project.portalapo.service.CoordenadorService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/coordenadores")
@CrossOrigin
public class CoordenadorController {

    private final CoordenadorService service;

    public CoordenadorController(CoordenadorService service) {
        this.service = service;
    }

    @GetMapping
    public List<Coordenador> getAll() {
        return service.findAll();
    }

    @PostMapping
    public Coordenador create(@RequestBody Coordenador c) {
        return service.save(c);
    }
}