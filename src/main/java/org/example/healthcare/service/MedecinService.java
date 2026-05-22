package org.example.healthcare.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.example.healthcare.dto.MedecinDto;
import org.example.healthcare.entity.MedecinEntity;
import org.example.healthcare.mapper.MedecinMapper;
import org.example.healthcare.mapper.RendezVousMapper;
import org.example.healthcare.repository.MedecinRepository;
import org.example.healthcare.repository.RendezVousRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MedecinService {

    private final MedecinRepository medecinRepository;
    private final MedecinMapper  medecinMapper;
    private final RendezVousMapper rendezVousMapper;
    private final RendezVousRepository rendezVousRepository;




    public MedecinDto ajouterMedecin(MedecinDto dto){
        MedecinEntity medecin=medecinMapper.toEntity(dto);
        MedecinEntity save=medecinRepository.save(medecin);
        return medecinMapper.toDto(save);
    }
    public MedecinDto modifierMedecin(Long id,MedecinDto dto){
        MedecinEntity medecin=medecinRepository.findById(id).orElse(null);
        medecinMapper.updateEntityFromDto(dto,medecin);
        return medecinMapper.toDto(medecinRepository.save(medecin));
    }

    public void supprimerMedecin(Long id){
        if (!medecinRepository.existsById(id)){
            throw new EntityNotFoundException("medecin intouvable avec l id"+id);
        }
        medecinRepository.deleteById(id);
    }

//    public List<MedecinDto> listerMedecins(){
//        return  medecinMapper.toDtoList(medecinRepository.findAll());
//    }


    public Page<MedecinDto> listerMedecins(int page,int size,String sortDir){
        Sort sort=sortDir.equalsIgnoreCase("asc")?Sort.by("specialite").ascending():Sort.by("specialite").ascending();
        Pageable pageable= PageRequest.of(page,size,sort);
        return medecinRepository.findAll(pageable).map(medecinMapper::toDto);
    }

    public Page<MedecinDto> rechercherParSpecialite(String specialite, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return medecinRepository.findBySpecialiteContainingIgnoreCase(specialite, pageable).map(medecinMapper::toDto);
    }
}
