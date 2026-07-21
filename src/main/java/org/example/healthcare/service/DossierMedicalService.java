package org.example.healthcare.service;

import lombok.RequiredArgsConstructor;
import org.example.healthcare.dto.DossierDto;
import org.example.healthcare.entity.DossierMedicalEntity;
import org.example.healthcare.entity.PatientEntity;
import org.example.healthcare.entity.UserEntity;
import org.example.healthcare.enums.Role;
import org.example.healthcare.mapper.DossierMedicalMapper;
import org.example.healthcare.repository.DossierMedicalRepository;
import org.example.healthcare.repository.PatientRepository;
import org.example.healthcare.repository.UserRepository;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DossierMedicalService {

    private final DossierMedicalMapper dossierMedicalMapper;
    private final DossierMedicalRepository dossierMedicalRepository;
    private final PatientRepository patientRepository;
    private final UserRepository userRepository;

    @CacheEvict(value = "Dossier", allEntries = true)
    public DossierDto CreerDossier(DossierDto request) {
        PatientEntity patient = patientRepository.findById(request.getPatientId())
                .orElseThrow(() -> new RuntimeException("Patient not found"));
        DossierMedicalEntity dossier = dossierMedicalMapper.toEntity(request);
        dossier.setPatient(patient);
        return dossierMedicalMapper.toDto( dossierMedicalRepository.save(dossier) );
    }

    @Cacheable(value = "Dossier")
    public DossierDto consulterDossier(Long id) {
        DossierMedicalEntity dossier = dossierMedicalRepository.findById(id).orElseThrow(() -> new RuntimeException("Dossier not found"));
        return dossierMedicalMapper.toDto(dossier);
    }

    @CacheEvict(value = "Dossier", allEntries = true)
    public DossierDto ajouterDiagnostic(Long id, String diagnostic) {
        DossierMedicalEntity dossier = dossierMedicalRepository.findById(id).orElseThrow(() -> new RuntimeException("Dossier not found"));
        dossier.setDiagnostic(diagnostic);
        return dossierMedicalMapper.toDto(
                dossierMedicalRepository.save(dossier)
        );
    }

    @CacheEvict(value = "Dossier", allEntries = true)
    public DossierDto ajouterObservations(Long id, String observations) {

        DossierMedicalEntity dossier = dossierMedicalRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Dossier not found"));

        dossier.setObservations(observations);

        return dossierMedicalMapper.toDto(
                dossierMedicalRepository.save(dossier)
        );
    }

    public List<DossierDto> getMesDossiers() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        UserEntity user = userRepository.findByEmail(email).orElseThrow(() -> new RuntimeException("Utilisateur introuvable"));
        List<DossierMedicalEntity> dossiers;
        if (user.getRole() == Role.ADMIN) {
            dossiers = dossierMedicalRepository.findAll();
        } else if (user.getRole() == Role.MEDECIN) {
            dossiers = dossierMedicalRepository.findAll();
        } else {
            dossiers = dossierMedicalRepository.findByPatientId(user.getId());
        }
        return dossiers.stream()
                .map(dossierMedicalMapper::toDto)
                .toList();
    }

    public List<DossierDto> getAllDossiers() {
        return dossierMedicalRepository.findAll()
                .stream()
                .map(dossierMedicalMapper::toDto)
                .toList();
    }
}