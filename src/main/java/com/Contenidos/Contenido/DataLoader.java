package com.Contenidos.Contenido;

import com.Contenidos.Contenido.Model.*;
import com.Contenidos.Contenido.Repository.*;
import net.datafaker.Faker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.time.LocalDate;

@Profile("dev")
@Component
public class DataLoader  implements CommandLineRunner {

    Faker faker = new Faker();

    @Autowired
    private cRepository cRepository;

    @Override
    public void run(String... args) throws Exception {
        //Contenidos
        for (int i = 0; i < 10; i++) {
            cModel contenido = cModel.builder()
                    .idInstructor(faker.number().numberBetween(1, 100))
                    .titulo(faker.book().title())
                    .descripcion(faker.lorem().sentence(10))
                    .urlContenido("https://contenido.fake/" + faker.internet().slug())
                    .tipoContenido(faker.options().option("Video", "Articulo", "Podcast", "Documento"))
                    .fechaCreacion(Date.valueOf(LocalDate.now().minusDays(faker.number().numberBetween(0, 365))))
                    .build();
            cRepository.save(contenido);
        }
    }
}
