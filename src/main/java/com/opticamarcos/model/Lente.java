package com.opticamarcosweb.model;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinTable(
			name = "lentes_medidas",
			joinColumns = @JoinColumn(name = "id_lente"),
			inverseJoinColumns = @JoinColumn(name = "id_medida")
	)
	private Set<Medida> medidasLentes = new HashSet<>();

	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_armazon", nullable = true)
	private Armazon armazon;

}
