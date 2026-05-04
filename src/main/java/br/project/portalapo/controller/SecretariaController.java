package br.project.portalapo.controller;

import br.project.portalapo.model.Secretaria;
import br.project.portalapo.service.SecretariaService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/secretarias")
@CrossOrigin
public class SecretariaController {

    private final SecretariaService service;

    public SecretariaController(SecretariaService service) {
        this.service = service;
    }

    @GetMapping
    public List<Secretaria> getAll() {
        return service.findAll();
    }

    @PostMapping
    public Secretaria create(@RequestBody Secretaria s) {
        return service.save(s);
    }
}