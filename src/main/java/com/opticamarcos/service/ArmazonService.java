package com.opticamarcos.service;

import java.util.List;
import java.util.Optional;

import com.opticamarcos.exceptions.CustomException;
import com.opticamarcos.mapper.ArmazonMapper;
import com.opticamarcos.model.dto.ArmazonDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.opticamarcos.model.entity.Armazon;
import com.opticamarcos.repository.ArmazonRepository;

@Service
public class ArmazonService {

	@Autowired
	private ArmazonRepository armazonRepository;

	@Autowired
	private ArmazonMapper armazonMapper;

	public List<Armazon> getAllArmazones(){
		return armazonRepository.findAll();
	}

	public Page<Armazon> getAllArmazonesPaginado(Pageable pageable){
		return armazonRepository.findAll(pageable);
	}
	
	public Armazon findById(Integer id) throws CustomException {
		return armazonRepository.findById(id).orElse(null);
	}

	public List<Armazon> findArmazonesByPrecio(Integer currentPage, Double regularDesde, Double regularHasta) {
		return armazonRepository.findArmazonesByPrecio(currentPage, regularDesde, regularHasta);
	}

	public Armazon addArmazon(ArmazonDTO armazonDTO) {
		Armazon armazon = armazonMapper.dtoToEntity(armazonDTO);
		return armazonRepository.save(armazon);
	}
	
	public void deleteArmazon(Integer id) throws CustomException {
		armazonRepository.delete(findById(id));
	}
	
	public Armazon updateArmazon(Armazon armazon) {
		return armazonRepository.save(armazon);
	}
}
