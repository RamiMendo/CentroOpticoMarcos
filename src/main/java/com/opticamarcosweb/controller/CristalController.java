package com.opticamarcosweb.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.opticamarcosweb.model.Cristal;
import com.opticamarcosweb.service.CristalService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping("/cristal")
public class CristalController {

	@Autowired
	private CristalService cristalService;
	
	@GetMapping(value="/")
	@Operation(summary="Devuelve un listado de Cristales", description="No tiene parámetros de entrada, no está paginada y devuelve todos los cristales de bd", tags= {"Cristales"})
	@ApiResponses(value= {
			@ApiResponse(responseCode="200", description = "Se encontraron cristales en bd!"),
			@ApiResponse(responseCode="404", description = "No se encotraron cristales en BD!")
			
	})
	private @ResponseBody List<Cristal> getListaCristales() {
		return cristalService.getAllCristales();
	}
	
	@GetMapping(value="/{id}")
	@Operation(summary="Devuelve un cristal apartir de ID ingresado", description="Tiene parámetros de entrada, está paginada y devuelve el cristal a partir del ID ingresado", tags= {"Cristales"})
	@ApiResponses(value= {
			@ApiResponse(responseCode="200", description = "Se encontraron cristales en bd!"),
			@ApiResponse(responseCode="404", description = "No se encotraron cristales en BD!")
			
	})
	private @ResponseBody Optional<Cristal> getCristalById(@PathVariable("id") Integer id) {
		return  cristalService.getCristalById(id);
	}
	
	@PostMapping(value="/",produces="application/json")
	@Operation(summary="Agrega un cristal", description="Debe enviar el objeto cristal a ingresar", tags= {"Cristales"})
	private @ResponseBody Cristal addCristal(Cristal cristal) {	
		return cristalService.addCristal(cristal);
	}
	
	@DeleteMapping(path="/{id}", produces="application/json")
	@Operation(summary="Borra un cristal de la Base de Datos", description="Debe enviar el id del cristal a borrar", tags= {"Cristales"})
	private @ResponseBody void deleteCristal(@PathVariable("id") Integer id) {
		cristalService.deleteCristal(id);
	}
	
	@PutMapping(path="/", produces="application/json")
	@Operation(summary="Permite modificar un cristal", description="Debe enviar el objeto a modificar, no se puede modificar el id", tags= {"Cristales"})
	private @ResponseBody Cristal updateCristal(Cristal cristal) {
		return cristalService.updateCristal(cristal);
	}
	
	
}
