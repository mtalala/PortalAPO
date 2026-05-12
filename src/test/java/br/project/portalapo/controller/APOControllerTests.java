package br.project.portalapo.controller;

import br.project.portalapo.model.APO;
import br.project.portalapo.enums.StatusAPO;
import br.project.portalapo.service.APOService;
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

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@DisplayName("APOController Tests")
class APOControllerTests {

    @Mock
    private APOService apoService;

    @InjectMocks
    private APOController apoController;

    private MockMvc mockMvc;
    private ObjectMapper objectMapper;

    private APO apo;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(apoController).build();
        objectMapper = new ObjectMapper();

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
    void testFindAll() throws Exception {

        APO apo2 = new APO();
        apo2.setId(2L);
        apo2.setNome("Machine Learning");

        when(apoService.findAll()).thenReturn(Arrays.asList(apo, apo2));

        mockMvc.perform(get("/api/apos"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2));

        verify(apoService).findAll();
    }

    @Test
    @DisplayName("Should find APO by id")
    void testFindById() throws Exception {

        when(apoService.findById(1L)).thenReturn(apo);

        mockMvc.perform(get("/api/apos/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.nome").value("Pesquisa em Inteligência Artificial"))
                .andExpect(jsonPath("$.status").value("PENDENTE"));

        verify(apoService).findById(1L);
    }

    @Test
    @DisplayName("Should create APO")
    void testCreate() throws Exception {

        when(apoService.create(any(APO.class))).thenReturn(apo);

        mockMvc.perform(post("/api/apos")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(apo)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.nome").value("Pesquisa em Inteligência Artificial"));

        verify(apoService).create(any(APO.class));
    }

    @Test
    @DisplayName("Should update APO")
    void testUpdate() throws Exception {

        when(apoService.update(eq(1L), any(APO.class))).thenReturn(apo);

        mockMvc.perform(put("/api/apos/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(apo)))
                .andExpect(status().isOk());

        verify(apoService).update(eq(1L), any(APO.class));
    }

    @Test
    @DisplayName("Should delete APO")
    void testDelete() throws Exception {

        doNothing().when(apoService).delete(1L);

        mockMvc.perform(delete("/api/apos/1"))
                .andExpect(status().isNoContent());

        verify(apoService).delete(1L);
    }

    @Test
    @DisplayName("Should approve orientador")
    void testAprovarOrientador() throws Exception {

        when(apoService.aprovarOrientador(1L)).thenReturn(apo);

        mockMvc.perform(post("/api/apos/1/aprovar-orientador"))
                .andExpect(status().isOk());

        verify(apoService).aprovarOrientador(1L);
    }

    @Test
    @DisplayName("Should approve coordenador")
    void testAprovarCoordenador() throws Exception {

        when(apoService.aprovarCoordenador(1L)).thenReturn(apo);

        mockMvc.perform(post("/api/apos/1/aprovar-coordenador"))
                .andExpect(status().isOk());

        verify(apoService).aprovarCoordenador(1L);
    }

    @Test
    @DisplayName("Should reject APO")
    void testRejeitar() throws Exception {

        when(apoService.rejeitar(1L)).thenReturn(apo);

        mockMvc.perform(post("/api/apos/1/rejeitar"))
                .andExpect(status().isOk());

        verify(apoService).rejeitar(1L);
    }
}