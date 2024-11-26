package com.opticamarcos.model.dto;

import com.opticamarcos.model.entity.Cliente;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Set;

@Data
@AllArgsConstructor
public class FichaDTO {

    private Integer senia;
    private Cliente cliente;
    private Set<LenteDTO> lentes;

}
