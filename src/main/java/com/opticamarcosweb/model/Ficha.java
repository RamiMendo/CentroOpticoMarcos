package com.opticamarcosweb.model;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

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
@Table(name="fichas")
public class Ficha {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id_ficha")
	private Long idFicha;

	@Column(nullable = false)
	private LocalDate fecha;

	@Column(nullable = false)
	private Double senia;

	@Column(nullable = false)
	private Double saldo;

	@Column(nullable = false)
	private Double total;
	
	@OneToOne
	@JoinColumn(name = "id_cliente")
	private Cliente cliente;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private Set<Lente> lentes = new HashSet<Lente>();

}
