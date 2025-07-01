package com.Contenidos.Contenido.Service;

import com.Contenidos.Contenido.Model.cModel;
import com.Contenidos.Contenido.Repository.cRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.Date;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class cServiceTest {

    @Mock
    private cRepository repository;

    @InjectMocks
    private cService service;

    private cModel contenido;
    private final Integer CONTENIDO_ID = 1;

    @BeforeEach
    void setUp() {
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
    void testGetContenidos() {
        // Given
        List<cModel> contenidos = Arrays.asList(contenido, contenido);
        when(repository.findAll()).thenReturn(contenidos);

        // When
        List<cModel> result = service.getContenidos();

        // Then
        assertNotNull(result);
        assertEquals(2, result.size());
        verify(repository, times(1)).findAll();
    }

    @Test
    void testGetContenido() {
        // Given
        when(repository.findById(CONTENIDO_ID)).thenReturn(Optional.of(contenido));

        // When
        cModel result = service.getContenido(CONTENIDO_ID);

        // Then
        assertNotNull(result);
        assertEquals(CONTENIDO_ID, result.getIdContenido());
        assertEquals("Test Title", result.getTitulo());
        verify(repository, times(1)).findById(CONTENIDO_ID);
    }

    @Test
    void testSaveContenido() {
        // Given
        when(repository.save(any(cModel.class))).thenReturn(contenido);

        // When
        cModel result = service.saveContenido(contenido);

        // Then
        assertNotNull(result);
        assertEquals(CONTENIDO_ID, result.getIdContenido());
        assertEquals("Test Title", result.getTitulo());
        verify(repository, times(1)).save(any(cModel.class));
    }

    @Test
    void testDeleteContenido() {
        // Given
        doNothing().when(repository).deleteById(anyInt());

        // When
        service.deleteContenido(CONTENIDO_ID);

        // Then
        verify(repository, times(1)).deleteById(CONTENIDO_ID);
    }
}