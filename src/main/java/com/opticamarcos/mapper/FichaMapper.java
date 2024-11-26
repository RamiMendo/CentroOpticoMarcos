package com.opticamarcos.mapper;

import com.opticamarcos.model.dto.FichaDTO;
import com.opticamarcos.model.entity.Ficha;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {LenteMapper.class})
public interface FichaMapper {

    Ficha dtoToEntity(FichaDTO fichaDTO);

}
