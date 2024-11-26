package com.opticamarcosweb.model;

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
@Table(name="medidas")
public class Medida {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="id_medida")
	private Integer idMedida;

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
	private Integer precio;

	@Column(nullable = false)
	private Boolean esOjoDerecho;

    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"}) //Notacion para que ignore el cuerpo en la otra entidad
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_cristal", nullable = true)
	private Cristal cristal;
}
