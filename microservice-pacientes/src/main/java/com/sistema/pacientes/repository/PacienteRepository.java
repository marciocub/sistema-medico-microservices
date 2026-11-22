package com.sistema.pacientes.repository;

import com.sistema.pacientes.entity.PacienteEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface PacienteRepository extends JpaRepository<PacienteEntity, Long> {
    Optional<PacienteEntity> findByDocumento(String documento);
    Optional<PacienteEntity> findByEmail(String email);
}