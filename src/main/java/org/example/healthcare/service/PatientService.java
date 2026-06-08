package org.example.healthcare.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.example.healthcare.dto.PatientDto;
import org.example.healthcare.dto.PatientRequestDto;
import org.example.healthcare.dto.RendezVousDto;
import org.example.healthcare.entity.PatientEntity;
import org.example.healthcare.enums.Role;
import org.example.healthcare.mapper.PatientMapper;
import org.example.healthcare.mapper.RendezVousMapper;
import org.example.healthcare.repository.PatientRepository;
import org.example.healthcare.repository.RendezVousRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PatientService {

    private final PatientRepository patientRepository;
    private final PatientMapper patientMapper;

    private final PasswordEncoder passwordEncoder;

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

    public PatientDto modifierPatient(Long id,PatientDto dto){
        PatientEntity patient=patientRepository.findById(id).orElseThrow(()->new RuntimeException("patient not found"));
        patientMapper.updateEntityFromDto(dto,patient);
        return patientMapper.toDto(patientRepository.save(patient));

    }
    public void SupprimerPatient(Long id){
        if (!patientRepository.existsById(id)){
            throw  new EntityNotFoundException("patient introuvable avec l'id :" + id);
        }
        patientRepository.deleteById(id);

    }

    public Page<PatientDto> ListerPatients(int page,int size,String sortBy,String sortDercition){

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

}