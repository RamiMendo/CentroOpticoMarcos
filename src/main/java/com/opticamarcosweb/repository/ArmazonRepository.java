package com.opticamarcosweb.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.opticamarcosweb.model.Armazon;
@Repository
public interface ArmazonRepository extends JpaRepository<Armazon, Integer>{}
