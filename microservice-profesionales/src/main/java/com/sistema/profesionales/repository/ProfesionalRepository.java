package com.sistema.profesionales.repository;

import com.sistema.profesionales.entity.ProfesionalEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface ProfesionalRepository extends JpaRepository<ProfesionalEntity, Long> {
    Optional<ProfesionalEntity> findByMatricula(String matricula);
}