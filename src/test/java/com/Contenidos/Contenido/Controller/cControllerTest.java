package com.Contenidos.Contenido.Controller;

import com.Contenidos.Contenido.Model.cModel;
import com.Contenidos.Contenido.Service.cService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.sql.Date;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
public class cControllerTest {

    private MockMvc mockMvc;

    @Mock
    private cService service;

    @InjectMocks
    private cController controller;

    private ObjectMapper objectMapper = new ObjectMapper();

    private cModel contenido;
    private final Integer CONTENIDO_ID = 1;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();

        contenido = cModel.builder()
                .idContenido(CONTENIDO_ID)
                .idInstructor(1)
                .titulo("Test Title")
                .descripcion("Test Description")
                .urlContenido("https://test.com/test")
                .tipoContenido("Video")
                .fechaCreacion(Date.valueOf(LocalDate.now()))
                .build();
    }

    @Test
    void testListaContenido() throws Exception {
        // Given
        List<cModel> contenidos = Arrays.asList(contenido, contenido);
        when(service.getContenidos()).thenReturn(contenidos);
        // When & Then
        mockMvc.perform(get("/api/v1/contenido"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].idContenido", is(CONTENIDO_ID)))
                .andExpect(jsonPath("$[0].titulo", is("Test Title")));

        verify(service, times(1)).getContenidos();
    }

    @Test
    void testObtenerContenidoPorID() throws Exception {
        // Given
        when(service.getContenido(CONTENIDO_ID)).thenReturn(contenido);
        // When & Then
        mockMvc.perform(get("/api/v1/contenido/{idContenido}", CONTENIDO_ID))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.idContenido", is(CONTENIDO_ID)))
                .andExpect(jsonPath("$.titulo", is("Test Title")));

        verify(service, times(1)).getContenido(CONTENIDO_ID);
    }

    @Test
    void testAgregarContenido() throws Exception {
        // Given
        when(service.saveContenido(any(cModel.class))).thenReturn(contenido);
        // When & Then
        mockMvc.perform(post("/api/v1/contenido")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(contenido)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.idContenido", is(CONTENIDO_ID)))
                .andExpect(jsonPath("$.titulo", is("Test Title")));

        verify(service, times(1)).saveContenido(any(cModel.class));
    }

    @Test
    void testActualizarContenido() throws Exception {
        // Given
        when(service.saveContenido(any(cModel.class))).thenReturn(contenido);
        // When & Then
        mockMvc.perform(put("/api/v1/contenido/{idContenido}", CONTENIDO_ID)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(contenido)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.idContenido", is(CONTENIDO_ID)))
                .andExpect(jsonPath("$.titulo", is("Test Title")));

        verify(service, times(1)).saveContenido(any(cModel.class));
    }

    @Test
    void testEliminarContenido() throws Exception {
        // Given
        doNothing().when(service).deleteContenido(anyInt());
        // When & Then
        mockMvc.perform(delete("/api/v1/contenido/{idContenido}", CONTENIDO_ID))
                .andExpect(status().isOk());

        verify(service, times(1)).deleteContenido(CONTENIDO_ID);
    }
}
