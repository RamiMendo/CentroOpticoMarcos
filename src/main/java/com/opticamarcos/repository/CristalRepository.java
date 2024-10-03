package com.opticamarcos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.opticamarcos.model.Cristal;
@Repository
public interface CristalRepository extends JpaRepository<Cristal, Integer>{}
