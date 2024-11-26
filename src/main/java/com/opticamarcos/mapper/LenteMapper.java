package com.opticamarcos.mapper;

import com.opticamarcos.model.dto.LenteDTO;
import com.opticamarcos.model.entity.Lente;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring", uses = {MedidaMapper.class})
public interface LenteMapper {

    Lente dtoToEntity(LenteDTO lenteDTO);
    List<Lente> dtoListToEntity(List<LenteDTO> lenteDTOList);

}
