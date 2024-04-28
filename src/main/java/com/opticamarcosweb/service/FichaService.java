package com.opticamarcosweb.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.opticamarcosweb.model.Ficha;
import com.opticamarcosweb.repository.FichaRepository;

@Service
public class FichaService{

	@Autowired
	private FichaRepository fichaRepository;
	
	public List<Ficha> getAllFichas(){
		return fichaRepository.findAll();
	}
	
	public Optional<Ficha> getFichaById(Integer IDFicha) {
		return fichaRepository.findById(IDFicha);
	}
	
	public Ficha addFicha(Ficha ficha) {
		return fichaRepository.save(ficha);
	}
	
	public void deleteFicha(Integer IDFicha) {
		fichaRepository.deleteById(IDFicha);
	}
	
	public Ficha updateFicha(Ficha ficha) {
		return fichaRepository.save(ficha);
	}

	public List<Ficha> getAllFichasByCriteria(Integer nombre, Boolean seniado, Boolean saldado, Date fechaDesde, Date fechaHasta, Double totalDesde, Double totalHasta) {
		return null;
		// TODO Auto-generated method stub
		//return fichaRepository.getAllFichasByCriteria(nombre,seniado,saldado,fechaDesde,fechaHasta,totalDesde,totalHasta);
	}
}
