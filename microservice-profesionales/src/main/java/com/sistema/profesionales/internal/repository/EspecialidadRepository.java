package com.sistema.profesionales.internal.repository;

import com.sistema.profesionales.internal.entity.EspecialidadEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface EspecialidadRepository extends JpaRepository<EspecialidadEntity, Long> {
    Optional<EspecialidadEntity> findByNombre(String nombre);
    boolean existsByNombreIgnoreCase(String nombre);
}
