package org.example.healthcare.service;


import lombok.*;

import org.example.healthcare.dto.RendezVousDto;
import org.example.healthcare.dto.RendezVousMedecinResponse;
import org.example.healthcare.dto.RendezVousPatientResponse;
import org.example.healthcare.entity.MedecinEntity;
import org.example.healthcare.entity.PatientEntity;
import org.example.healthcare.entity.RendezVousEntity;
import org.example.healthcare.enums.StatutRendezVous;
import org.example.healthcare.mapper.RendezVousMapper;
import org.example.healthcare.repository.MedecinRepository;
import org.example.healthcare.repository.PatientRepository;
import org.example.healthcare.repository.RendezVousRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Getter
@Setter
@RequiredArgsConstructor
public class RendezVousService {

    private final RendezVousRepository rendezVousRepository;
    private  final RendezVousMapper rendezVousMapper;
    private  final MedecinRepository medecinRepository;
    private  final PatientRepository patientRepository;


    public RendezVousDto creerRendezVous(RendezVousDto dto ){

        MedecinEntity medecin=medecinRepository.findById(dto.getMedecinId()).orElseThrow(() -> new RuntimeException("Medecin not found"));;
        PatientEntity patient=patientRepository.findById(dto.getPatientId()).orElseThrow(() -> new RuntimeException("Patient not found"));;
        RendezVousEntity rendezVous = rendezVousMapper.toEntity(dto);
        rendezVous.setMedecin(medecin);
        rendezVous.setPatient(patient);
        return rendezVousMapper.toDto(rendezVousRepository.save(rendezVous));

    }

    public RendezVousDto modifierRendezVous(Long id,RendezVousDto dto){
        RendezVousEntity entity=rendezVousRepository.findById(id).orElseThrow(()->new RuntimeException("Ronder vous not found"));
        rendezVousMapper.updateEntityFromDto(dto,entity);
        return rendezVousMapper.toDto(rendezVousRepository.save(entity));
    }


    public Page<RendezVousDto> listerRendezVous(int page,int size,String sortBy,String sortDirection){
        Sort sort=sortDirection.equalsIgnoreCase("dec")? Sort.by(sortBy).descending():Sort.by(sortBy).descending();
        Pageable pageable= PageRequest.of(page,size,sort);
        return rendezVousRepository.findAll(pageable).map(rendezVousMapper::toDto);
    }
    public RendezVousDto AnnuleRendezVous(Long id){
        RendezVousEntity rendezVous=rendezVousRepository.findById(id).orElseThrow(()->new RuntimeException("Rendez vous not found"));
        rendezVous.setStatut(StatutRendezVous.ANNULE);
        return rendezVousMapper.toDto(rendezVousRepository.save(rendezVous));

    }

    public List<RendezVousPatientResponse> chercherPatient(Long patientId){
        return rendezVousMapper.toListRenderVousPatient(rendezVousRepository.findByPatientId(patientId));
    }

    public List<RendezVousMedecinResponse> chercherMedecin(Long medecinId){
        return rendezVousMapper.toListRendezVousMedecin(rendezVousRepository.findByMedecinId(medecinId));
    }

    public Page<RendezVousDto> rechercherParStatut(StatutRendezVous statut, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return rendezVousRepository.findByStatut(statut, pageable).map(rendezVousMapper::toDto);
    }
}
