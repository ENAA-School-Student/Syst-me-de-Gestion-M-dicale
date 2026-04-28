package org.example.healthcare.mapper;

import org.example.healthcare.dto.DossierRequest;
import org.example.healthcare.dto.DossierResponse;
import org.example.healthcare.entity.DossierMedicalEntity;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring")
public interface DossierMedicalMapper {


    DossierMedicalEntity toEntity(DossierRequest request);

    @Mapping(source = "patient.id",target = "patientId")
    @Mapping(source = "patient.nom",target = "patientNom")
    @Mapping(source = "patient.prenom",target = "patientPrenom")
    DossierResponse toResponse(DossierMedicalEntity entity);

    List<DossierResponse> toResponseList(List<DossierMedicalEntity> entities);

}
