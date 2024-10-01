package com.opticamarcos.repository;

import com.opticamarcos.model.Ficha;

import java.time.LocalDate;
import java.util.List;

public interface FichaRepositoryCustom {

    List<Ficha> getFichasByFiltro(LocalDate fechaDesde, LocalDate fechaHasta, Double totalDesde, Double totalHasta, Integer estaSeniado, Integer estaPago);

}
