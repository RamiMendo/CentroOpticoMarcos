package com.opticamarcos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.opticamarcos.model.entity.Cristal;

import java.util.Optional;

@Repository
public interface CristalRepository extends JpaRepository<Cristal, Integer>{

    Optional<Cristal> findByNombre(String nombre);

}
