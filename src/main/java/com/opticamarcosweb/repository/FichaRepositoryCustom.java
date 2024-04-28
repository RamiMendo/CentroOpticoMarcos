package com.opticamarcosweb.repository;

import java.util.Date;
import java.util.List;

import com.opticamarcosweb.model.Ficha;

public interface FichaRepositoryCustom {
	List<Ficha> getAllFichasByCriteria(Integer nombre, Boolean seniado, Boolean saldado, Date fechaDesde, Date fechaHasta, Double totalDesde, Double totalHasta);
}
