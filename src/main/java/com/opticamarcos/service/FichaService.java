package com.opticamarcos.service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDate;
import java.util.*;

import com.opticamarcos.exceptions.ObjectNotFoundException;
import com.opticamarcos.model.entity.Lente;
import com.opticamarcos.model.entity.Medida;
import com.opticamarcos.repository.ClienteRepository;
import com.opticamarcos.repository.LenteRepository;
import com.opticamarcos.repository.MedidaRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.opticamarcos.model.entity.Ficha;
import com.opticamarcos.repository.FichaRepository;

@Service
public class FichaService {

	@Autowired
	private FichaRepository fichaRepository;

	@Autowired
	private ClienteRepository clienteRepository;

	@Autowired
	private LenteRepository lenteRepository;

	@Autowired
	private MedidaRepository medidaRepository;
	
	public List<Ficha> getAllFichas(){
		return fichaRepository.findAll();
	}

	public Page<Ficha> getAllFichasPageable(Pageable pageable, LocalDate fechaDesde, LocalDate fechaHasta){
		return fichaRepository.findAllByFechas(pageable, fechaDesde, fechaHasta);
	}
	
	public Ficha findById(Integer idFicha) throws ObjectNotFoundException {
		Optional<Ficha> ficha = fichaRepository.findById(idFicha);

		if (ficha.isEmpty())
			throw new ObjectNotFoundException("NO SE ENCONTRO LA FICHA " + idFicha + "!!", HttpStatus.NOT_FOUND);

		return ficha.get();
	}

	@Transactional
	public File getFichaFile(Integer idFicha, Boolean sobreescribir) throws IOException, ObjectNotFoundException {
		Ficha ficha = findById(idFicha);

		if (!ficha.getEstaImpreso()||sobreescribir){
			if(!sobreescribir)
				fichaRepository.updateImpresion(idFicha);

			ficha.escribirPDF();
		}

		File archivoPDF = new File("C:\\RAMIRO\\Programacion\\Java\\Proyectos\\CentroOpticoMarcosWeb\\src\\main\\resources\\fichas\\Ficha-" + idFicha + ".pdf");

		if (!archivoPDF.exists())
			throw new FileNotFoundException("NO SE ENCONTRO EL ARCHIVO PARA LA FICHA " + idFicha);

		return archivoPDF;
	}

	public Ficha addFicha(Ficha ficha) {
		ficha.setSaldo(ficha.getTotal() - ficha.getSenia());
		return fichaRepository.save(ficha);
	}
	
	public void deleteFicha(Integer idFicha) throws ObjectNotFoundException {
		Ficha ficha = findById(idFicha);
		fichaRepository.delete(ficha);
	}
	
	public void updateFicha(@NotNull Ficha ficha) {
		Integer nuevoTotal = 0;

		for (Lente lente: ficha.getLentes()) {
			for (Medida medida: lente.getMedidasLentes()){
				nuevoTotal = nuevoTotal + medida.getPrecio();
				medidaRepository.save(medida);
			}
			nuevoTotal = nuevoTotal + lente.getArmazon().getPrecio();
			lenteRepository.save(lente);
		}

		ficha.setTotal(nuevoTotal);
		ficha.setSaldo(nuevoTotal - ficha.getSenia());
		fichaRepository.save(ficha);
	}

	@Transactional
	public void updateCliente(Integer idFicha, Integer idCliente) throws ObjectNotFoundException {
		findById(idFicha);

		if(clienteRepository.findById(idCliente).isPresent())
			if (fichaRepository.tieneFicha(idCliente) == 0)
				fichaRepository.updateCliente(idFicha, idCliente);

	}

    @Transactional
	public void updateSaldoCero(Integer id) {
		fichaRepository.updateSaldoCero(id);
	}

	public List<Ficha> getFichasByFiltro(LocalDate fechaDesde, LocalDate fechaHasta, Double totalDesde, Double totalHasta, Integer estaSeniado, Integer estaPago){
        return fichaRepository.getFichasByFiltro(fechaDesde, fechaHasta, totalDesde, totalHasta, estaSeniado, estaPago);
	}

	public List<Ficha> getAllFichasByidCliente(Integer idCliente){
		return fichaRepository.findAllByidCliente(idCliente);
	}

	public Map<Integer, Integer> getTotalPorMes(Integer mes, Integer anio, Boolean esPesos){
		List<Ficha> fichas = fichaRepository.findAll();
		Map<Integer, Integer> totalPorMes = new HashMap<>();
		Integer total = 0;

		fichas = fichas.stream()
				.filter(f -> (f.getFecha().getYear() == anio))
				.sorted(Comparator.comparing(Ficha::getFecha))
				.toList();

		for (int i = 1; i <= 12; i++){
			if (mes != 0) i = mes;

			for (Ficha ficha: fichas){
				if (ficha.getFecha().getMonthValue() == i)
					if (esPesos){
						total += ficha.getTotal();
					}else{total += 1;}
			}
			totalPorMes.put(i, total);
			total = 0;

			if (i == mes) break;
		}
		return totalPorMes;
	}

	public Map<Integer, Integer> getArmazonMasVendido(Integer mes, Integer anio){
		Map<Integer, Integer> totalArmazon = new HashMap<>();
		List<Ficha> fichas = fichaRepository.findAll();

		fichas = fichas.stream()
				.filter(f -> (f.getFecha().getYear() == anio))
				.sorted(Comparator.comparing(Ficha::getFecha))
				.toList();

		for (int i = 1; i <= 12; i++){
			if (mes != 0) i = mes;

			for (Ficha ficha: fichas){
				if (ficha.getFecha().getMonthValue() == i)
					for (Lente lente : ficha.getLentes()) {
						if (lente.getArmazon() == null) continue;
						if (!totalArmazon.containsKey(lente.getArmazon().getIdArmazon())) {
							totalArmazon.put(lente.getArmazon().getIdArmazon(), 1);
						} else {
							Integer valorAnterior = totalArmazon.get(lente.getArmazon().getIdArmazon());
							totalArmazon.replace(lente.getArmazon().getIdArmazon(), valorAnterior, valorAnterior + 1);
						}
					}

			}

			if (i == mes) break;
		}

		return totalArmazon;
	}
}
