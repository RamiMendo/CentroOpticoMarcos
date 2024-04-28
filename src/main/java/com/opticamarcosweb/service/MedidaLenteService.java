package com.opticamarcosweb.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.opticamarcosweb.model.MedidaLente;
import com.opticamarcosweb.repository.MedidaLenteRepository;

@Service
public class MedidaLenteService {
	
	@Autowired
	private MedidaLenteRepository medidaLenteRepository;
	
	public List<MedidaLente> getAllMedidaLentes(){
		return medidaLenteRepository.findAll();
	}
	
	public Optional<MedidaLente> getMedidaLenteById(Integer id){
		return medidaLenteRepository.findById(id);
	}

	public MedidaLente addMedidaLente(MedidaLente lente) {
		return medidaLenteRepository.save(lente);
	}
	
	public void deleteMedidaLente(Integer id) {
		medidaLenteRepository.deleteById(id);
	}
	
	public MedidaLente updateMedidaLente(MedidaLente lente) {
		return medidaLenteRepository.save(lente);
	}

}
