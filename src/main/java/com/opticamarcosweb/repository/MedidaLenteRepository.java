package com.opticamarcosweb.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.opticamarcosweb.model.MedidaLente;
@Repository
public interface MedidaLenteRepository extends JpaRepository<MedidaLente, Integer> {

}
