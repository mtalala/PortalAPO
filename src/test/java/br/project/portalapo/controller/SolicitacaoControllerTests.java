package br.project.portalapo.controller;

import br.project.portalapo.model.Solicitacao;
import br.project.portalapo.enums.StatusSolicitacao;
import br.project.portalapo.service.SolicitacaoService;
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

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@DisplayName("SolicitacaoController Tests")
class SolicitacaoControllerTests {

    @Mock
    private SolicitacaoService solicitacaoService;

    @InjectMocks
    private SolicitacaoController solicitacaoController;

    private MockMvc mockMvc;
    private Solicitacao solicitacao;
    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(solicitacaoController).build();
        objectMapper = new ObjectMapper();

        solicitacao = new Solicitacao();
        solicitacao.setId(1L);
        solicitacao.setTitle("Solicitação de Aprovação");
        solicitacao.setCategory("APO");
        solicitacao.setLevel("Graduação");
        solicitacao.setColor("blue");
        solicitacao.setStatus(StatusSolicitacao.PENDENTE);
        solicitacao.setCreatedAt(LocalDate.now());
    }

    @Test
    @DisplayName("Should list all solicitacoes")
    void testListarTodos() throws Exception {
        Solicitacao solicitacao2 = new Solicitacao();
        solicitacao2.setId(2L);
        solicitacao2.setTitle("Outra Solicitação");

        List<Solicitacao> solicitacoes = Arrays.asList(solicitacao, solicitacao2);
        when(solicitacaoService.listarTodos()).thenReturn(solicitacoes);

        mockMvc.perform(get("/api/solicitacoes"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2));

        verify(solicitacaoService, times(1)).listarTodos();
    }

    @Test
    @DisplayName("Should find solicitacao by id")
    void testBuscarPorId() throws Exception {
        when(solicitacaoService.buscarPorId(1L)).thenReturn(Optional.of(solicitacao));

        mockMvc.perform(get("/api/solicitacoes/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.title").value("Solicitação de Aprovação"))
                .andExpect(jsonPath("$.status").value("PENDENTE"));

        verify(solicitacaoService, times(1)).buscarPorId(1L);
    }

    @Test
    @DisplayName("Should return 404 when solicitacao not found")
    void testBuscarPorIdNotFound() throws Exception {
        when(solicitacaoService.buscarPorId(999L)).thenReturn(Optional.empty());

        mockMvc.perform(get("/api/solicitacoes/999"))
                .andExpect(status().isNotFound());

        verify(solicitacaoService, times(1)).buscarPorId(999L);
    }

    @Test
    @DisplayName("Should create solicitacao")
    void testCriar() throws Exception {
        when(solicitacaoService.criar(any(Solicitacao.class))).thenReturn(solicitacao);

        mockMvc.perform(post("/api/solicitacoes")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(solicitacao)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.title").value("Solicitação de Aprovação"));

        verify(solicitacaoService, times(1)).criar(any(Solicitacao.class));
    }

    @Test
    @DisplayName("Should update solicitacao")
    void testAtualizar() throws Exception {
        Solicitacao updatedSolicitacao = new Solicitacao();
        updatedSolicitacao.setTitle("Solicitação Atualizada");
        updatedSolicitacao.setCategory("APO");
        updatedSolicitacao.setLevel("Pós-Graduação");
        updatedSolicitacao.setColor("red");

        when(solicitacaoService.atualizar(eq(1L), any(Solicitacao.class))).thenReturn(solicitacao);

        mockMvc.perform(put("/api/solicitacoes/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(updatedSolicitacao)))
                .andExpect(status().isOk());

        verify(solicitacaoService, times(1)).atualizar(eq(1L), any(Solicitacao.class));
    }
}
