package com.opticamarcos.controller;

import com.opticamarcos.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.opticamarcos.model.entity.Armazon;
import com.opticamarcos.service.ArmazonService;

import io.swagger.v3.oas.annotations.Operation;
//import io.swagger.v3.oas.annotations.responses.ApiResponse;
//import io.swagger.v3.oas.annotations.responses.ApiResponses;

import java.util.List;

@RestController
@RequestMapping("/armazon")
public class ArmazonController {
	
	@Autowired
	private ArmazonService armazonService;

	@GetMapping
	@Operation(summary="Devuelve un listado de Armazones", description="No tiene parámetros de entrada, no está paginada y devuelve todos los armazones de bd", tags= {"Armazones"})
	private @ResponseBody List<Armazon> getListaArmazones() {
		return armazonService.getAllArmazones();
	}

	@GetMapping(path = "/paginado")
	@Operation(summary = "Devuelve un listado de Armazones paginado", description = "Tiene parametros de entrada, esta paginada y devuelve las armazones paginadas", tags = {"Armazones"})
	private ResponseEntity<Page<Armazon>> getPaginadoArmazon(@RequestParam Integer page, @RequestParam Integer size) {
		Pageable pagina = PageRequest.of(page, size);
		Page<Armazon> armazonPage = armazonService.getAllArmazonesPaginado(pagina);

		return new ResponseEntity<>(armazonPage, HttpStatus.OK);
	}

	@GetMapping(value="/findById")
	@Operation(summary="Devuelve un armazon apartir de ID ingresado", description="Tiene parámetros de entrada y devuelve el armazon a partir del ID ingresado", tags= {"Armazones"})
	private @ResponseBody Armazon getArmazonById(@RequestParam Integer id) throws ObjectNotFoundException {
		return armazonService.findById(id);
	}

	@GetMapping(path = "/findByPrecio", produces = "application/json")
	@Operation(summary = "Busca todos los armazones por precio", description = "Se pasan por parametro los precios minimos y maximos, y el numero de pagina", tags = {"Armazones"})
	private @ResponseBody List<Armazon> findArmazonesByPrecio(Integer currentPage, Double regularDesde, Double regularHasta) {
		return armazonService.findArmazonesByPrecio(currentPage, regularDesde, regularHasta);
	}
	
	@PostMapping(consumes="application/json")
	@Operation(summary="Agrega un armazon", description="Debe enviar el objeto armazon a ingresar", tags= {"Armazones"})
	private @ResponseBody Armazon addArmazon(@RequestBody Armazon armazon) {
		return armazonService.addArmazon(armazon);
	}
	
	@DeleteMapping(produces="application/json")
	@Operation(summary="Borra un armazon de la Base de Datos", description="Debe enviar el id del armazon a borrar", tags= {"Armazones"})
	private ResponseEntity<Void> deleteArmazon(@RequestParam Integer id) throws ObjectNotFoundException {
		armazonService.deleteArmazon(id);
		return ResponseEntity.noContent().build();
	}
	
	@PutMapping(path="/", produces="application/json")
	@Operation(summary="Permite modificar un armazon", description="Debe enviar el objeto a modificar, no se puede modificar el id", tags= {"Armazones"})
	public @ResponseBody Armazon updateArmazon(Armazon armazon) {
		return armazonService.updateArmazon(armazon);
	}
	
}
