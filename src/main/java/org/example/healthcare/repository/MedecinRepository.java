package org.example.healthcare.repository;

import org.example.healthcare.entity.MedecinEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
public interface MedecinRepository extends JpaRepository <MedecinEntity ,Long> {
 Page<MedecinEntity> findAll(Pageable pageable);
 }
