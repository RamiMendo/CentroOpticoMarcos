package com.opticamarcos.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="clientes")
public class Cliente {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="id_cliente")
	private Integer idCliente;

	@Column(nullable = false, length = 50)
	private String nombre;

	@Column(nullable = false, length = 10)
	private Long telefono;

	@Column(nullable = false, length = 80)
	private String direccion;

}
