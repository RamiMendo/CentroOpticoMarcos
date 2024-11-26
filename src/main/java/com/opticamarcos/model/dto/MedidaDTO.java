package com.opticamarcos.model.dto;

import com.opticamarcos.model.entity.Cristal;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MedidaDTO {

    private Double esferico;
    private Double cilindrico;
    private Double eje;
    private Double distanciaIntercupilar;
    private Double altura;
    private Boolean esOrganico;
    private Boolean esMineral;
    private Integer precio;
    private Boolean esOjoDerecho;
    private Cristal cristal;
}
