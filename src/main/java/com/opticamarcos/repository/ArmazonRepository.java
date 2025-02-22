package com.opticamarcos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.opticamarcos.model.entity.Armazon;

import java.util.List;
import java.util.Optional;

@Repository
public interface ArmazonRepository extends JpaRepository<Armazon, Integer>, ArmazonRepositoryCustom {

    Optional<Armazon> findByNombre(String nombre);

    //CUSTOM DERIVED QUERY
    List<Armazon> findArmazonesByPrecio(Integer currentPage, Double regularDesde, Double regularHasta);

//    List<Armazon> findByTieneDescuento(Integer currentPage, Boolean tieneDescuento);
//    List<Armazon> findArmazonesByPreciosDescuento(Integer currentPage, Double descuentoDesde, Double descuentoHasta);
//    List<Armazon> findArmazonesByPreciosTarjeta(Integer currentPage, Double tarjetaDesde, Double tarjetaHasta);
}
