package com.opticamarcos.mapper;

import com.opticamarcos.model.dto.ClienteDTO;
import com.opticamarcos.model.entity.Cliente;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ClienteMapper {

    Cliente dtoToEntity(ClienteDTO clienteDTO);

}
