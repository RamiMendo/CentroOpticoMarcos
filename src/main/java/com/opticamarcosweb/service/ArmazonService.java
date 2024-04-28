package com.opticamarcosweb.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.opticamarcosweb.model.Armazon;
import com.opticamarcosweb.repository.ArmazonRepository;

@Service
public class ArmazonService {

	@Autowired
	private ArmazonRepository armazonRepository;
	
	public List<Armazon> getAllArmazones(){
		return armazonRepository.findAll();
	}
	
	public Optional<Armazon> getArmazonById(Integer id){
		return armazonRepository.findById(id);
	}

	public Armazon addArmazon(Armazon armazon) {
		return armazonRepository.save(armazon);
	}
	
	public void deleteArmazon(Integer id) {
		armazonRepository.deleteById(id);
	}
	
	public Armazon updateArmazon(Armazon armazon) {
		return armazonRepository.save(armazon);
	}
}
