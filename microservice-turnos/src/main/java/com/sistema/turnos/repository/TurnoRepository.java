package com.sistema.turnos.repository;

import com.sistema.turnos.entity.TurnoEntity;
import java.time.LocalDateTime;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface TurnoRepository extends JpaRepository<TurnoEntity, Long> {
    List<TurnoEntity> findByPacienteId(Long pacienteId);
    List<TurnoEntity> findByProfesionalIdAndFechaHora(Long profesionalId, LocalDateTime fechaHora);
}