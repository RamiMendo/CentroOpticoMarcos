package com.opticamarcos.controller;

import java.util.List;

import com.opticamarcos.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.opticamarcos.model.entity.Cristal;
import com.opticamarcos.service.CristalService;

import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("/cristal")
public class CristalController {

	@Autowired
	private CristalService cristalService;
	
	@GetMapping
	@Operation(summary="Devuelve un listado de Cristales", description="No tiene parámetros de entrada, no está paginada y devuelve todos los cristales de bd", tags= {"Cristales"})
	private @ResponseBody List<Cristal> getListaCristales() {
		return cristalService.getAllCristales();
	}

	@GetMapping(path = "/paginado")
	@Operation(summary="Devuelve un listado de Cristales", description="No tiene parámetros de entrada, está paginada y devuelve todos los cristales de bd", tags= {"Cristales"})
	private ResponseEntity<Page<Cristal>> getListaCristales(@RequestParam Integer page, @RequestParam Integer size) {
		Pageable paginado = PageRequest.of(page, size);
		Page<Cristal> cristalPage = cristalService.getAllCristalesPaginado(paginado);
		return new ResponseEntity<>(cristalPage, HttpStatus.OK);
	}

	@GetMapping(value="/findById")
	@Operation(summary="Devuelve un cristal apartir de ID ingresado", description="Tiene parámetros de entrada, está paginada y devuelve el cristal a partir del ID ingresado", tags= {"Cristales"})
	private @ResponseBody Cristal getCristalById(@RequestParam Integer id) throws ObjectNotFoundException {
		return cristalService.findById(id);
	}
	
	@PostMapping(produces="application/json")
	@Operation(summary="Agrega un cristal", description="Debe enviar el objeto cristal a ingresar", tags= {"Cristales"})
	private @ResponseBody Cristal addCristal(@RequestBody Cristal cristal) {
		return cristalService.addCristal(cristal);
	}
	
	@DeleteMapping(produces="application/json")
	@Operation(summary="Borra un cristal de la Base de Datos", description="Debe enviar el id del cristal a borrar", tags= {"Cristales"})
	private ResponseEntity<Void> deleteCristal(@RequestParam Integer id) throws ObjectNotFoundException {
		cristalService.deleteCristal(id);
		return ResponseEntity.noContent().build();
	}
	
	@PutMapping(produces="application/json")
	@Operation(summary="Permite modificar un cristal", description="Debe enviar el objeto a modificar, no se puede modificar el id", tags= {"Cristales"})
	private @ResponseBody Cristal updateCristal(Cristal cristal) {
		return cristalService.updateCristal(cristal);
	}
	
	
}
