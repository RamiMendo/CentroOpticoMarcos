package com.opticamarcosweb.model;

import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="medidas_lentes")
public class MedidaLente {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="id_medida_lente")
	private Integer idMedidaLente;

	@Column(nullable = false)
	private Double esferico;

	@Column(nullable = false)
	private Double cilindrico;

	@Column(nullable = false)
	private Double eje;

	@Column(nullable = false, name="distancia_intercupilar")
	private Double distanciaIntercupilar;

	@Column(nullable = false)
	private Double altura;

	@Column(nullable = false, name="es_organico")
	private Boolean esOrganico;

	@Column(nullable = false, name="es_mineral")
	private Boolean esMineral;

	@Column(nullable = false)
	private Double precio;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_cristal")
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"}) //Notacion para que ignore el cuerpo en la otra entidad
	private Cristal cristal;

}
