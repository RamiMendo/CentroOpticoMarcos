package com.opticamarcosweb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.opticamarcosweb.model.Ficha;
//import com.opticamarcosweb.service.ClienteService;
import com.opticamarcosweb.service.FichaService;
//import com.opticamarcosweb.service.LenteService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

import java.util.List;
import java.util.Optional;
import java.util.ArrayList;

@RestController
@RequestMapping("/ficha")
public class FichaController {
	
	@Autowired
	private FichaService fichaService;
		
	@GetMapping(value="/listado")
	@Operation(summary="Devuelve el listado de fichas opticas", description="No tiene parámetros de entrada, no está paginada y devuelve todas las fichas en base de datos", tags= {"Fichas"})
	@ApiResponses(value= {
			@ApiResponse(responseCode="200", description = "Se encontraron fichas en la base de datos!"),
			@ApiResponse(responseCode="404", description = "No se encotraron fichas en la base de datos!")
			
	})
	public @ResponseBody List<Ficha> getListaFichas() {
		List<Ficha> listaFichas = new ArrayList<Ficha>();
		listaFichas = fichaService.getAllFichas();
		
		return listaFichas;
	}
	
	@GetMapping(value="/{id}")
	@Operation(summary="Devuelve una ficha de la base de datos", description="debe enviar el id de la ficha a buscar", tags= {"Fichas"})
	@ApiResponses(value= {
			@ApiResponse(responseCode="200", description = "Se encontró la ficha buscada!"),
			@ApiResponse(responseCode="404", description = "No existe una ficha con el id ingresado")
			
	})
	public @ResponseBody Optional<Ficha> getFichaById(@PathVariable("id") Integer IDFicha) {
		return fichaService.getFichaById(IDFicha);
	}
	
	@PostMapping(value="/",produces="application/json")
	@Operation(summary="Agrega una ficha", description="Debe enviar el objeto ficha a ingresar", tags= {"Fichas"})
	public @ResponseBody Ficha addFicha(Ficha ficha) {
		return fichaService.addFicha(ficha);
	}
	
	@DeleteMapping(path="/{id}", produces="application/json")
	@Operation(summary="Borra un ficha de la Base de Datos", description="Debe enviar el id del ficha a borrar", tags= {"Fichas"})
	public @ResponseBody void deleteFicha(@PathVariable("id") Integer IDFicha) {
		fichaService.deleteFicha(IDFicha);
	}
	
	@PutMapping(path="/", produces="application/json")
	@Operation(summary="Permite modificar un ficha", description="Debe enviar el objeto a modificar, no se puede modificar el id", tags= {"Fichas"})
	public @ResponseBody Ficha updateFicha(Ficha ficha) {
		return fichaService.updateFicha(ficha);
	}

}
