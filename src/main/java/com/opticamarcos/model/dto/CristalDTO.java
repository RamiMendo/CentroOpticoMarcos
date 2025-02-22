package com.opticamarcos.model.dto;

import com.opticamarcos.model.entity.Medida;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class CristalDTO {
    private String nombre;
    private List<Medida> medidas;
}
