package com.Contenidos.Contenido;

import com.Contenidos.Contenido.Controller.cControllerV2;
import com.Contenidos.Contenido.Model.cModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@Component
public class ContenidoModelAssembler implements RepresentationModelAssembler<cModel, EntityModel<cModel>> {
    @Override
    public EntityModel<cModel> toModel(cModel contenido) {
        return EntityModel.of(contenido,
                linkTo(methodOn(cControllerV2.class).obtenerContenidoPorID(contenido.getIdContenido())).withSelfRel(),
                linkTo(methodOn(cControllerV2.class).listaContenido()).withRel("contenido"));
    }
}
