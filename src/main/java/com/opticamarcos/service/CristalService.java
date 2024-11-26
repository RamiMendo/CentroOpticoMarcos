package com.opticamarcos.service;

import java.util.List;
import java.util.Optional;

import com.opticamarcos.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.opticamarcos.model.entity.Cristal;
import com.opticamarcos.repository.CristalRepository;

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
	
	public Cristal findById(Integer idCristal) throws ObjectNotFoundException {
		Optional<Cristal> cristal = cristalRepository.findById(idCristal);

		if(cristal.isEmpty())
			throw new ObjectNotFoundException("NO SE ENCONTRO EL CRISTAL CON ID: " + idCristal, HttpStatus.NOT_FOUND);

		return cristal.get();
	}
	
	public Cristal addCristal(Cristal cristal) {
		return cristalRepository.save(cristal);
	}
	
	public void deleteCristal(Integer idCristal) throws ObjectNotFoundException {
		cristalRepository.delete(findById(idCristal));
	}
	
	public Cristal updateCristal(Cristal cristal) {
		return cristalRepository.save(cristal);
	}
}
