package com.opticamarcosweb.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.opticamarcosweb.model.Lente;
import com.opticamarcosweb.repository.LenteRepository;

@Service
public class LenteService {
	
	@Autowired
	private LenteRepository lenteRepository;
	
	public List<Lente> getAllLentes(){
		return lenteRepository.findAll();
	}
	
	public Optional<Lente> getLenteById(Integer id){
		return lenteRepository.findById(id);
	}

	public Lente addLente(Lente lente) {
		return lenteRepository.save(lente);
	}
	
	public void deleteLente(Integer id) {
		lenteRepository.deleteById(id);
	}
	
	public Lente updateLente(Lente lente) {
		return lenteRepository.save(lente);
	}
}
