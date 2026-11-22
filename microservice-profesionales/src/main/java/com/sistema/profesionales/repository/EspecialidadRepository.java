package com.sistema.profesionales.repository;

import com.sistema.profesionales.entity.EspecialidadEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface EspecialidadRepository extends JpaRepository<EspecialidadEntity, Long> {
    Optional<EspecialidadEntity> findByNombre(String nombre);
}