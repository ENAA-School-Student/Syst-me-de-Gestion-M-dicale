package org.example.healthcare.mapper;

import org.example.healthcare.dto.PatientDto;
import org.example.healthcare.dto.PatientRequestDto;
import org.example.healthcare.entity.PatientEntity;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface PatientMapper {


    PatientDto toDto(PatientEntity entity);
    
    @Mapping(target = "id", ignore = true)
    PatientEntity toEntity(PatientDto dto);

    PatientRequestDto toDtoRequest(PatientEntity entity);

    PatientEntity toEntityRequest(PatientRequestDto dto);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "id", ignore = true)
    void updateEntityFromDto(PatientDto dto, @MappingTarget PatientEntity patient);
}