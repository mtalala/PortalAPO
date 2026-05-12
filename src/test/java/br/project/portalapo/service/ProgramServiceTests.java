package br.project.portalapo.service;

import br.project.portalapo.model.Program;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("ProgramService Tests")
class ProgramServiceTests {

    private ProgramService programService = new ProgramService();

    @Test
    @DisplayName("Should get all programs")
    void testFindAll() {
        List<Program> result = programService.findAll();

        assertNotNull(result);
        assertTrue(result.size() > 0);
    }

    @Test
    @DisplayName("Should have Administração de Empresas in programs list")
    void testProgramsContainsAdministracao() {
        List<Program> programs = programService.findAll();

        boolean found = programs.stream()
                .anyMatch(p -> p.getName().equals("Administração de Empresas"));
        
        assertTrue(found, "Administração de Empresas should be in the list");
    }

    @Test
    @DisplayName("Should have correct program id and name")
    void testProgramContent() {
        List<Program> programs = programService.findAll();
        Program firstProgram = programs.get(0);

        assertEquals(1, firstProgram.getId());
        assertEquals("Administração de Empresas", firstProgram.getName());
    }
}
