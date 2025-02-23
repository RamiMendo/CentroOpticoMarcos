package com.opticamarcos.repository;

import com.opticamarcos.model.entity.Ficha;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;
import java.util.List;

public interface FichaRepositoryCustom {

    Page<Ficha> getFichasByFiltro(Pageable pageable, LocalDate fechaDesde, LocalDate fechaHasta, Double totalDesde, Double totalHasta, Integer estaSeniado, Integer estaPago);

}
