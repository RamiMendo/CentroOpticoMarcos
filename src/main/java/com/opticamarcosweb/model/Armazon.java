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
@Table(name="armazones")
public class Armazon {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="id_armazon")
	private Integer idArmazon;

	@Column(nullable = false, length = 80)
	private String nombre;

	@Column(nullable = false, name="precio_regular")
	private Double precioRegular;

	@Column(nullable = false, name="precio_con_desc")
	private Double precioConDesc;

	@Column(nullable = false, name="precio_tarjeta")
	private Double precioTarjeta;

	@OneToMany(mappedBy = "armazon", fetch = FetchType.LAZY)
	private Set<Lente> lentes = new HashSet<>();
	
}
