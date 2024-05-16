package com.opticamarcosweb.model;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private Set<Cliente> clientes = new HashSet<Cliente>();
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy="ficha", orphanRemoval=true, fetch = FetchType.LAZY)
	private Set<Lente> lentes = new HashSet<Lente>();
	
//	public void addCliente(Cliente cliente) {
//		clientes.add(cliente);
//	}
//
//	public void removeCliente(Cliente cliente) {
//		clientes.remove(cliente);
//	}
//
//	public void addLente(Lente lente) {
//		lentes.add(lente);
//	}
//
//	public void removeLente(Lente lente) {
//		lentes.remove(lente);
//	}
	
}
