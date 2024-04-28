package com.opticamarcosweb.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.opticamarcosweb.model.Cristal;
@Repository
public interface CristalRepository extends JpaRepository<Cristal, Integer>{}
