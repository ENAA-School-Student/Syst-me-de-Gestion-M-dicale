package org.example.healthcare.mapper;

import org.example.healthcare.dto.PatientDto;
import org.example.healthcare.entity.PatientEntity;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring")
public interface PatientMapper {

    PatientDto toDto(PatientEntity entity);

    PatientEntity toEntity(PatientDto dto);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEntityFromDto(PatientDto dto, @MappingTarget PatientEntity patient);
}
