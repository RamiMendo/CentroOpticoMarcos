package com.opticamarcosweb.controller;

import com.opticamarcosweb.model.Cliente;
import com.opticamarcosweb.model.Ficha;
import com.opticamarcosweb.service.FichaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
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
    @Operation(summary="Devuelve una ficha apartir de ID ingresado", description="Tiene parámetros de entrada y devuelve la ficha a partir del ID ingresado", tags= {"Fichas"})
    @ApiResponses(value= {
            @ApiResponse(responseCode="200", description = "Se encontraron fichas en bd!"),
            @ApiResponse(responseCode="404", description = "No se encotraron fichas en BD!")

    })
    private @ResponseBody Optional<Ficha> getFichaById(@PathVariable("id") Integer id) {
        return  fichaService.getFichaById(id);
    }

    @GetMapping(path = "/filter/{fechaDesde}/{fechaHasta}/{totalDesde}/{totalHasta}/{seniado}/{pagado}")
    @Operation(summary = "Devuelve una lista de fichas a partir de un filtro aplicado", description = "Debe enviar fechas(desde-hasta), lo mismo con el total, si fue señado y pagado ", tags = {"Fichas"})
    private @ResponseBody List<Ficha> getAllFichasByFilter(@PathVariable ("fechaDesde") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate fechaDesde, @PathVariable("fechaHasta") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate fechaHasta, @PathVariable("totalDesde") Double totalDesde, @PathVariable("totalHasta") Double totalHasta, @PathVariable("seniado") Boolean estaSeniado, @PathVariable("pagado") Boolean estaPagado){
        return fichaService.getAllFichasByFiltro(fechaDesde, fechaHasta, totalDesde, totalHasta, estaSeniado, estaPagado);
    }

    @GetMapping(path = "/cliente/", produces = "application/json")
    @Operation(summary = "Busca todas las fichas a partir del id del cliente", description = "Debe enviar id del cliente", tags = {"Fichas"})
    private @ResponseBody List<Ficha> getAllFichasByCliente(@RequestParam Integer idCliente) {
        return fichaService.getAllFichasByidCliente(idCliente);
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
    private @ResponseBody Ficha updateFicha(@RequestBody Ficha ficha) {
        return fichaService.updateFicha(ficha);
    }

    @PatchMapping(path="/pago/{id}", produces = "application/json")
    @Operation(summary = "Modifica el saldo de la ficha", description = "Debe enviar el id de la ficha", tags = {"Fichas"})
    private ResponseEntity<Void> updateSaldoFicha(@PathVariable("id") Integer id){
        fichaService.updateSaldoCero(id);
        return ResponseEntity.noContent().build();
    }

}
