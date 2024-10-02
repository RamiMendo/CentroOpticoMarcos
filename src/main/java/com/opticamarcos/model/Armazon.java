package com.opticamarcos.model;

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
@Table(name="armazones")
@JsonIgnoreProperties(value = "lentes")
public class Armazon {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="id_armazon")
	private Integer idArmazon;

	@Column(nullable = false, length = 80)
	private String nombre;

	@Column(nullable = false, name="precio")
	private Integer precio;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "armazon")
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	private List<Lente> lentes = new ArrayList<>();

}
