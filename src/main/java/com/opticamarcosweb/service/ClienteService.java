package com.opticamarcosweb.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.opticamarcosweb.model.Cliente;
import com.opticamarcosweb.repository.ClienteRepository;

@Service
public class ClienteService {
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	public List<Cliente> getListaCliente(){	
		return clienteRepository.findAll();
	}
	
	public Optional<Cliente> getClienteById(Integer id){
		return clienteRepository.findById(id);
	}

	public Cliente addCliente(Cliente cliente) {
		return clienteRepository.save(cliente);
	}
	
	public void deleteCliente(Integer id) {
		clienteRepository.deleteById(id);
	}
	
	public Cliente updateCliente(Cliente cliente) {
		return clienteRepository.save(cliente);
	}
}
