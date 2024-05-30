package com.opticamarcosweb.service;

import java.util.List;
import java.util.Optional;

import com.opticamarcosweb.exceptions.EntidadException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
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

	public Page<Cristal> getAllCristalesPaginado(Pageable pageable){
		return cristalRepository.findAll(pageable);
	}
	
	public Optional<Cristal> getCristalById(Integer IDCristal) {
		return cristalRepository.findById(IDCristal);
	}
	
	public Cristal addCristal(Cristal cristal) {
		return cristalRepository.save(cristal);
	}
	
	public void deleteCristal(Integer IDCristal) throws EntidadException {
		Optional<Cristal> cristal = cristalRepository.findById(IDCristal);

		if(cristal.isEmpty()){
			throw new EntidadException("Cristal No Encontrado!", HttpStatus.NOT_FOUND);
		}else{
			cristalRepository.delete(cristal.get());
		}

	}
	
	public Cristal updateCristal(Cristal cristal) {
		return cristalRepository.save(cristal);
	}
}
