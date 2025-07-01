package com.Contenidos.Contenido.Controller;

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


@RestController
@RequestMapping("/api/v1/contenido")
public class cController {
    
    @Autowired
    private cService contenidoService;

    @GetMapping("")
    public List<cModel> listaContenido(){
        return contenidoService.getContenidos();
    }

    //Guardar
    @PostMapping("")
    public cModel agregarContenido(@RequestBody cModel contenido){
        return contenidoService.saveContenido(contenido);
    } 

    @GetMapping("/{idContenido}")
    public cModel obtenerContenidoPorID(@PathVariable int idContenido) {
        return contenidoService.getContenido(idContenido);
    }

    //Actualizar
    @PutMapping("/{idContenido}")
    public cModel actualizarContenido(@RequestBody cModel contenido, @PathVariable int idContenido) { 
        return contenidoService.saveContenido(contenido);
    } 

    @DeleteMapping("/{idContenido}")
    public void eliminarContenido(@PathVariable int idContenido){
        contenidoService.deleteContenido(idContenido);
    } 
}

