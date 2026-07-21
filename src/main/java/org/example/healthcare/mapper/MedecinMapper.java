package org.example.healthcare.mapper;

import org.example.healthcare.dto.MedecinDto;
import org.example.healthcare.dto.MedecinRequestDto;
import org.example.healthcare.entity.MedecinEntity;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.List;

@Mapper(componentModel = "spring")
public interface MedecinMapper {
    @org.mapstruct.Mapping(target = "id", source = "id")
    MedecinDto toDto(MedecinEntity medecin);
    MedecinEntity toEntity(MedecinDto dto);

    MedecinRequestDto toDtoRequest(MedecinEntity entity);

   @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEntityFromDto(MedecinDto dto, @MappingTarget MedecinEntity medecin);


}
