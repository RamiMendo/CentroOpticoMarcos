package com.opticamarcos.controller.rest;

import java.util.List;

import com.opticamarcos.exceptions.CustomException;
import com.opticamarcos.model.dto.ClienteDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.opticamarcos.model.entity.Cliente;
import com.opticamarcos.service.ClienteService;

import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("/cliente")
public class ClienteController {

	@Autowired
	private ClienteService clienteService;
	
	@GetMapping
	@Operation(summary="Devuelve un listado de Clientes", description="No tiene parámetros de entrada, no está paginada y devuelve todos los clientes de bd", tags= {"Clientes"})
	private @ResponseBody List<Cliente> getListaClientes() {
		return clienteService.getListaCliente();
	}

	@GetMapping(path = "/paginado")
	@Operation(summary="Devuelve un listado de Clientes", description="No tiene parámetros de entrada, está paginada y devuelve todos los clientes de bd", tags= {"Clientes"})
	private ResponseEntity<Page<Cliente>> getListaClientesPaginado(@RequestParam Integer page, @RequestParam Integer size) {
		Pageable pagina = PageRequest.of(page, size);
		Page<Cliente> clientePage = clienteService.getListaClientePaginado(pagina);
		return new ResponseEntity<>(clientePage, HttpStatus.OK);
	}

	@GetMapping(value="/findById")
	@Operation(summary="Devuelve un cliente apartir de ID ingresado", description="Tiene parámetros de entrada, devuelve el cliente a partir del ID ingresado", tags= {"Clientes"})
	private @ResponseBody Cliente getClienteByID(@RequestParam Integer id) throws CustomException {
		return clienteService.findById(id);
	}

	@GetMapping(path = "/findSinFicha")
	@Operation(summary = "Busca todos los cliente que no tienen ficha, y esta paginado", description = "Se pasan por parametro numero de pagina y tamaño", tags = {"Clientes"})
	private ResponseEntity<Page<Cliente>> findAllNoTieneFichas(@RequestParam Integer page, @RequestParam Integer size) {
		Pageable pageable = PageRequest.of(page, size);
		Page<Cliente> clientePage = clienteService.findAllNoTieneFichas(pageable);

		return new ResponseEntity<>(clientePage, HttpStatus.OK);
	}
	
	@PostMapping(consumes="application/json")
	@Operation(summary="Agrega un cliente", description="Debe enviar el objeto cliente a ingresar", tags= {"Clientes"})
	private @ResponseBody Cliente addCliente(@RequestBody ClienteDTO clienteDTO) {
		return clienteService.addCliente(clienteDTO);
	}
	
	@DeleteMapping(produces="application/json")
	@Operation(summary="Borra un cliente de la Base de Datos", description="Debe enviar el id del cliente a borrar", tags= {"Clientes"})
	private ResponseEntity<Void> deleteCliente(@RequestParam Integer id) throws CustomException {
		clienteService.deleteCliente(id);
		return ResponseEntity.noContent().build();
	}
	
	@PutMapping(consumes="application/json")
	@Operation(summary="Permite modificar un cliente", description="Debe enviar el objeto a modificar, no se puede modificar el id", tags= {"Clientes"})
	public @ResponseBody Cliente updateCliente(@RequestBody Cliente cliente) {
		return clienteService.updateCliente(cliente);
	}
	
	
}
