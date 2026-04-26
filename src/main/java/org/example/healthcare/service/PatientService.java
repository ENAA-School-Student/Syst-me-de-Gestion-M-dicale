package org.example.healthcare.service;

import lombok.RequiredArgsConstructor;
import org.example.healthcare.dto.PatientDto;
import org.example.healthcare.entity.PatientEntity;
import org.example.healthcare.mapper.PatientMapper;
import org.example.healthcare.repository.PatientRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PatientService {

    private final PatientRepository patientRepository;
    private final PatientMapper patientMapper;

    public PatientDto ajouterPatient(PatientDto dto){
        PatientEntity patient=patientMapper.toEntity(dto);
        return patientMapper.toDto(patientRepository.save(patient));
    }
}
