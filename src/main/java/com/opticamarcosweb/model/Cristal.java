package com.opticamarcosweb.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="cristales")
public class Cristal {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="id_cristal")
	private Integer idCristal;

	@Column(nullable = false, length = 50)
	private String nombre;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "cristal")
	private Set<MedidaLente> medidasLentes = new HashSet<>();

}
