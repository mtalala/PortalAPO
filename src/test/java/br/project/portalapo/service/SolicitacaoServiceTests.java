package br.project.portalapo.service;

import br.project.portalapo.model.Solicitacao;
import br.project.portalapo.enums.StatusSolicitacao;
import br.project.portalapo.repository.SolicitacaoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@DisplayName("SolicitacaoService Tests")
class SolicitacaoServiceTests {

    @Mock
    private SolicitacaoRepository solicitacaoRepository;

    @InjectMocks
    private SolicitacaoService solicitacaoService;

    private Solicitacao solicitacao;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        
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
    void testListarTodos() {
        Solicitacao solicitacao2 = new Solicitacao();
        solicitacao2.setId(2L);
        solicitacao2.setTitle("Outra Solicitação");

        List<Solicitacao> solicitacoes = Arrays.asList(solicitacao, solicitacao2);
        when(solicitacaoRepository.findAll()).thenReturn(solicitacoes);

        List<Solicitacao> result = solicitacaoService.listarTodos();

        assertEquals(2, result.size());
        verify(solicitacaoRepository, times(1)).findAll();
    }

    @Test
    @DisplayName("Should find solicitacao by id")
    void testBuscarPorId() {
        when(solicitacaoRepository.findById(1L)).thenReturn(Optional.of(solicitacao));

        Optional<Solicitacao> result = solicitacaoService.buscarPorId(1L);

        assertTrue(result.isPresent());
        assertEquals("Solicitação de Aprovação", result.get().getTitle());
        verify(solicitacaoRepository, times(1)).findById(1L);
    }

    @Test
    @DisplayName("Should create solicitacao with PENDENTE status")
    void testCriar() {
        Solicitacao newSolicitacao = new Solicitacao();
        newSolicitacao.setTitle("Nova Solicitação");
        newSolicitacao.setCategory("APO");
        newSolicitacao.setLevel("Graduação");
        newSolicitacao.setColor("green");

        when(solicitacaoRepository.save(any(Solicitacao.class))).thenReturn(solicitacao);

        Solicitacao result = solicitacaoService.criar(newSolicitacao);

        assertNotNull(result);
        assertEquals(StatusSolicitacao.PENDENTE, result.getStatus());
        verify(solicitacaoRepository, times(1)).save(any(Solicitacao.class));
    }

    @Test
    @DisplayName("Should update solicitacao")
    void testAtualizar() {
        Solicitacao updatedSolicitacao = new Solicitacao();
        updatedSolicitacao.setTitle("Solicitação Atualizada");
        updatedSolicitacao.setCategory("APO");
        updatedSolicitacao.setLevel("Pós-Graduação");
        updatedSolicitacao.setColor("red");

        when(solicitacaoRepository.findById(1L)).thenReturn(Optional.of(solicitacao));
        when(solicitacaoRepository.save(any(Solicitacao.class))).thenReturn(solicitacao);

        Solicitacao result = solicitacaoService.atualizar(1L, updatedSolicitacao);

        assertNotNull(result);
        verify(solicitacaoRepository, times(1)).findById(1L);
        verify(solicitacaoRepository, times(1)).save(any(Solicitacao.class));
    }

    @Test
    @DisplayName("Should throw exception when updating non-existent solicitacao")
    void testAtualizarNotFound() {
        when(solicitacaoRepository.findById(999L)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> {
            solicitacaoService.atualizar(999L, solicitacao);
        });
    }
}
