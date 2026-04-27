package org.example.healthcare.mapper;

import org.example.healthcare.dto.RendezVousDto;
import org.example.healthcare.entity.MedecinEntity;
import org.example.healthcare.entity.RendezVousEntity;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring")
public interface RendezVousMapper {

    @Mapping(source = "patient.id", target = "patientId")
    @Mapping(source = "medecin.id", target = "medecinId")
    RendezVousDto toDto(RendezVousEntity entity);

    List<RendezVousDto> toDtoList(List<RendezVousEntity> rendezVousEntities);
    @Mapping(source = "patientId", target = "patient.id")
    @Mapping(source = "medecinId", target = "medecin.id")
    RendezVousEntity toEntity(RendezVousDto dto);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEntityFromDto(RendezVousDto dto, @MappingTarget RendezVousDto rendezVousDto);


}
