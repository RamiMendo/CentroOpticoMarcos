package com.opticamarcos.repository;

import com.opticamarcos.model.Armazon;

import java.util.List;

public interface ArmazonRepositoryCustom {

//    List<Armazon> findByTieneDescuento(Integer currentPage, Boolean tieneDescuento);
    List<Armazon> findArmazonesByPrecio(Integer currentPage, Double regularDesde, Double regularHasta);
//    List<Armazon> findArmazonesByPreciosDescuento(Integer currentPage, Double descuentoDesde, Double descuentoHasta);
//    List<Armazon> findArmazonesByPreciosTarjeta(Integer currentPage, Double tarjetaDesde, Double tarjetaHasta);
}
