package br.project.portalapo.service;

import br.project.portalapo.model.VisualColor;
import br.project.portalapo.repository.VisualColorRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("VisualColorService Tests")
class VisualColorServiceTests {

    @Mock
    private VisualColorRepository visualColorRepository;

    @InjectMocks
    private VisualColorService visualColorService;

    private VisualColor visualColor;

    @BeforeEach
    void setUp() {
        visualColor = new VisualColor();
        visualColor.setId(1L);
        visualColor.setName("Vermelho");
        visualColor.setColor("#FF0000");
        visualColor.setDescription("Cor vermelha padrão");
    }

    @Test
    @DisplayName("Should get all visual colors")
    void testGetAllVisualColors() {
        VisualColor visualColor2 = new VisualColor();
        visualColor2.setId(2L);
        visualColor2.setName("Azul");
        visualColor2.setColor("#0000FF");
        visualColor2.setDescription("Cor azul padrão");

        List<VisualColor> colors = Arrays.asList(visualColor, visualColor2);
        when(visualColorRepository.findAll()).thenReturn(colors);

        List<VisualColor> result = visualColorService.getAllVisualColors();

        assertEquals(2, result.size());
        verify(visualColorRepository, times(1)).findAll();
    }

    @Test
    @DisplayName("Should get visual color by id")
    void testGetVisualColorById() {
        when(visualColorRepository.findById(1L)).thenReturn(Optional.of(visualColor));

        Optional<VisualColor> result = visualColorService.getVisualColorById(1L);

        assertTrue(result.isPresent());
        assertEquals("Vermelho", result.get().getName());
        assertEquals("#FF0000", result.get().getColor());
        verify(visualColorRepository, times(1)).findById(1L);
    }

    @Test
    @DisplayName("Should create visual color")
    void testCreateVisualColor() {
        when(visualColorRepository.save(any(VisualColor.class))).thenReturn(visualColor);

        VisualColor result = visualColorService.createVisualColor(visualColor);

        assertNotNull(result);
        assertEquals("Vermelho", result.getName());
        verify(visualColorRepository, times(1)).save(any(VisualColor.class));
    }

    @Test
    @DisplayName("Should update visual color")
    void testUpdateVisualColor() {
        VisualColor updated = new VisualColor();
        updated.setName("Vermelho Escuro");
        updated.setColor("#8B0000");
        updated.setDescription("Tom mais escuro");

        when(visualColorRepository.findById(1L)).thenReturn(Optional.of(visualColor));
        when(visualColorRepository.save(any(VisualColor.class))).thenReturn(visualColor);

        VisualColor result = visualColorService.updateVisualColor(1L, updated);

        assertNotNull(result);

        assertEquals("Vermelho Escuro", visualColor.getName());
        assertEquals("#8B0000", visualColor.getColor());
        assertEquals("Tom mais escuro", visualColor.getDescription());

        verify(visualColorRepository, times(1)).findById(1L);
        verify(visualColorRepository, times(1)).save(any(VisualColor.class));
    }

    @Test
    @DisplayName("Should delete visual color")
    void testDeleteVisualColor() {
        visualColorService.deleteVisualColor(1L);

        verify(visualColorRepository, times(1)).deleteById(1L);
    }
}