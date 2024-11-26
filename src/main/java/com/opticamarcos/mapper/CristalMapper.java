package com.opticamarcos.mapper;

import com.opticamarcos.model.dto.CristalDTO;
import com.opticamarcos.model.entity.Cristal;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CristalMapper {

    Cristal dtoToEntity(CristalDTO cristalDTO);

}
