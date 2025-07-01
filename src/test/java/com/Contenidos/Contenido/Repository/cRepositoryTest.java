package com.Contenidos.Contenido.Repository;

import com.Contenidos.Contenido.Model.cModel;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@ActiveProfiles("test")
public class cRepositoryTest {

    @Autowired
    private cRepository repository;

    @Test
    public void testSaveContenido() {
        // Given
        cModel contenido = createSampleContenido();

        // When
        cModel savedContenido = repository.save(contenido);

        // Then
        assertNotNull(savedContenido.getIdContenido());
        assertEquals(contenido.getTitulo(), savedContenido.getTitulo());
        assertEquals(contenido.getDescripcion(), savedContenido.getDescripcion());
    }

    @Test
    public void testFindById() {
        // Given
        cModel contenido = createSampleContenido();
        cModel savedContenido = repository.save(contenido);

        // When
        Optional<cModel> foundContenido = repository.findById(savedContenido.getIdContenido());

        // Then
        assertTrue(foundContenido.isPresent());
        assertEquals(savedContenido.getIdContenido(), foundContenido.get().getIdContenido());
        assertEquals(contenido.getTitulo(), foundContenido.get().getTitulo());
    }

    @Test
    public void testFindAll() {
        // Given
        repository.save(createSampleContenido());
        repository.save(createSampleContenido());

        // When
        List<cModel> contenidos = repository.findAll();

        // Then
        assertFalse(contenidos.isEmpty());
        assertEquals(2, contenidos.size());
    }

    @Test
    public void testDeleteById() {
        // Given
        cModel contenido = createSampleContenido();
        cModel savedContenido = repository.save(contenido);

        // When
        repository.deleteById(savedContenido.getIdContenido());
        Optional<cModel> foundContenido = repository.findById(savedContenido.getIdContenido());

        // Then
        assertFalse(foundContenido.isPresent());
    }

    private cModel createSampleContenido() {
        return cModel.builder()
                .idInstructor(1)
                .titulo("Test Title")
                .descripcion("Test Description")
                .urlContenido("https://test.com/" + Math.random())
                .tipoContenido("Video")
                .fechaCreacion(Date.valueOf(LocalDate.now()))
                .build();
    }
}
