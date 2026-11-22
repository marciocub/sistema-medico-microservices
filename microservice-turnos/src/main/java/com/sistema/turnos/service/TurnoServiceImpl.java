package com.sistema.turnos.service;

import com.sistema.turnos.dto.*;
import com.sistema.turnos.entity.TurnoEntity;
import com.sistema.turnos.repository.TurnoRepository;
import com.sistema.turnos.util.TurnoMapperUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TurnoServiceImpl implements TurnosApi {

    private final TurnoRepository turnoRepository;

    public TurnoServiceImpl(TurnoRepository turnoRepository) {
        this.turnoRepository = turnoRepository;
    }

    @Override
    @Transactional
    public TurnoResponse crearTurno(TurnoRequest request) {
        // Validar disponibilidad del profesional (misma BD, misma consulta)
        if (!turnoRepository.findByProfesionalIdAndFechaHora(request.profesionalId(), request.fechaHora()).isEmpty()) {
            throw new IllegalArgumentException("El profesional no tiene disponibilidad en ese horario");
        }

        TurnoEntity entity = TurnoEntity.builder()
                .pacienteId(request.pacienteId())
                .profesionalId(request.profesionalId())
                .fechaHora(request.fechaHora())
                .motivo(request.motivo())
                .estado(EstadoTurno.PENDIENTE)
                .build();
        return TurnoMapperUtil.toResponse(turnoRepository.save(entity));
    }

    @Override
    public TurnoResponse obtenerTurnoPorId(Long id) {
        return turnoRepository.findById(id)
                .map(TurnoMapperUtil::toResponse)
                .orElseThrow(() -> new IllegalArgumentException("Turno no encontrado: " + id));
    }

    @Override
    public List<TurnoResponse> listarTurnosPorPaciente(Long pacienteId) {
        return turnoRepository.findAll().stream()
                .filter(t -> t.getPacienteId().equals(pacienteId))
                .map(TurnoMapperUtil::toResponse)
                .toList();
    }

    @Override
    public List<TurnoResponse> listarTurnos() {
        return turnoRepository.findAll().stream()
                .map(TurnoMapperUtil::toResponse)
                .toList();
    }

    @Override
    @Transactional
    public void cancelarTurno(Long id) {
        TurnoEntity turno = turnoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Turno no encontrado: " + id));
        turno.setEstado(EstadoTurno.CANCELADO);
        turnoRepository.save(turno);
    }
}