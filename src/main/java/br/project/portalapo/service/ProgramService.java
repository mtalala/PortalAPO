package br.project.portalapo.service;

import br.project.portalapo.model.Program;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProgramService {

    private final List<Program> programs = List.of(
        new Program(1, "Administração de Empresas"),
        new Program(2, "Administração do Desenvolvimento de Negócios"),
        new Program(3, "Arquitetura e Urbanismo"),
        new Program(4, "Computação Aplicada"),
        new Program(5, "Ciências do Desenvolvimento Humano"),
        new Program(6, "Comunicação Intercultural nas Organizações"),
        new Program(7, "Controladoria, Finanças e Tecnologias de Gestão"),
        new Program(8, "Direito Político e Econômico"),
        new Program(9, "Economia e Mercados"),
        new Program(10, "Educação, Arte e História da Cultura"),
        new Program(11, "Engenharia de Produção"),
        new Program(12, "Engenharia Elétrica e Computação"),
        new Program(13, "Engenharia de Materiais e Nanotecnologia"),
        new Program(14, "Letras")
    );

    public List<Program> findAll() {
        return programs;
    }
}