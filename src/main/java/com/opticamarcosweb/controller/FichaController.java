package com.opticamarcosweb.controller;

import com.opticamarcosweb.model.Ficha;
import com.opticamarcosweb.service.FichaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/ficha")
public class FichaController {

    @Autowired
    private FichaService fichaService;

    @GetMapping(value="/")
    @Operation(summary="Devuelve un listado de Fichas", description="No tiene parámetros de entrada, no está paginada y devuelve todos las fichas de bd", tags= {"Fichas"})
    @ApiResponses(value= {
            @ApiResponse(responseCode="200", description = "Se encontraron fichas en bd!"),
            @ApiResponse(responseCode="404", description = "No se encotraron fichas en BD!")

    })
    private @ResponseBody List<Ficha> getListaFichas() {
        return fichaService.getAllFichas();
    }

    @GetMapping(value="/{id}")
    @Operation(summary="Devuelve una ficha apartir de ID ingresado", description="Tiene parámetros de entrada, está paginada y devuelve la ficha a partir del ID ingresado", tags= {"Fichas"})
    @ApiResponses(value= {
            @ApiResponse(responseCode="200", description = "Se encontraron fichas en bd!"),
            @ApiResponse(responseCode="404", description = "No se encotraron fichas en BD!")

    })
    private @ResponseBody Optional<Ficha> getFichaById(@PathVariable("id") Integer id) {
        return  fichaService.getFichaById(id);
    }

    @PostMapping(value="/",consumes="application/json")
    @Operation(summary="Agrega una ficha", description="Debe enviar el objeto ficha a ingresar", tags= {"Fichas"})
    private @ResponseBody void addFicha(@RequestBody Ficha ficha) {
        fichaService.addFicha(ficha);
    }

    @DeleteMapping(path="/{id}", produces="application/json")
    @Operation(summary="Borra una ficha de la Base de Datos", description="Debe enviar el id del ficha a borrar", tags= {"Fichas"})
    private @ResponseBody void deleteFicha(@PathVariable("id") Integer id) {
        fichaService.deleteFicha(id);
    }

    @PutMapping(value="/", consumes="application/json")
    @Operation(summary="Permite modificar un ficha", description="Debe enviar el objeto a modificar, no se puede modificar el id", tags= {"Fichas"})
    private @ResponseBody void updateFicha(@RequestBody Ficha ficha) {
        fichaService.updateFicha(ficha);
    }

}
