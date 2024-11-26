package com.opticamarcos.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.opticamarcos.model.entity.Cliente;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente,Integer>{

    @Query(value = "SELECT SUM(Saldo) FROM Fichas WHERE id_cliente = :id ",nativeQuery = true)
    Double getSaldoCliente(@Param("id") Integer id);

    @Query(value = "SELECT C.* FROM Clientes C LEFT JOIN Fichas F ON C.id_cliente = F.id_cliente WHERE F.id_cliente is null;", nativeQuery = true)
    Page<Cliente> findAllNoTieneFichas(Pageable pageable);
}
