package com.opticamarcos.model;

import java.awt.*;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.Set;
import java.util.function.Predicate;

import com.opticamarcos.interfaces.Archivo;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.util.Matrix;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="fichas")
public class Ficha implements Archivo {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id_ficha")
	private Long idFicha;

	@Column(nullable = false)
	private LocalDate fecha;

	@Column(nullable = false)
	private Integer senia;

	@Column(nullable = false)
	private Integer saldo;

	@Column(nullable = false)
	private Integer total;

	@Column(nullable = false)
	private Boolean estaImpreso;
	
	@OneToOne
	@JoinColumn(name = "id_cliente")
	private Cliente cliente;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinTable(
			name = "fichas_lentes",
			joinColumns = @JoinColumn(name = "id_ficha"),
			inverseJoinColumns = @JoinColumn(name = "id_lente")
	)
	private Set<Lente> lentes = new HashSet<>();

	private static PDPageContentStream contentStream;

	private static final Integer[] vectorWidth = {80, 85, 45, 55, 70, 65, 65, 120};
	private static final String[] vectorTitulos = {"Esferico", "Cilindrico", "Eje", "D.I.", "Altura", "Mineral", "Organico", "Otros - Tratamientos"};

	@Override
	public void escribirPDF() throws IOException {
		Long idFicha = this.getIdFicha();
		LocalDate fecha = this.getFecha();
		Cliente cliente = this.getCliente();
		Set<Lente> lentes = this.getLentes();

		Integer[] totales = new Integer[3];
		totales[0] = this.getSenia();
		totales[1] = this.getSaldo();
		totales[2] = this.getTotal();

		PDDocument document = new PDDocument();
		PDPage page = new PDPage(PDRectangle.A4);
		page.setRotation(90);

		document.addPage(page);

		contentStream = new PDPageContentStream(document, page);
		contentStream.transform(new Matrix(0, 1, -1, 0, page.getMediaBox().getWidth(), 0));

		contentStream.setFont(PDType1Font.TIMES_BOLD_ITALIC, 18);
		escribeTexto( "Ficha Nº " + idFicha, 390, 565);

		contentStream.setFont(PDType1Font.HELVETICA, 12);
		escribeTexto("Fecha Ingreso: " + fecha.format(DateTimeFormatter.ofPattern("dd-MM-yyyy")), 40, 550);
		escribeTexto("Cliente: " + cliente.getNombre(), 40, 515);
		escribeTexto("Telefono: " + cliente.getTelefono(), 370, 515);
		escribeTexto("Domicilio: " + cliente.getDireccion(), 650, 515);

		contentStream.drawLine(10,505,830,505);

		Set<Medida> medidasCerca = new HashSet<>(Set.of()),
				medidasLejos = new HashSet<>(Set.of()),
				medidasPolifocal = new HashSet<>(Set.of());
		Armazon armazonCerca = new Armazon(),
				armazonLejos = new Armazon();

		for (Lente lente : lentes){
			if (lente.getEsCerca()) {
				medidasCerca.addAll(lente.getMedidasLentes());
				armazonCerca = lente.getArmazon();
			}

			if (lente.getEsLejos()) {
				medidasLejos.addAll(lente.getMedidasLentes());
				armazonLejos = lente.getArmazon();
			}

			if (lente.getEsBifocal()){
				dibujaPolifocal(0);
				medidasPolifocal.addAll(lente.getMedidasLentes());
			}

			if (lente.getEsMultifocal()){
				dibujaPolifocal(1);
				medidasPolifocal.addAll(lente.getMedidasLentes());
			}
		}

		Integer[] preciosMedidas = new Integer[2];

		contentStream.setFont(PDType1Font.HELVETICA, 13);
		escribeTexto("Cerca", 405, 490);

		contentStream.setFont(PDType1Font.HELVETICA, 12);
		escribeOjos(45,440);
		dibujaCuadriculas(460, false, medidasCerca, preciosMedidas);
		escribeArmazon(380, armazonCerca);
		dibujaFinales(370, false, preciosMedidas);

		preciosMedidas = new Integer[2];

		contentStream.drawLine(55,365,780,365);

		contentStream.setFont(PDType1Font.HELVETICA, 13);
		escribeTexto("Lejos", 405, 350);

		contentStream.setFont(PDType1Font.HELVETICA, 12);
		escribeOjos(45,300);
		dibujaCuadriculas(320, false, medidasLejos, preciosMedidas);
		escribeArmazon(240, armazonLejos);
		dibujaFinales(230, false, preciosMedidas);

		preciosMedidas = new Integer[2];

		contentStream.drawLine(55,225,780,225);

		escribeOjos(260,170);
		dibujaCuadriculas(190, true, medidasPolifocal, preciosMedidas);
		dibujaFinales(140, true, preciosMedidas);

		contentStream.drawLine(55,115,780,115);

		preciosMedidas = new Integer[2];
		preciosMedidas[0] = 0;
		preciosMedidas[1] = 0;

		dibujaFinales(15, false, preciosMedidas);
		escribeTextoFinales(totales);

		contentStream.close();
		document.save("C:\\RAMIRO\\Programacion\\Java\\Proyectos\\CentroOpticoMarcosWeb\\src\\main\\resources\\fichas\\Ficha-" + idFicha + ".pdf");
		document.close();
	}

