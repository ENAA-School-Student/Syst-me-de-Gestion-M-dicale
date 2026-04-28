package org.example.healthcare.mapper;

import org.example.healthcare.dto.RendezVousDto;
import org.example.healthcare.dto.RendezVousMedecinResponse;
import org.example.healthcare.dto.RendezVousPatientResponse;
import org.example.healthcare.dto.RendezVousResponse;
import org.example.healthcare.entity.MedecinEntity;
import org.example.healthcare.entity.RendezVousEntity;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring")
public interface RendezVousMapper {

    RendezVousResponse toResponse(RendezVousEntity entity);

    List<RendezVousResponse> toDtoList(List<RendezVousEntity> rendezVousEntities);


    RendezVousEntity toEntity(RendezVousDto dto);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEntityFromDto(RendezVousDto dto, @MappingTarget RendezVousEntity entity);

    List<RendezVousPatientResponse> toListRenderVousPatient(List<RendezVousEntity> entities);
    List<RendezVousMedecinResponse> toListRendezVousMedecin(List<RendezVousEntity> entities);


}
