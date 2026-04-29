package org.example.healthcare.mapper;

import org.example.healthcare.dto.RendezVousDto;
import org.example.healthcare.dto.RendezVousMedecinResponse;
import org.example.healthcare.dto.RendezVousPatientResponse;
import org.example.healthcare.entity.MedecinEntity;
import org.example.healthcare.entity.RendezVousEntity;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring")
public interface RendezVousMapper {


    @Mapping(source = "patient.id", target = "patientId")
    @Mapping(source = "medecin.id", target = "medecinId")
    RendezVousDto toDto(RendezVousEntity entity);

    @Mapping(source = "patientId", target = "patient.id")
    @Mapping(source = "medecinId", target = "medecin.id")
    RendezVousEntity toEntity(RendezVousDto dto);

    List<RendezVousDto> toDtoList(List<RendezVousEntity> rendezVousEntities);

    @Mapping(source = "patient.id", target = "patientId")
    RendezVousPatientResponse toPatientResponse(RendezVousEntity entity);
    List<RendezVousPatientResponse> toListRenderVousPatient(List<RendezVousEntity> rendezVousEntities);

    @Mapping(source = "medecin.id", target = "medecinId")
    RendezVousMedecinResponse toMedecinResponse(RendezVousEntity entity);
    List<RendezVousMedecinResponse> toListRendezVousMedecin(List<RendezVousEntity> rendezVousEntities);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEntityFromDto(RendezVousDto dto, @MappingTarget RendezVousEntity entity);



}
