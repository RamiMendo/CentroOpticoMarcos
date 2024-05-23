package com.opticamarcosweb.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.opticamarcosweb.model.Ficha;
import com.opticamarcosweb.repository.FichaRepository;

@Service
public class FichaService {

	@Autowired
	private FichaRepository fichaRepository;
	
	public List<Ficha> getAllFichas(){
		return fichaRepository.findAll();
	}
	
	public Optional<Ficha> getFichaById(Integer IDFicha) {
		return fichaRepository.findById(IDFicha);
	}

	public void addFicha(Ficha ficha){
		ficha.setSaldo(ficha.getTotal() - ficha.getSenia());
		fichaRepository.save(ficha);
	}
	
	public void deleteFicha(Integer IDFicha) {
		fichaRepository.deleteById(IDFicha);
	}
	
	public Ficha updateFicha(Ficha ficha) {
		return fichaRepository.save(ficha);
	}

    @Transactional
	public void updateSaldoCero(Integer id){
		fichaRepository.updateSaldoCero(id);
	}

	public List<Ficha> getAllFichasByFiltro(LocalDate fechaDesde, LocalDate fechaHasta, Double totalDesde, Double totalHasta, Boolean estaSeniado, Boolean estaPagado){
		List<Ficha> listaFichas = new ArrayList<Ficha>();
		listaFichas = fichaRepository.getAllFichasByFiltro(fechaDesde, fechaHasta, totalDesde, totalHasta);
		return listaFichas;
//		if(estaPagado){
//			listaFichas = listaFichas
//					.stream()
//					.filter((f) -> f.getSaldo().equals(0)).collect(Collectors.toList());
//		}else{
//			listaFichas = listaFichas
//					.stream()
//					.filter((f) -> !f.getSaldo().equals(0)).collect(Collectors.toList());
//		}
//
//		if(estaSeniado){
//			listaFichas = listaFichas
//					.stream()
//					.filter((f) -> f.getSenia().equals(0)).collect(Collectors.toList());
//		}else{
//			listaFichas = listaFichas
//					.stream()
//					.filter((f) -> !f.getSenia().equals(0)).collect(Collectors.toList());
//		}


	}
}
