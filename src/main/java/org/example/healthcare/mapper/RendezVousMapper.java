package org.example.healthcare.mapper;

import org.example.healthcare.dto.RendezVousDto;
import org.example.healthcare.entity.MedecinEntity;
import org.example.healthcare.entity.RendezVousEntity;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface RendezVousMapper {

    @Mapping(source = "patient.id", target="patientId")
    @Mapping(source = "medecin",target = "medecinId")
    RendezVousDto toDto(RendezVousEntity entity);

    @Mapping(source = "patient.id", target="patientId")
    @Mapping(source = "medecin",target = "medecinId")
    RendezVousEntity toEntity(RendezVousDto dto);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEntityFromDto(RendezVousDto dto, @MappingTarget RendezVousDto rendezVousDto);


}
