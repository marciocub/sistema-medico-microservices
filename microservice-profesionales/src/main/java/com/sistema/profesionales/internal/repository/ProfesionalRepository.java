package com.sistema.profesionales.internal.repository;

import com.sistema.profesionales.internal.entity.ProfesionalEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface ProfesionalRepository extends JpaRepository<ProfesionalEntity, Long> {
    Optional<ProfesionalEntity> findByMatricula(String matricula);
    java.util.List<ProfesionalEntity> findByEspecialidad(String especialidad);
}
