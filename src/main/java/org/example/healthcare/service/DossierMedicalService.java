package org.example.healthcare.service;

import lombok.RequiredArgsConstructor;
import org.example.healthcare.dto.DossierDto;
import org.example.healthcare.entity.DossierMedicalEntity;
import org.example.healthcare.entity.PatientEntity;
import org.example.healthcare.mapper.DossierMedicalMapper;
import org.example.healthcare.repository.DossierMedicalRepository;
import org.example.healthcare.repository.PatientRepository;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DossierMedicalService {


    private  final DossierMedicalMapper dossierMedicalMapper;
    private final DossierMedicalRepository dossierMedicalRepository;
    private final PatientRepository patientRepository;

    @CacheEvict(value = "Dossie", allEntries = true)
    public DossierDto CreerDossier(DossierDto request){
        PatientEntity patient=patientRepository.findById(request.getPatientId()).orElseThrow(()->new RuntimeException("patient note founde"));
        DossierMedicalEntity dossierMedical=dossierMedicalMapper.toEntity(request);
        dossierMedical.setPatient(patient);
        return dossierMedicalMapper.toDto(dossierMedicalRepository.save(dossierMedical));

    }
    @Cacheable(value = "RendezVous")
    public DossierDto consulterDossier(Long id){
        DossierMedicalEntity dossierMedical=dossierMedicalRepository.findById(id).orElseThrow(()->new RuntimeException("dossier not found"));
        return dossierMedicalMapper.toDto(dossierMedical);
    }



    @CacheEvict(value = "ajouterDiagnostic", allEntries = true)
    public DossierDto ajouterDiagnostic(Long id ,String diagnostic){
       DossierMedicalEntity entity=dossierMedicalRepository.findById(id).orElseThrow(()->new RuntimeException("dossier not found: "+id));
       entity.setDiagnostic(diagnostic);
       return dossierMedicalMapper.toDto(dossierMedicalRepository.save(entity));
    }
    @CacheEvict(value = "ajouterObservations", allEntries = true)
    public DossierDto ajouterObservations (Long id,String observations){
        DossierMedicalEntity entity=dossierMedicalRepository.findById(id).orElseThrow(()->new RuntimeException("dossier not found : "+id));
        entity.setObservations(observations);
        return dossierMedicalMapper.toDto(dossierMedicalRepository.save(entity));
    }


}
