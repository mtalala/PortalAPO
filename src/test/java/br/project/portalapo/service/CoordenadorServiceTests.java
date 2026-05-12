package br.project.portalapo.service;

import br.project.portalapo.model.Coordenador;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("CoordenadorService Tests")
class CoordenadorServiceTests {

    private CoordenadorService coordenadorService;

    private Coordenador coordenador;

    @BeforeEach
    void setUp() {
        coordenadorService = new CoordenadorService();

        coordenador = new Coordenador();
        coordenador.setId(1L);
        coordenador.setNome("Prof. Dr. Carlos");
        coordenador.setEmail("carlos@university.edu");
    }

    @Test
    @DisplayName("Should save coordenador")
    void testSaveCoordenador() {
        Coordenador result = coordenadorService.save(coordenador);

        assertNotNull(result);
        assertEquals("Prof. Dr. Carlos", result.getNome());
    }

    @Test
    @DisplayName("Should get all coordenadores")
    void testGetAllCoordenadores() {
        coordenadorService.save(coordenador);

        List<Coordenador> result = coordenadorService.findAll();

        assertEquals(1, result.size());
        assertEquals("Prof. Dr. Carlos", result.get(0).getNome());
    }

    @Test
    @DisplayName("Should maintain list state")
    void testInternalList() {
        coordenadorService.save(coordenador);

        List<Coordenador> list = coordenadorService.findAll();

        assertTrue(list.contains(coordenador));
    }
}