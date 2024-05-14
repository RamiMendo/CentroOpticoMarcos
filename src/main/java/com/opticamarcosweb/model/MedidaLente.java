package com.opticamarcosweb.model;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
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
			
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="idLente")
	private Lente lente;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy="medidaLente", orphanRemoval=true,fetch = FetchType.LAZY)
	private Set<Cristal> cristales = new HashSet<Cristal>();	
	
	public void addCristales(Cristal cristal) {
		cristales.add(cristal);
	}
	
	public void removeCristales(Cristal cristal) {
		cristales.remove(cristal);
	}
}