	private static void escribeOjos(Integer posX, Integer posY) throws IOException {
		escribeTexto("Ojo Derecho", posX, posY);
		escribeTexto("Ojo Izquierdo", posX, posY-30);
	}

	private static void escribeCuadricula(Integer posicionVector, Integer posX, Integer posY, Medida medida) throws IOException{
		float ancho = 0;

		switch(posicionVector){
			case 0:
				ancho = calculaAnchoTexto(medida.getEsferico().toString());
				escribeTexto(medida.getEsferico().toString(), (int) (posX - ancho), posY);
				break;
			case 1:
				ancho = calculaAnchoTexto(medida.getCilindrico().toString());
				escribeTexto(medida.getCilindrico().toString(), (int) (posX - ancho), posY);
				break;
			case 2:
				ancho = calculaAnchoTexto(medida.getEje().toString());
				escribeTexto(medida.getEje().toString(), (int) (posX - ancho), posY);
				break;
			case 3:
				ancho = calculaAnchoTexto(medida.getDistanciaIntercupilar().toString());
				escribeTexto(medida.getDistanciaIntercupilar().toString(), (int) (posX - ancho), posY);
				break;
			case 4:
				ancho = calculaAnchoTexto(medida.getAltura().toString());
				escribeTexto(medida.getAltura().toString(), (int) (posX - ancho), posY);
				break;
			case 5:
				contentStream.addRect(posX + 30 - vectorWidth[posicionVector], posY, 10, 10);
				if (medida.getEsMineral()) {
					contentStream.setNonStrokingColor(Color.BLACK);
					contentStream.fill();
				}else{
					contentStream.setStrokingColor(Color.BLACK);
					contentStream.stroke();
				}
				break;
			case 6:
				contentStream.addRect(posX + 30 - vectorWidth[posicionVector], posY, 10, 10);
				if (medida.getEsOrganico()) {
					contentStream.setNonStrokingColor(Color.BLACK);
					contentStream.fill();
				}else{
					contentStream.setStrokingColor(Color.BLACK);
					contentStream.stroke();
				}
				break;
			case 7:
				escribeTexto(medida.getCristal().getNombre(), posX + 10 - vectorWidth[posicionVector], posY);
				break;
		}
	}

