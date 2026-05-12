package br.project.portalapo.service;

import br.project.portalapo.model.Modalidade;
import br.project.portalapo.repository.ModalidadeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@DisplayName("ModalidadeService Tests")
class ModalidadeServiceTests {

    @Mock
    private ModalidadeRepository modalidadeRepository;

    @InjectMocks
    private ModalidadeService modalidadeService;

    private Modalidade modalidade;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        modalidade = new Modalidade();
        modalidade.setId(1L);
        modalidade.setName("Pesquisa");;
    }

    @Test
    @DisplayName("Should get all modalidades")
    void testGetAllModalidades() {
        Modalidade modalidade2 = new Modalidade();
        modalidade2.setId(2L);
        modalidade2.setName("Extensão");

        List<Modalidade> modalidades = Arrays.asList(modalidade, modalidade2);
        when(modalidadeRepository.findAll()).thenReturn(modalidades);

        List<Modalidade> result = modalidadeService.getAllModalidades();

        assertEquals(2, result.size());
        verify(modalidadeRepository, times(1)).findAll();
    }

    @Test
    @DisplayName("Should get modalidade by id")
    void testGetModalidadeById() {
        when(modalidadeRepository.findById(1L)).thenReturn(Optional.of(modalidade));

        Optional<Modalidade> result = modalidadeService.getModalidadeById(1L);

        assertTrue(result.isPresent());
        assertEquals("Pesquisa", result.get().getName());
        verify(modalidadeRepository, times(1)).findById(1L);
    }

    @Test
    @DisplayName("Should create modalidade")
    void testCreateModalidade() {
        when(modalidadeRepository.save(any(Modalidade.class))).thenReturn(modalidade);

        Modalidade result = modalidadeService.createModalidade(modalidade);

        assertNotNull(result);
        assertEquals("Pesquisa", result.getName());
        verify(modalidadeRepository, times(1)).save(any(Modalidade.class));
    }

    @Test
    @DisplayName("Should update modalidade")
    void testUpdateModalidade() {
        Modalidade updatedModalidade = new Modalidade();
        updatedModalidade.setName("Pesquisa Atualizada");

        when(modalidadeRepository.findById(1L)).thenReturn(Optional.of(modalidade));
        when(modalidadeRepository.save(any(Modalidade.class))).thenReturn(modalidade);

        Modalidade result = modalidadeService.updateModalidade(1L, updatedModalidade);

        assertNotNull(result);
        verify(modalidadeRepository, times(1)).findById(1L);
        verify(modalidadeRepository, times(1)).save(any(Modalidade.class));
    }

    @Test
    @DisplayName("Should delete modalidade")
    void testDeleteModalidade() {
        modalidadeService.deleteModalidade(1L);

        verify(modalidadeRepository, times(1)).deleteById(1L);
    }
}
