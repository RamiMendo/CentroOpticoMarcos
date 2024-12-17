package com.opticamarcos.controller.view;

import com.opticamarcos.exceptions.CustomException;
import com.opticamarcos.model.entity.Cristal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.opticamarcos.service.CristalService;

@Controller
@RequestMapping("/cristal")
public class CristalController {

	@Autowired
	private CristalService cristalService;

	@GetMapping(path = "/paginado")
	private String getListaCristales(Model model, @RequestParam Integer page) {
		Pageable paginado = PageRequest.of(page, 10);
		Page<Cristal> listaCristales = cristalService.getAllCristalesPaginado(paginado);

		model.addAttribute("listaCristales", listaCristales);

		return "";
	}

	@GetMapping(path = "/findById")
	private String getCristalById(Model model, @RequestParam Integer id) throws CustomException {
		Cristal cristal = cristalService.findById(id);

		model.addAttribute("cristal", cristal);

		return "";
	}

	@PostMapping(path = "/save")
	private String addCristal(Model model, @RequestBody Cristal cristal) {
		Cristal cristalSaved = cristalService.addCristal(cristal);
		model.addAttribute("cristal", cristalSaved);

		return "";
	}

	@DeleteMapping(path = "/delete")
	private String deleteCristal(Model model, @RequestParam Integer id) throws CustomException {
		cristalService.deleteCristal(id);

		return "";
	}

	@PutMapping(path = "/update")
	private String updateCristal(Model model, @RequestBody Cristal cristal) {
		Cristal cristalUp = cristalService.updateCristal(cristal);
		model.addAttribute("cristal", cristalUp);

		return "";
	}

}
