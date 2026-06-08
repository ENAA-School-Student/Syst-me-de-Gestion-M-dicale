package org.example.healthcare.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.example.healthcare.dto.MedecinDto;
import org.example.healthcare.dto.MedecinRequestDto;
import org.example.healthcare.entity.MedecinEntity;
import org.example.healthcare.enums.Role;
import org.example.healthcare.mapper.MedecinMapper;
import org.example.healthcare.mapper.RendezVousMapper;
import org.example.healthcare.repository.MedecinRepository;
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
public class MedecinService {


    private final MedecinRepository medecinRepository;
    private final MedecinMapper  medecinMapper;
    private final RendezVousMapper rendezVousMapper;
    private final RendezVousRepository rendezVousRepository;
    private final PasswordEncoder passwordEncoder;




        public MedecinRequestDto ajouterMedecin(MedecinRequestDto dto){

            MedecinEntity medecin=new MedecinEntity();
            medecin.setUsername(dto.getUsername());
            medecin.setSpecialite(dto.getSpecialite());
            medecin.setEmail(dto.getEmail());
            medecin.setTelephone(dto.getTelephone());
            String chiffremtPassword=passwordEncoder.encode(dto.getPassword());
            medecin.setPassword(chiffremtPassword);
            medecin.setRole(Role.MEDECIN);
            MedecinEntity saveMedecin=medecinRepository.save(medecin);

            return medecinMapper.toDtoRequest(saveMedecin);
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
