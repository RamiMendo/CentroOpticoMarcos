package com.opticamarcosweb.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.opticamarcosweb.model.Ficha;
@Repository
public interface FichaRepository extends JpaRepository<Ficha,Integer>{

}
