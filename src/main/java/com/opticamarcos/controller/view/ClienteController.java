package com.opticamarcos.controller.view;

import com.opticamarcos.exceptions.CustomException;
import com.opticamarcos.model.dto.ClienteDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.opticamarcos.model.entity.Cliente;
import com.opticamarcos.service.ClienteService;

@Controller
@RequestMapping("/cliente")
public class ClienteController {
//
//	@Autowired
//	private ClienteService clienteService;
//
//	@GetMapping
//	private String getListaClientes(Model model, @RequestParam Integer current) {
//		Pageable pageable = PageRequest.of(current, 10);
//		Page<Cliente> listaClientes = clienteService.getListaCliente(pageable);
//
//		model.addAttribute("listaCliente", listaClientes);
//		return "cliente/listado/index";
//	}
//
//	@GetMapping("/formulario")
//	public String getCliente(Model model) {
//
//		Cliente cliente = new Cliente();
//		model.addAttribute("cliente", cliente);
//		return "cliente/formulario/index";
//	}
//
//	@GetMapping("/formulario/{id}")
//	private String getClienteByID(@RequestParam Integer id, Model model) throws CustomException {
//		Cliente cliente = clienteService.findById(id);
//
//		model.addAttribute("cliente", cliente);
//		return "cliente/";
//	}
//
//	@GetMapping(path = "/findSinFicha")
//	private String findAllNoTieneFichas(Model model, @RequestParam Integer page, @RequestParam Integer size) {
//		Pageable pageable = PageRequest.of(page, size);
//		Page<Cliente> listaClientes = clienteService.findAllNoTieneFichas(pageable);
//
//		model.addAttribute("listaClientes", listaClientes);
//
//		return "";
//	}
//
//	@PostMapping(path = "/save")
//	private String addCliente(@RequestBody ClienteDTO clienteDTO, Model model) {
//		Cliente cliente = clienteService.addCliente(clienteDTO);
//		model.addAttribute("cliente", cliente);
//
//		return "";
//	}
//
//	@DeleteMapping(produces="application/json")
//	private String deleteCliente(@RequestParam Integer id) throws CustomException {
//		clienteService.deleteCliente(id);
//		return "";
//	}
//
//	@PutMapping("/formulario/{id}")
//	public String updateCliente(@RequestBody Cliente cliente) {
//		Cliente clienteUp = clienteService.updateCliente(cliente);
//		return "";
//	}
//

}
