package com.opticamarcosweb.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="idLente")
	private Lente lente;
	
}
