package com.opticamarcos.mapper;

import com.opticamarcos.model.dto.ArmazonDTO;
import com.opticamarcos.model.entity.Armazon;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ArmazonMapper {

    Armazon dtoToEntity(ArmazonDTO armazonDTO);

}
