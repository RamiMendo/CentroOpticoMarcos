package com.opticamarcos.controller.view;

import com.opticamarcos.exceptions.CustomException;
import com.opticamarcos.model.dto.ArmazonDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.opticamarcos.service.ArmazonService;

import com.opticamarcos.model.entity.Armazon;

import java.util.List;
import java.util.ArrayList;

@Controller
@RequestMapping("/armazon")
public class ArmazonController {
	
	@Autowired
	private ArmazonService armazonService;

	@GetMapping(path = "/listado")
	public String getListaArmazones(Model model, @RequestParam Integer page) {
		Pageable pagina = PageRequest.of(page, 10);
		Page<Armazon> listaArmazones = armazonService.getAllArmazonesPaginado(pagina);
		
		model.addAttribute("listaArmazones", listaArmazones);
		
		return "armazon/listado/index";
	}

	@GetMapping(path="/findById")
	private String getArmazonById(Model model, @RequestParam Integer id) throws CustomException {
		Armazon armazon = armazonService.findById(id);
		model.addAttribute("armazon", armazon);

		return "armazon/formulario/index";
	}

	@GetMapping(path = "/findByPrecio", produces = "application/json")
	private String findArmazonesByPrecio(Model model, Integer currentPage, Double regularDesde, Double regularHasta) {
		Pageable pagina = PageRequest.of(currentPage, 10);
		List<Armazon> pageArmazon = armazonService.findArmazonesByPrecio(currentPage, regularDesde, regularHasta);

		model.addAttribute("armazones", pageArmazon);

		return "armazon/listado/index";
	}

	@PostMapping(path = "/save")
	private String addArmazon(@RequestBody ArmazonDTO armazonDTO) {
		armazonService.addArmazon(armazonDTO);
		return "redirect:/armazon/findById";
	}

	@DeleteMapping(path = "/delete")
	private String deleteArmazon(@RequestParam Integer id) throws CustomException {
		armazonService.deleteArmazon(id);
		return "redirect:/armazon/listado";
	}

	@PutMapping(path="/update")
	public String updateArmazon(Armazon armazon) {
		armazonService.updateArmazon(armazon);
		return "redirect:/armazon/findById";
	}

}
