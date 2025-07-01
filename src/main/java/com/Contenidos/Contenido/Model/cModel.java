package com.Contenidos.Contenido.Model;

import java.sql.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Table(name = "CONTENIDO")
@Data
@AllArgsConstructor 
@NoArgsConstructor
@Builder
public class cModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idContenido;

    @Column(unique = false,nullable = true)
    private Integer idInstructor;

    @Column(unique = false,nullable = true)
    private String titulo;

    @Column(nullable = true)
    private String descripcion;
    
    @Column(unique = true,nullable = true)
    private String urlContenido;
    
    @Column(unique = false, nullable = true)
    private String tipoContenido;
    
    @Column(nullable = true)
    private Date fechaCreacion;
      
}
