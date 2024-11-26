package com.opticamarcos.repository;

import com.opticamarcos.model.entity.Archivo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArchivoRepository extends JpaRepository<Archivo, Integer> {
}
