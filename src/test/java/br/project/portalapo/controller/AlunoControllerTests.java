package br.project.portalapo.controller;

import br.project.portalapo.model.Aluno;
import br.project.portalapo.service.AlunoService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@DisplayName("AlunoController Tests")
class AlunoControllerTests {

    @Mock
    private AlunoService alunoService;

    @InjectMocks
    private AlunoController alunoController;

    private MockMvc mockMvc;
    private ObjectMapper objectMapper;

    private Aluno aluno;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(alunoController).build();
        objectMapper = new ObjectMapper();

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
    void testGetAllAlunos() throws Exception {

        Aluno aluno2 = new Aluno();
        aluno2.setId(2L);
        aluno2.setNome("Maria Santos");
        aluno2.setRa("2023002");

        List<Aluno> alunos = Arrays.asList(aluno, aluno2);

        when(alunoService.findAll()).thenReturn(alunos);

        mockMvc.perform(get("/alunos"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2));

        verify(alunoService, times(1)).findAll();
    }

    @Test
    @DisplayName("Should create aluno")
    void testCreateAluno() throws Exception {

        when(alunoService.save(any(Aluno.class))).thenReturn(aluno);

        mockMvc.perform(post("/alunos")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(aluno)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.nome").value("João da Silva"));

        verify(alunoService, times(1)).save(any(Aluno.class));
    }
}