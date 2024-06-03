package com.opticamarcosweb.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.opticamarcosweb.exceptions.EntidadException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
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

	public Page<Ficha> getAllFichasPageable(Pageable pageable){
		return fichaRepository.findAll(pageable);
	}
	
	public Optional<Ficha> getFichaById(Integer IDFicha) {
		return fichaRepository.findById(IDFicha);
	}

	public Ficha addFicha(Ficha ficha){
		ficha.setSaldo(ficha.getTotal() - ficha.getSenia());
		return fichaRepository.save(ficha);
	}
	
	public void deleteFicha(Integer IDFicha) {
		fichaRepository.deleteById(IDFicha);
	}
	
	public Ficha updateFicha(Ficha ficha) {
		return fichaRepository.save(ficha);
	}

    @Transactional
	public void updateSaldoCero(Integer id) throws FichaException {
		Optional<Ficha> ficha = fichaRepository.findById(id);

		if(ficha.isEmpty()) {
			throw new FichaException("Ficha no existe!", HttpStatus.NOT_FOUND);
		}
		fichaRepository.updateSaldoCero(id);
	}

	public List<Ficha> getAllFichasByFiltro(LocalDate fechaDesde, LocalDate fechaHasta, Double totalDesde, Double totalHasta, Boolean estaSeniado, Boolean estaPagado){
		List<Ficha> listaFichas, listaFiltada = new ArrayList<Ficha>();
		listaFichas = fichaRepository.getAllFichasByFiltro(fechaDesde, fechaHasta, totalDesde, totalHasta);

		if(!estaSeniado)
			listaFiltada = listaFichas.stream().filter((f) ->  f.getSenia()==0).collect(Collectors.toList());

//		if(estaPagado)
//			listaFiltada = listaFichas.stream().filter((f) ->  f.getSaldo()==0).collect(Collectors.toList());

		return listaFiltada;
	}

	public List<Ficha> getAllFichasByidCliente(Integer idCliente){
		return fichaRepository.findAllByidCliente(idCliente);
	}
}
