package com.opticamarcos.service;

import java.util.List;
import java.util.Optional;

import com.opticamarcos.exceptions.CustomException;
import com.opticamarcos.mapper.ClienteMapper;
import com.opticamarcos.model.dto.ClienteDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.opticamarcos.model.entity.Cliente;
import com.opticamarcos.repository.ClienteRepository;

@Service
public class ClienteService {
	
	@Autowired
	private ClienteRepository clienteRepository;

	@Autowired
	private ClienteMapper clienteMapper;
	
	public List<Cliente> getListaCliente(){	
		return clienteRepository.findAll();
	}

	public Page<Cliente> getListaClientePaginado(Pageable pageable){
		return clienteRepository.findAll(pageable);
	}
	
	public Cliente findById(Integer idCliente) throws CustomException {
		return clienteRepository.findById(idCliente).orElse(null);
	}

	public Page<Cliente> findAllNoTieneFichas(Pageable pageable){
		return clienteRepository.findAllNoTieneFichas(pageable);
	}

	public Cliente addCliente(ClienteDTO clienteDTO) {
		Cliente cliente = clienteMapper.dtoToEntity(clienteDTO);
		return clienteRepository.save(cliente);
	}
	
	public void deleteCliente(Integer idCliente) throws CustomException {
		clienteRepository.delete(findById(idCliente));
	}
	
	public Cliente updateCliente(Cliente cliente) {
		return clienteRepository.save(cliente);
	}

	public Double getSaldoCliente(Integer idCliente) throws CustomException {
		return clienteRepository.getSaldoCliente(idCliente);
	}
}
