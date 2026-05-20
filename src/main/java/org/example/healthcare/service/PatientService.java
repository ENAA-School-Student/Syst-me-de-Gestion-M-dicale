package org.example.healthcare.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.example.healthcare.dto.PatientDto;
import org.example.healthcare.dto.RendezVousDto;
import org.example.healthcare.entity.PatientEntity;
import org.example.healthcare.mapper.PatientMapper;
import org.example.healthcare.mapper.RendezVousMapper;
import org.example.healthcare.repository.PatientRepository;
import org.example.healthcare.repository.RendezVousRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PatientService {

    private final PatientRepository patientRepository;
    private final PatientMapper patientMapper;


    public PatientDto ajouterPatient(PatientDto dto){
        PatientEntity patient=patientMapper.toEntity(dto);
        return patientMapper.toDto(patientRepository.save(patient));
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


}