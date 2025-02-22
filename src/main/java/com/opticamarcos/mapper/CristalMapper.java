package com.opticamarcos.mapper;

import com.opticamarcos.model.dto.CristalDTO;
import com.opticamarcos.model.entity.Cristal;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {MedidaMapper.class})
public interface CristalMapper {

    Cristal dtoToEntity(CristalDTO cristalDTO);

}
