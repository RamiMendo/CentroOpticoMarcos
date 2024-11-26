package com.opticamarcosweb.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

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

    @Column(name = "habilitado", nullable = false)
    private Boolean enabled;

//    @Column(name = "token_expirado", nullable = false)
//    private boolean tokenExpirado;

//    @ManyToMany(fetch = FetchType.LAZY)
//    @JoinTable(
//            name = "usuarios_roles",
//            joinColumns = @JoinColumn(name = "id_usuario"),
//            inverseJoinColumns = @JoinColumn(name = "id_rol")
//    )
//    private List<Rol> listaRoles;


}
