package com.opticamarcosweb.repository;

import com.opticamarcosweb.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.opticamarcosweb.model.Ficha;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface FichaRepository extends JpaRepository<Ficha,Integer>{

      @Query(value = "SELECT * FROM Fichas WHERE id_cliente = :idCliente", nativeQuery = true)
      List<Ficha> findAllByidCliente(@Param("idCliente") Integer idCliente);

      @Query(value = "SELECT * FROM Fichas WHERE fecha between :fechaDesde and :fechaHasta and total between :totalDesde and :totalHasta", nativeQuery = true)
      List<Ficha> getAllFichasByFiltro(LocalDate fechaDesde, LocalDate fechaHasta, Double totalDesde, Double totalHasta);

      @Modifying //Hace que la query sea una query de modificacion
      @Query(value = "UPDATE Fichas SET Saldo = 0 WHERE id_ficha = :id", nativeQuery = true)
      void updateSaldoCero(@Param("id")Integer id);


}
