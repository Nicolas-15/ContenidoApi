package com.Contenidos.Contenido.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Contenidos.Contenido.Model.cModel;
@Repository
public interface cRepository extends JpaRepository<cModel, Integer> {
}