package com.sistema.pacientes.internal.repository;

import com.sistema.pacientes.internal.entity.PacienteEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface PacienteRepository extends JpaRepository<PacienteEntity, Long> {
    Optional<PacienteEntity> findByDni(String dni);
    boolean existsByUsuarioId(Long usuarioId);
}
