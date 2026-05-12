package br.project.portalapo.controller;

import br.project.portalapo.model.VisualColor;
import br.project.portalapo.service.VisualColorService;
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
import java.util.Optional;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@DisplayName("VisualColorController Tests")
class VisualColorControllerTests {

    @Mock
    private VisualColorService visualColorService;

    @InjectMocks
    private VisualColorController visualColorController;

    private MockMvc mockMvc;
    private ObjectMapper objectMapper;

    private VisualColor visualColor;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(visualColorController).build();
        objectMapper = new ObjectMapper();

        visualColor = new VisualColor();
        visualColor.setId(1L);
        visualColor.setName("Vermelho");
        visualColor.setColor("#FF0000");
        visualColor.setDescription("Cor forte");
    }

    @Test
    @DisplayName("Should get all visual colors")
    void testGetAll() throws Exception {

        VisualColor c2 = new VisualColor();
        c2.setId(2L);
        c2.setName("Azul");
        c2.setColor("#0000FF");

        when(visualColorService.getAllVisualColors())
                .thenReturn(Arrays.asList(visualColor, c2));

        mockMvc.perform(get("/api/visual-colors"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2));

        verify(visualColorService).getAllVisualColors();
    }

    @Test
    @DisplayName("Should get visual color by id")
    void testGetById() throws Exception {

        when(visualColorService.getVisualColorById(1L))
                .thenReturn(Optional.of(visualColor));

        mockMvc.perform(get("/api/visual-colors/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("Vermelho"))
                .andExpect(jsonPath("$.color").value("#FF0000"));

        verify(visualColorService).getVisualColorById(1L);
    }

    @Test
    @DisplayName("Should return 404 when not found")
    void testGetByIdNotFound() throws Exception {

        when(visualColorService.getVisualColorById(999L))
                .thenReturn(Optional.empty());

        mockMvc.perform(get("/api/visual-colors/999"))
                .andExpect(status().isNotFound());

        verify(visualColorService).getVisualColorById(999L);
    }

    @Test
    @DisplayName("Should create visual color")
    void testCreate() throws Exception {

        when(visualColorService.createVisualColor(any()))
                .thenReturn(visualColor);

        mockMvc.perform(post("/api/visual-colors")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(visualColor)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Vermelho"));

        verify(visualColorService).createVisualColor(any());
    }

    @Test
    @DisplayName("Should update visual color successfully")
    void testUpdateSuccess() throws Exception {

        when(visualColorService.updateVisualColor(eq(1L), any()))
                .thenReturn(visualColor);

        mockMvc.perform(put("/api/visual-colors/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(visualColor)))
                .andExpect(status().isOk());

        verify(visualColorService).updateVisualColor(eq(1L), any());
    }

    @Test
    @DisplayName("Should return 404 on update when service throws exception")
    void testUpdateNotFound() throws Exception {

        when(visualColorService.updateVisualColor(eq(1L), any()))
                .thenThrow(new RuntimeException("not found"));

        mockMvc.perform(put("/api/visual-colors/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(visualColor)))
                .andExpect(status().isNotFound());

        verify(visualColorService).updateVisualColor(eq(1L), any());
    }

    @Test
    @DisplayName("Should delete visual color")
    void testDelete() throws Exception {

        doNothing().when(visualColorService).deleteVisualColor(1L);

        mockMvc.perform(delete("/api/visual-colors/1"))
                .andExpect(status().isNoContent());

        verify(visualColorService).deleteVisualColor(1L);
    }
}