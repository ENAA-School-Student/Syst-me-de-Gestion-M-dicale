package org.example.healthcare.repository;

import org.example.healthcare.entity.MedecinEntity;
import org.springframework.data.jpa.repository.JpaRepository;
public interface MedecinRepository extends JpaRepository <MedecinEntity ,Long> {
 }
