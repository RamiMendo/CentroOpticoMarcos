package com.opticamarcosweb.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.opticamarcosweb.model.Cristal;
import com.opticamarcosweb.repository.CristalRepository;

@Service
public class CristalService {

	@Autowired
	private CristalRepository cristalRepository;

	public List<Cristal> getAllCristales(){
		return cristalRepository.findAll();
	}
	
	public Optional<Cristal> getCristalById(Integer IDCristal) {
		return cristalRepository.findById(IDCristal);
	}
	
	public Cristal addCristal(Cristal cristal) {
		return cristalRepository.save(cristal);
	}
	
	public void deleteCristal(Integer IDCristal) {
		cristalRepository.deleteById(IDCristal);
	}
	
	public Cristal updateCristal(Cristal cristal) {
		return cristalRepository.save(cristal);
	}
}
