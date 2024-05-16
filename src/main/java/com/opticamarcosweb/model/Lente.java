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
@Table(name="lentes")
public class Lente {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="id_lente")
	private Integer idLente;
	@Column(nullable = false, name="es_lejos")
	private Boolean esLejos;
	@Column(nullable = false, name="es_cerca")
	private Boolean esCerca;
	@Column(nullable = false, name="es_bifocal")
	private Boolean esBifocal;
	@Column(nullable = false, name="es_multifocal")
	private Boolean esMultifocal;
	
//	@ManyToOne(cascade = CascadeType.ALL)
//	@JoinColumn(name="id_ficha")
//	private Ficha ficha;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy="lente", orphanRemoval=true,fetch = FetchType.LAZY)
	private Set<MedidaLente> medidasLentes = new HashSet<MedidaLente>();
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy="lente", orphanRemoval=true, fetch = FetchType.LAZY)
	private Set<Armazon> armazones = new HashSet<Armazon>();
	
	public void addMedidasLentes(MedidaLente medidaLente) {
		medidasLentes.add(medidaLente);
	}
	
	public void removeMedidasLentes(MedidaLente medidaLente) {
		medidasLentes.remove(medidaLente);
	}
	
	public void addArmazones(Armazon armazon) {
		armazones.add(armazon);
	}
	
	public void removeArmazones(Armazon armazon) {
		armazones.remove(armazon);
	}
		
}
