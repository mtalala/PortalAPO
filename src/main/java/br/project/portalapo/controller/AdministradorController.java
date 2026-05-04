package br.project.portalapo.controller;

import br.project.portalapo.model.Administrador;
import br.project.portalapo.service.AdministradorService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/administradores")
@CrossOrigin
public class AdministradorController {

    private final AdministradorService service;

    public AdministradorController(AdministradorService service) {
        this.service = service;
    }

    @GetMapping
    public List<Administrador> getAll() {
        return service.findAll();
    }

    @PostMapping
    public Administrador create(@RequestBody Administrador a) {
        return service.save(a);
    }
}