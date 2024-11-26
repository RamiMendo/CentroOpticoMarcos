package com.opticamarcos.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ClienteDTO {

    private String nombre;
    private Long telefono;
    private String direccion;
}
