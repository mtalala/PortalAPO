package br.project.portalapo.controller;

import br.project.portalapo.model.Modalidade;
import br.project.portalapo.service.ModalidadeService;
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
import java.util.Optional;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@DisplayName("ModalidadeController Tests")
class ModalidadeControllerTests {

    @Mock
    private ModalidadeService modalidadeService;

    @InjectMocks
    private ModalidadeController modalidadeController;

    private MockMvc mockMvc;
    private Modalidade modalidade;
    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(modalidadeController).build();
        objectMapper = new ObjectMapper();

        modalidade = new Modalidade();
        modalidade.setId(1L);
        modalidade.setName("Pesquisa");
    }

    @Test
    @DisplayName("Should get all modalidades")
    void testGetAllModalidades() throws Exception {
        Modalidade modalidade2 = new Modalidade();
        modalidade2.setId(2L);
        modalidade2.setName("Extensão");

        List<Modalidade> modalidades = Arrays.asList(modalidade, modalidade2);
        when(modalidadeService.getAllModalidades()).thenReturn(modalidades);

        mockMvc.perform(get("/api/modalidades"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2));

        verify(modalidadeService, times(1)).getAllModalidades();
    }

    @Test
    @DisplayName("Should get modalidade by id")
    void testGetModalidadeById() throws Exception {
        when(modalidadeService.getModalidadeById(1L)).thenReturn(Optional.of(modalidade));

        mockMvc.perform(get("/api/modalidades/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.nome").value("Pesquisa"));

        verify(modalidadeService, times(1)).getModalidadeById(1L);
    }

    @Test
    @DisplayName("Should return 404 when modalidade not found")
    void testGetModalidadeByIdNotFound() throws Exception {
        when(modalidadeService.getModalidadeById(999L)).thenReturn(Optional.empty());

        mockMvc.perform(get("/api/modalidades/999"))
                .andExpect(status().isNotFound());

        verify(modalidadeService, times(1)).getModalidadeById(999L);
    }

    @Test
    @DisplayName("Should create modalidade")
    void testCreateModalidade() throws Exception {
        when(modalidadeService.createModalidade(any(Modalidade.class))).thenReturn(modalidade);

        mockMvc.perform(post("/api/modalidades")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(modalidade)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.nome").value("Pesquisa"));

        verify(modalidadeService, times(1)).createModalidade(any(Modalidade.class));
    }

    @Test
    @DisplayName("Should update modalidade")
    void testUpdateModalidade() throws Exception {
        Modalidade updatedModalidade = new Modalidade();
        updatedModalidade.setName("Pesquisa Atualizada");

        when(modalidadeService.updateModalidade(eq(1L), any(Modalidade.class))).thenReturn(modalidade);

        mockMvc.perform(put("/api/modalidades/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(updatedModalidade)))
                .andExpect(status().isOk());

        verify(modalidadeService, times(1)).updateModalidade(eq(1L), any(Modalidade.class));
    }

    @Test
    @DisplayName("Should delete modalidade")
    void testDeleteModalidade() throws Exception {
        doNothing().when(modalidadeService).deleteModalidade(1L);

        mockMvc.perform(delete("/api/modalidades/1"))
                .andExpect(status().isNoContent());

        verify(modalidadeService, times(1)).deleteModalidade(1L);
    }
}
