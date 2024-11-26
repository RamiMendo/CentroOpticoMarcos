package com.opticamarcos.model.dto;

import com.opticamarcos.model.entity.Armazon;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Set;

@Data
@AllArgsConstructor
public class LenteDTO {

    private Boolean esLejos;
    private Boolean esCerca;
    private Boolean esBifocal;
    private Boolean esMultifocal;
    private Set<MedidaDTO> medidasLentes;
    private Armazon armazon;
}
