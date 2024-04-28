package com.opticamarcosweb.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.opticamarcosweb.model.Lente;
@Repository
public interface LenteRepository extends JpaRepository<Lente, Integer> {

}
