package org.example.healthcare.mapper;

import org.example.healthcare.dto.DossierDto;
import org.example.healthcare.entity.DossierMedicalEntity;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface DossierMedicalMapper {

    DossierMedicalEntity toEntity(DossierDto request);

    @Mapping(source = "patient.id", target = "patientId")
    DossierDto toDto(DossierMedicalEntity entity);

}
