package com.opticamarcos.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.opticamarcos.model.entity.Ficha;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface FichaRepository extends JpaRepository<Ficha,Integer>, FichaRepositoryCustom{

      @Query(value = "SELECT * FROM Fichas WHERE fecha between :fechaDesde and :fechaHasta", nativeQuery = true)
      Page<Ficha> findAllByFechas(Pageable pageable, LocalDate fechaDesde, LocalDate fechaHasta);

      @Query(value = "SELECT * FROM Fichas WHERE id_cliente = :idCliente", nativeQuery = true)
      List<Ficha> findAllByidCliente(@Param("idCliente") Integer idCliente);

      //DERIVED QUERY
      Page<Ficha> getFichasByFiltro(Pageable pageable, LocalDate fechaDesde, LocalDate fechaHasta, Double totalDesde, Double totalHasta, Integer estaSeniado, Integer estaPago);

      @Query(value = "SELECT COUNT(*) FROM Fichas WHERE id_cliente = :idCliente", nativeQuery = true)
      Integer tieneFicha(Integer idCliente);

      @Modifying
      @Query(value = "UPDATE Fichas SET id_cliente = :idCliente WHERE id_ficha = :idFicha", nativeQuery = true)
      void updateCliente(Integer idFicha, Integer idCliente);

      @Modifying //Hace que la query sea una query de modificacion
      @Query(value = "UPDATE Fichas SET Saldo = 0 WHERE id_ficha = :id", nativeQuery = true)
      void updateSaldoCero(@Param("id")Integer id);

      @Modifying
      @Query(value = "UPDATE Fichas SET esta_impreso = true WHERE id_ficha = :id", nativeQuery = true)
      void updateImpresion(@Param("id") Integer id);

}
