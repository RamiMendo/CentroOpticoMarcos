package com.opticamarcos.service;

import java.util.List;
import java.util.Optional;

import com.opticamarcos.exceptions.CustomException;
import com.opticamarcos.mapper.CristalMapper;
import com.opticamarcos.model.dto.CristalDTO;
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

	@Autowired
	private CristalMapper cristalMapper;

	public List<Cristal> getAllCristales(){
		return cristalRepository.findAll();
	}

	public Page<Cristal> getAllCristalesPaginado(Pageable pageable){
		return cristalRepository.findAll(pageable);
	}
	
	public Cristal findById(Integer idCristal) throws CustomException {
		return cristalRepository.findById(idCristal).orElse(null);
	}

	public Cristal findByNombre(String nombre) throws CustomException{
		return cristalRepository.findByNombre(nombre).orElse(null);
	}
	
	public Cristal addCristal(CristalDTO cristalDTO) {
		Cristal cristal = cristalMapper.dtoToEntity(cristalDTO);
		return cristalRepository.save(cristal);
	}
	
	public void deleteCristal(Integer idCristal) throws CustomException {
		cristalRepository.delete(findById(idCristal));
	}
	
	public Cristal updateCristal(Cristal cristal) {
		return cristalRepository.save(cristal);
	}
}
