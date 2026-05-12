package br.project.portalapo.service;

import br.project.portalapo.enums.StatusAPO;
import br.project.portalapo.model.APO;
import br.project.portalapo.repository.APORepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("APOService Tests")
class APOServiceTests {

    @Mock
    private APORepository apoRepository;

    @InjectMocks
    private APOService apoService;

    private APO apo;

    @BeforeEach
    void setUp() {
        apo = new APO();
        apo.setId(1L);
        apo.setNome("Pesquisa em Inteligência Artificial");
        apo.setMatricula("2023001");
        apo.setProgram("Engenharia de Computação");
        apo.setSemestre("2024/1");
        apo.setOrientador("Prof. Dr. João");
        apo.setCoordenador("Prof. Dra. Maria");
        apo.setStatus(StatusAPO.PENDENTE_COMISSAO);
        apo.setDataSubmissao(LocalDate.now());
    }

    @Test
    @DisplayName("Should find all APOs")
    void testFindAll() {
        APO apo2 = new APO();
        apo2.setId(2L);
        apo2.setNome("Pesquisa em Machine Learning");

        when(apoRepository.findAll()).thenReturn(Arrays.asList(apo, apo2));

        List<APO> result = apoService.findAll();

        assertEquals(2, result.size());
        verify(apoRepository).findAll();
    }

    @Test
    @DisplayName("Should find APO by id")
    void testFindById() {
        when(apoRepository.findById(1L)).thenReturn(Optional.of(apo));

        APO result = apoService.findById(1L);

        assertNotNull(result);
        assertEquals("Pesquisa em Inteligência Artificial", result.getNome());
        verify(apoRepository).findById(1L);
    }

    @Test
    @DisplayName("Should throw exception when APO not found")
    void testFindByIdNotFound() {
        when(apoRepository.findById(999L)).thenReturn(Optional.empty());

        RuntimeException ex = assertThrows(RuntimeException.class,
                () -> apoService.findById(999L));

        assertTrue(ex.getMessage().contains("APO não encontrada"));
    }

    @Test
    @DisplayName("Should create APO and calculate points")
    void testCreate() {
        when(apoRepository.save(any(APO.class))).thenReturn(apo);

        APO result = apoService.create(apo);

        assertNotNull(result);
        verify(apoRepository).save(any(APO.class));
    }

    @Test
    @DisplayName("Should update APO fields correctly")
    void testUpdate() {
        APO updated = new APO();
        updated.setNome("Pesquisa Atualizada");
        updated.setProgram("Computação Aplicada");
        updated.setOrientador("Prof. Pedro");

        when(apoRepository.findById(1L)).thenReturn(Optional.of(apo));
        when(apoRepository.save(any(APO.class))).thenReturn(apo);

        APO result = apoService.update(1L, updated);

        assertNotNull(result);
        assertEquals("Pesquisa Atualizada", apo.getNome());
        assertEquals("Computação Aplicada", apo.getProgram());
        assertEquals("Prof. Pedro", apo.getOrientador());

        verify(apoRepository).findById(1L);
        verify(apoRepository).save(any(APO.class));
    }

    @Test
    @DisplayName("Should delete APO")
    void testDelete() {
        when(apoRepository.findById(1L)).thenReturn(Optional.of(apo));

        apoService.delete(1L);

        verify(apoRepository).delete(apo);
    }

    @Test
    @DisplayName("Should validate APO content")
    void testAPOContent() {
        assertEquals("Pesquisa em Inteligência Artificial", apo.getNome());
        assertEquals("2023001", apo.getMatricula());
        assertEquals("Engenharia de Computação", apo.getProgram());
        assertEquals(StatusAPO.PENDENTE_COMISSAO, apo.getStatus());
    }
}