package org.example.healthcare.service;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.example.healthcare.dto.PatientDto;
import org.example.healthcare.dto.PatientRequestDto;
import org.example.healthcare.entity.PatientEntity;
import org.example.healthcare.enums.Role;
import org.example.healthcare.mapper.PatientMapper;
import org.example.healthcare.repository.PatientRepository;

import org.springframework.data.domain.*;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@CacheConfig(cacheNames = "patients")
public class PatientService {

    private final PatientRepository patientRepository;
    private final PatientMapper patientMapper;

    private final PasswordEncoder passwordEncoder;

    @CacheEvict(value = "patients", allEntries = true)
    public PatientRequestDto ajouterPatient(PatientRequestDto dto){

        PatientEntity patient = new PatientEntity();
        patient.setUsername(dto.getUsername());
        patient.setPrenom(dto.getPrenom());
        patient.setEmail(dto.getEmail());
        patient.setTelephone(dto.getTelephone());
        patient.setDateNaissance(dto.getDateNaissance());
        String passwordChiffre = passwordEncoder.encode(dto.getPassword());
        patient.setPassword(passwordChiffre);
        patient.setRole(Role.PATIENT);
        PatientEntity savedPatient = patientRepository.save(patient);
        return patientMapper.toDtoRequest(savedPatient);
    }

    @CacheEvict(value = "patients", allEntries = true)
    public PatientDto modifierPatient(Long id,PatientDto dto){
        PatientEntity patient=patientRepository.findById(id).orElseThrow(()->new RuntimeException("patient not found"));
        patientMapper.updateEntityFromDto(dto,patient);
        return patientMapper.toDto(patientRepository.save(patient));

    }
    @CacheEvict(value = "patients", allEntries = true)
    public void SupprimerPatient(Long id){
        if (!patientRepository.existsById(id)){
            throw  new EntityNotFoundException("patient introuvable avec l'id :" + id);
        }
        patientRepository.deleteById(id);

    }
    @Cacheable(value = "patients", key = "#page + '-' + #size + '-' + #sortBy + '-' + #sortDercition")
    public Page<PatientDto> ListerPatients(int page,int size,String sortBy,String sortDercition){
        System.out.println("====== Appel à la base de données pour listerMedecins ======");
        System.out.println("====== ============================================== ======");
        System.out.println("====== Appel à la base de données pour listerMedecins ======");
        Sort sort= sortDercition.equalsIgnoreCase("asc")? Sort.by(sortBy).ascending(): Sort.by(sortBy).descending();
        Pageable pageable= PageRequest.of(page,size,sort);
        return patientRepository.findAll(pageable).map(patientMapper::toDto);
    }


    public PatientDto ConsulterPatient(Long id){
        PatientEntity patient=patientRepository.findById(id).orElse(null);
        return patientMapper.toDto(patient);
    }
    public Page<PatientDto> rechercherPatientParNom(String prenom, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);

        return patientRepository.findByPrenomContainingIgnoreCase(prenom, pageable).map(patientMapper::toDto);
    }

    @Cacheable(value = "patients")
    public List<PatientDto> testCache() {

        System.out.println("DB CALL");

        return patientRepository.findAll().stream().map(patientMapper::toDto).toList();
    }

}