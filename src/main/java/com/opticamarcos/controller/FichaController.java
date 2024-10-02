package com.opticamarcos.controller;

import com.opticamarcos.exceptions.ObjectNotFoundException;
import com.opticamarcos.model.Ficha;
import com.opticamarcos.service.FichaService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.FileInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/ficha")
public class FichaController {

    @Autowired
    private FichaService fichaService;

    @GetMapping
    @Operation(summary="Devuelve un listado de Fichas",
            description="No tiene parámetros de entrada, no está paginada y devuelve todos las fichas de bd",
            tags= {"Fichas"})
    private @ResponseBody List<Ficha> getListaFichas() {
        return fichaService.getAllFichas();
    }

    @GetMapping(path = "/paginado")
    @Operation(summary = "Devuelve un listado de Fichas paginado",
            description = "Tiene parametros de entrada, esta paginada y devuelve las fichas paginadas",
            tags = {"Fichas"})
    private ResponseEntity<Page<Ficha>> getListaFichasPageable(@RequestParam Integer page, @RequestParam Integer size, @RequestParam @DateTimeFormat(pattern = "dd-MM-yyyy") LocalDate fechaDesde, @RequestParam @DateTimeFormat(pattern = "dd-MM-yyyy") LocalDate fechaHasta) {
        Pageable paginable = PageRequest.of(page, size);
        Page<Ficha> pagina = fichaService.getAllFichasPageable(paginable, fechaDesde, fechaHasta);

        return new ResponseEntity<>(pagina, HttpStatus.OK);
    }

    @GetMapping(value="/findById")
    @Operation(summary="Devuelve una ficha apartir de ID ingresado",
            description="Tiene parámetros de entrada y devuelve la ficha a partir del ID ingresado",
            tags= {"Fichas"})
    private @ResponseBody Ficha getFichaById(@RequestParam Integer id) throws ObjectNotFoundException {
        return fichaService.findById(id);
    }

    @GetMapping(path = "/filter")
    @Operation(summary = "Devuelve una lista de fichas a partir de un filtro aplicado",
            description = "Debe enviar fechas(desde-hasta), lo mismo con el total, si fue señado y pagado ",
            tags = {"Fichas"})
    private @ResponseBody List<Ficha> getAllFichasByFilter(@RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate fechaDesde, @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate fechaHasta, @RequestParam Double totalDesde, @RequestParam Double totalHasta, @RequestParam Integer estaSeniado, @RequestParam Integer estaPago){
        return fichaService.getFichasByFiltro(fechaDesde, fechaHasta, totalDesde, totalHasta, estaSeniado, estaPago);
    }

    @GetMapping(path = "/findByCliente", produces = "application/json")
    @Operation(summary = "Busca todas las fichas a partir del id del cliente",
            description = "Debe enviar id del cliente",
            tags = {"Fichas"})
    private @ResponseBody List<Ficha> getAllFichasByCliente(@RequestParam Integer idCliente) {
        return fichaService.getAllFichasByidCliente(idCliente);
    }

    @GetMapping(path = "/download")
    @Operation(summary = "Busca o crea el archivo PDF de la ficha.",
            description = "Se pasa por parametros el identificador de la ficha y si se desea sobreescribir.",
            tags = {"Fichas"}
    )
    private ResponseEntity<?> getFichaFile(@RequestParam Integer id, @RequestParam Boolean sobreescribir) throws ObjectNotFoundException, IOException {

        File fichaArchivo = fichaService.getFichaFile(id, sobreescribir);

        byte[] fichaBytes = null;
        try (FileInputStream fis = new FileInputStream(fichaArchivo)) {
            fichaBytes = fis.readAllBytes();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_PDF)
                .body(fichaBytes);
    }

    @GetMapping(path = "/estadisticas/totalPorMes")
    @Operation(summary = "Obtiene los totales vendidos por mes",
            description = "Se pasa por parametro mes, anio, y si es pesos o por cantidad",
            tags = {"Fichas"}
    )
    private @ResponseBody Map<Integer, Integer> findTotalPorMes(@RequestParam Integer mes, @RequestParam Integer anio, @RequestParam Boolean esPesos){
        return fichaService.getTotalPorMes(mes, anio, esPesos);
    }

    @GetMapping(path = "/estadisiticas/armazonMasVendido")
    @Operation(summary = "Obtiene los totales vendidos por armazon",
            description = "Se pasa por parametro mes y anio",
            tags = {"Fichas"}
    )
    private @ResponseBody Map<Integer, Integer> findArmazonMasVendido(@RequestParam Integer mes, @RequestParam Integer anio){
        return fichaService.getArmazonMasVendido(mes, anio);
    }

    @PostMapping(consumes="application/json")
    @Operation(summary="Agrega una ficha",
            description="Debe enviar el objeto ficha a ingresar",
            tags= {"Fichas"})
    private @ResponseBody Ficha addFicha(@RequestBody Ficha ficha) {
        return fichaService.addFicha(ficha);
    }

    @DeleteMapping(produces="application/json")
    @Operation(summary="Borra una ficha de la Base de Datos",
            description="Debe enviar el id del ficha a borrar",
            tags= {"Fichas"})
    private ResponseEntity<Void> deleteFicha(@RequestParam Integer id) throws ObjectNotFoundException {
        fichaService.deleteFicha(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping(consumes="application/json")
    @Operation(summary="Permite modificar un ficha",
            description="Debe enviar el objeto a modificar, no se puede modificar el id",
            tags= {"Fichas"})
    private @ResponseBody void updateFicha(@RequestBody Ficha ficha) {
        fichaService.updateFicha(ficha);
    }

    @PatchMapping(path="/updatePago", produces = "application/json")
    @Operation(summary = "Modifica el saldo de la ficha",
            description = "Debe enviar el id de la ficha",
            tags = {"Fichas"})
    private ResponseEntity<Void> updateSaldoFicha(@RequestParam Integer id) {
        fichaService.updateSaldoCero(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping(path = "/updateCliente", produces = "application/json")
    @Operation(summary = "Modifica el cliente de una ficha en especifica",
            description = "Se pasan por parametro numero de ficha y de cliente",
            tags = {"Fichas"})
    private ResponseEntity<Void> updateCliente(Integer idFicha, Integer idCliente) throws ObjectNotFoundException {
        fichaService.updateCliente(idFicha, idCliente);
        return ResponseEntity.noContent().build();
    }
}
