package com.opticamarcosweb.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.opticamarcosweb.model.Cliente;
import com.opticamarcosweb.service.ClienteService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

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
		
	@GetMapping(path="/{id}")
	@Operation(summary="Devuelve un cliente apartir de ID ingresado", description="Tiene parámetros de entrada, devuelve el cliente a partir del ID ingresado", tags= {"Clientes"})
	private @ResponseBody Optional<Cliente> getClienteByID(@PathVariable("id") Integer id) {
		return clienteService.getClienteById(id);
	}
	
	@PostMapping(consumes="application/json")
	@Operation(summary="Agrega un cliente", description="Debe enviar el objeto cliente a ingresar", tags= {"Clientes"})
	private @ResponseBody Cliente addCliente(@RequestBody Cliente cliente) {
		return clienteService.addCliente(cliente);
	}
	
	@DeleteMapping(path="/{id}", produces="application/json")
	@Operation(summary="Borra un cliente de la Base de Datos", description="Debe enviar el id del cliente a borrar", tags= {"Clientes"})
	private @ResponseBody void deleteCliente(@PathVariable("id") Integer id) {
		clienteService.deleteCliente(id);
	}
	
	@PutMapping(consumes="application/json")
	@Operation(summary="Permite modificar un cliente", description="Debe enviar el objeto a modificar, no se puede modificar el id", tags= {"Clientes"})
	public @ResponseBody Cliente updateCliente(@RequestBody Cliente cliente) {
		return clienteService.updateCliente(cliente);
	}
	
	
}
