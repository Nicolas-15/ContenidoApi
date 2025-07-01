package com.Contenidos.Contenido.Controller;

import com.Contenidos.Contenido.ContenidoModelAssembler;
import org.springframework.web.bind.annotation.RestController;
import com.Contenidos.Contenido.Model.cModel;
import com.Contenidos.Contenido.Service.cService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.CollectionModel;

import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;


@RestController
@RequestMapping("/api/v2/contenido")
public class cControllerV2 {

    @Autowired
    private cService contenidoService;

    @Autowired
    private ContenidoModelAssembler assembler;

    @GetMapping("")
    public CollectionModel<EntityModel<cModel>> listaContenido() {
        List<EntityModel<cModel>> contenidos = contenidoService.getContenidos().stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());

        return CollectionModel.of(contenidos,
                linkTo(methodOn(cControllerV2.class).listaContenido()).withSelfRel());
    }

    @PostMapping("")
    public cModel agregarContenido(@RequestBody cModel contenido){
        return contenidoService.saveContenido(contenido);
    }

    @GetMapping("/{idContenido}")
    public EntityModel<cModel> obtenerContenidoPorID(@PathVariable int idContenido) {
        cModel contenido = contenidoService.getContenido(idContenido);
        return assembler.toModel(contenido);
    }

    @PutMapping("/{idContenido}")
    public cModel actualizarContenido(@RequestBody cModel contenido, @PathVariable int idContenido) {
        return contenidoService.saveContenido(contenido);
    }

    @DeleteMapping("/{idContenido}")
    public void eliminarContenido(@PathVariable int idContenido){
        contenidoService.deleteContenido(idContenido);
    }
}

