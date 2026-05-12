package br.project.portalapo.service;

import br.project.portalapo.model.Aluno;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("AlunoService Tests")
class AlunoServiceTests {

    private AlunoService alunoService;

    private Aluno aluno;

    @BeforeEach
    void setUp() {
        alunoService = new AlunoService();

        aluno = new Aluno();
        aluno.setId(1L);
        aluno.setNome("João da Silva");
        aluno.setEmail("joao@university.edu");
        aluno.setRa("2023001");
        aluno.setCurso("Engenharia");
        aluno.setPeriodo(4);
    }

    @Test
    @DisplayName("Should get all alunos")
    void testGetAllAlunos() {
        alunoService.save(aluno);

        List<Aluno> result = alunoService.findAll();

        assertEquals(1, result.size());
        assertEquals("João da Silva", result.get(0).getNome());
    }

    @Test
    @DisplayName("Should save aluno")
    void testSaveAluno() {
        Aluno result = alunoService.save(aluno);

        assertNotNull(result);
        assertEquals(1, alunoService.findAll().size());
        assertEquals("João da Silva", result.getNome());
    }

    @Test
    @DisplayName("Should maintain list reference")
    void testInternalListBehavior() {
        alunoService.save(aluno);

        List<Aluno> alunos = alunoService.findAll();

        assertTrue(alunos.contains(aluno));
    }
}