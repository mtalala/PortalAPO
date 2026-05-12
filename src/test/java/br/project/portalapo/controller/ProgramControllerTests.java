package br.project.portalapo.controller;

import br.project.portalapo.model.Program;
import br.project.portalapo.service.ProgramService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@DisplayName("ProgramController Tests")
class ProgramControllerTests {

    @Mock
    private ProgramService programService;

    @InjectMocks
    private ProgramController programController;

    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(programController).build();
    }

    @Test
    @DisplayName("Should get all programs")
    void testGetAllPrograms() throws Exception {
        Program program1 = new Program(1, "Administração de Empresas");
        Program program2 = new Program(2, "Administração do Desenvolvimento de Negócios");

        List<Program> programs = Arrays.asList(program1, program2);
        when(programService.findAll()).thenReturn(programs);

        mockMvc.perform(get("/api/programs"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2));

        verify(programService, times(1)).findAll();
    }

    @Test
    @DisplayName("Should return list with valid program data")
    void testGetAllProgramsContent() throws Exception {
        Program program = new Program(1, "Administração de Empresas");
        List<Program> programs = Arrays.asList(program);

        when(programService.findAll()).thenReturn(programs);

        mockMvc.perform(get("/api/programs"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].name").value("Administração de Empresas"));

        verify(programService, times(1)).findAll();
    }
}
