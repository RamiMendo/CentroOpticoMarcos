package com.opticamarcos.mapper;

import com.opticamarcos.model.dto.MedidaDTO;
import com.opticamarcos.model.entity.Medida;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface MedidaMapper {

    Medida dtoToEntity(MedidaDTO medidaDTO);
    List<Medida> dtoListToEntity(List<MedidaDTO> medidaDTO);

}
