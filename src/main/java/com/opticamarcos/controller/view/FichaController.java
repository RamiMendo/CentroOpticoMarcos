package com.opticamarcos.controller.view;

import com.opticamarcos.exceptions.CustomException;
import com.opticamarcos.model.dto.FichaDTO;
import com.opticamarcos.model.entity.Cliente;
import com.opticamarcos.model.entity.Lente;
import com.opticamarcos.model.entity.Medida;
import com.opticamarcos.service.ClienteService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;

import com.opticamarcos.model.entity.Ficha;
import com.opticamarcos.service.FichaService;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/ficha")
public class FichaController {
//
//    private static final Logger log = LoggerFactory.getLogger(FichaController.class);
//    @Autowired
//	private FichaService fichaService;
//
//    @Autowired
//    private ClienteService clienteService;
//
//	@GetMapping(path = "/listado")
//	public String getListaFichas(Model model, @RequestParam Integer current,
//                                 @RequestParam @DateTimeFormat(pattern = "dd-MM-yyyy") LocalDate fechaDesde,
//                                 @RequestParam @DateTimeFormat(pattern = "dd-MM-yyyy") LocalDate fechaHasta) {
//		Pageable paginable = PageRequest.of(current, 10);
//        Page<Ficha> listaFichas = fichaService.getAllFichasPageable(paginable, fechaDesde, fechaHasta);
//
//		model.addAttribute("listaFichas", listaFichas.getContent());
//
//		return "ficha/listado/index";
//	}
//
//	@GetMapping(path = "listado/filter")
//    private String getAllFichasByFilter(Model model, @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate fechaDesde, @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate fechaHasta, @RequestParam Double totalDesde, @RequestParam Double totalHasta, @RequestParam Integer estaSeniado, @RequestParam Integer estaPago){
//        List<Ficha> listaFichas = fichaService.getFichasByFiltro(fechaDesde, fechaHasta, totalDesde, totalHasta, estaSeniado, estaPago);
//
//        model.addAttribute("listaFichas", listaFichas);
//
//		return "ficha/listado/index";
//	}
//
//	@GetMapping("/new")
//	public String getFormulario(Model model) {
//		Ficha ficha = new Ficha();
//        List<Cliente> listaClientes = clienteService.listaClientes();
//
//		model.addAttribute("ficha", ficha);
//		model.addAttribute("clientes", listaClientes);
//		return "ficha/formulario/index";
//	}
//
//	@GetMapping("/findById")
//	public String getFichaById(Model model, @RequestParam Integer id) throws CustomException {
//		Ficha ficha = fichaService.findById(id);
//        model.addAttribute("ficha", ficha);
////		if (fichaById != null) {
////			model.addAttribute("ficha", fichaById);
////			return "ficha/formulario/index";
////		}else {
////			return "error/index";
////		}
//		return "ficha/formulario/index";
//	}
//
//	@GetMapping(path = "/findByCliente", produces = "application/json")
//    private String getAllFichasByCliente(Model model, @RequestParam Integer idCliente) {
//        List<Ficha> listaFichas = fichaService.getAllFichasByidCliente(idCliente);
//
//		model.addAttribute("listaFichas", listaFichas);
//
//		return "";
//    }
//
//    @GetMapping(path = "/download")
//    private String getFichaFile(@RequestParam Integer id, @RequestParam Boolean sobreescribir) throws CustomException, IOException {
//
//        File fichaArchivo = fichaService.getFichaFile(id, sobreescribir);
//
//        byte[] fichaBytes = null;
//        try (FileInputStream fis = new FileInputStream(fichaArchivo)) {
//            fichaBytes = fis.readAllBytes();
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        return "redirect:/ficha/findById?id=" + id;
//    }
//
//    @GetMapping(path = "/estadisticas/totalPorMes")
//    private String findTotalPorMes(Model model, @RequestParam Integer mes, @RequestParam Integer anio, @RequestParam Boolean esPesos){
//		Map<Integer, Integer> totalPorMes = fichaService.getTotalPorMes(mes, anio, esPesos);
//
//		model.addAttribute("totalPorMes", totalPorMes);
//
//		return "";
//    }
//
//    @GetMapping(path = "/estadisiticas/armazonMasVendido")
//    private String findArmazonMasVendido(Model model, @RequestParam Integer mes, @RequestParam Integer anio){
//		Map<Integer, Integer> mapaArmazones = fichaService.getArmazonMasVendido(mes, anio);
//
//		model.addAttribute("mapaArmazones", mapaArmazones);
//
//		return "";
//    }
//
//	@PostMapping(path = "/save")
//	public String addFicha(Model model, @RequestBody FichaDTO fichaDTO) {
//		Ficha ficha = fichaService.addFicha(fichaDTO);
//
//		model.addAttribute("ficha", ficha);
////		if (fichaService.addFicha(ficha) == true) {
////			return "redirect:/ficha";
////		}else {
////			return "error/index";
////		}
//		return "";
//	}
//
//    @GetMapping(path = "/delete")
//    private String deleteFicha(@RequestParam Integer id) throws CustomException {
//        fichaService.deleteFicha(id);
//        return "redirect:/ficha/listado?current=0&fechaDesde=" + LocalDate.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy")) + "&fechaHasta=" + LocalDate.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
//    }
//
//    @PutMapping(consumes="application/json")
//    private String updateFicha(Model model, @RequestBody Ficha ficha) {
//        fichaService.updateFicha(ficha);
//
//		return "";
//    }
//
//    @PatchMapping(path="/updatePago", produces = "application/json")
//    private String updateSaldoFicha(@RequestParam Integer id) {
//        fichaService.updateSaldoCero(id);
//        return "";
//    }
//
//    @PatchMapping(path = "/updateCliente", produces = "application/json")
//    private String updateCliente(Integer idFicha, Integer idCliente) throws CustomException {
//        fichaService.updateCliente(idFicha, idCliente);
//        return "";
//    }

}