	private static void dibujaCuadriculas(Integer posY, Boolean esPolifocal, Set<Medida> medidas, Integer[] preciosMedidas) throws IOException {

		Medida[] medida = new Medida[2];

		medida[0] = medidas.stream().filter(Medida::getEsOjoDerecho).findAny().get();
		preciosMedidas[0] = medida[0].getPrecio();

		medida[1] = medidas.stream().filter(Predicate.not(Medida::getEsOjoDerecho)).findAny().get();
		preciosMedidas[1] = medida[1].getPrecio();

		Integer posX = 130;
		Integer alto = 20;

		contentStream.setStrokingColor(Color.BLACK);

		for (int j = 0; j<3; j++){
			for(int i = 0; i<8; i++){
				if(esPolifocal && i <= 2){
					posX = posX + vectorWidth[i];
					continue;
				}

				if(i == 3 || i == 5)
					posX += 5;

				if (esPolifocal && i >= 5){
					if (j == 2){
						continue;
					}else{
						if (i==5)
							posY = posY - 20;
					}
				}

				contentStream.addRect(posX, posY, vectorWidth[i] ,alto);
				contentStream.stroke();

				switch (j){
					case 0:
						escribeTexto(vectorTitulos[i], posX + 5, posY + 5);
						break;
					case 1:
						escribeCuadricula(i,posX - 5 + vectorWidth[i],posY + 10, medida[0]);
						break;
					case 2:
						escribeCuadricula(i,posX - 5 + vectorWidth[i],posY + 10, medida[1]);
						break;
				}

				posX = posX + vectorWidth[i];
			}

			if(j < 2){
				if(esPolifocal)
					posY = posY + 20;
				if(j == 0)
					alto = alto + 10;
			}
			posX = 130;
			posY = posY - alto;
		}
	}

	private static void escribeArmazon(Integer posY, Armazon armazon) throws IOException {
		float anchoTexto = 0;

		escribeTexto("Armazon: " + armazon.getNombre(), 390, posY);

		anchoTexto = calculaAnchoTexto(String.format("%,d",armazon.getPrecio()));
		escribeTexto(String.format("%,d",armazon.getPrecio()), (int) (792 - anchoTexto), posY);
	}

	private static void dibujaPolifocal(Integer esBifocal) throws IOException {
		Integer posY = 170;
		String titulo = "BIFOCAL";

		for (int i = 0; i<2; i++){
			contentStream.setFont(PDType1Font.HELVETICA, 12);
			escribeTexto(titulo, 80, posY + 3);

			contentStream.addRect(60, posY, 15, 15);
			if (i == esBifocal) {
				contentStream.setNonStrokingColor(Color.BLACK);
				contentStream.fill();
			}else{
				contentStream.setStrokingColor(Color.BLACK);
				contentStream.stroke();
			}

			posY = posY - 30;
			titulo ="MULTIFOCAL";
		}
	}

	private static void dibujaFinales(Integer posY, Boolean esPolifocal, Integer[] preciosMedidas) throws IOException {
		Integer adicionalPosY = 10;
		Integer cantidadIteracion = 3;
		float ancho = 0;

		if (esPolifocal) {
			cantidadIteracion = 1;
			adicionalPosY = -20;
		}

		contentStream.setStrokingColor(Color.BLACK);

		for(int i = 0; i<cantidadIteracion; i++){
			contentStream.addRect(735, posY,60 ,30);
			contentStream.stroke();

			posY = posY + 30;

			if (i == 2)
				continue;

			if (!preciosMedidas[i].toString().equals("0")){
				ancho = calculaAnchoTexto(String.format("%,d",preciosMedidas[i]));
				escribeTexto(String.format("%,d",preciosMedidas[i]), (int) (792 - ancho), posY + adicionalPosY);
			}
		}
	}

	private static void escribeTextoFinales(Integer[] totales) throws IOException {
		float anchoTexto = 0;

		escribeTexto("Seña", 690, 55);
		anchoTexto = calculaAnchoTexto(String.format("%,d",totales[0]));
		escribeTexto(String.format("%,d",totales[0]), (int) (792 - anchoTexto), 55);

		escribeTexto("Saldo", 690, 25);
		anchoTexto = calculaAnchoTexto(String.format("%,d",totales[1]));
		escribeTexto(String.format("%,d",totales[1]), (int) (792 - anchoTexto), 25);

		contentStream.setFont(PDType1Font.HELVETICA_BOLD, 12);
		escribeTexto("Total", 690, 85);
		anchoTexto = calculaAnchoTexto(String.format("%,d",totales[2]));
		escribeTexto(String.format("%,d",totales[2]) , (int) (792 - anchoTexto), 85);
	}

	private static void escribeTexto(String texto, Integer posX, Integer posY) throws IOException {
		contentStream.beginText();
		contentStream.newLineAtOffset(posX, posY);
		contentStream.showText(texto);
		contentStream.endText();
	}

	private static float calculaAnchoTexto(String texto) throws IOException {
        return (PDType1Font.HELVETICA.getStringWidth(texto) / 1000.0f) * 12;
	}
}
