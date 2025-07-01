package com.Contenidos.Contenido.Service;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.Contenidos.Contenido.Model.cModel;
import com.Contenidos.Contenido.Repository.cRepository;
import jakarta.transaction.Transactional;

@Transactional
@Service
public class cService {

    @Autowired
    private cRepository contenidoRepository;

    public List<cModel> getContenidos(){
        return contenidoRepository.findAll();
    }

    public cModel getContenido(Integer idContenido){
        return contenidoRepository.findById(idContenido).get();
    }

    public cModel saveContenido(cModel contenido){
        return contenidoRepository.save(contenido);
    }
    
    public void deleteContenido(Integer idContenido){
        contenidoRepository.deleteById(idContenido);
    }
}