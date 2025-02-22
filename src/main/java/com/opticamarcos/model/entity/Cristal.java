package com.opticamarcos.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="cristales")
@JsonIgnoreProperties(value = "medidas")
public class Cristal {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="id_cristal")
	private Integer id;

	@Column(nullable = false, length = 50)
	private String nombre;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "cristal")
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	private List<Medida> medidas = new ArrayList<>();
}
