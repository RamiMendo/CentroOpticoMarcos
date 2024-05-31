package com.opticamarcosweb.repository;

import com.opticamarcosweb.model.Ficha;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.opticamarcosweb.model.Cliente;

import java.util.List;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente,Integer>{

    @Query(value = "SELECT SUM(Saldo) FROM Fichas WHERE id_cliente = :id ",nativeQuery = true)
    Double getSaldoCliente(@Param("id") Integer id);
}
