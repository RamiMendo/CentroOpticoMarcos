package com.opticamarcosweb.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "usuarios")
@Entity
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_usuario")
    private Integer id;

    @Column(name = "usuario", nullable = false)
    private String userName;

    @Column(name = "contraseña", nullable = false)
    private String userPassword;

    @Column(name = "nombre", nullable = false)
    private String name;


}
