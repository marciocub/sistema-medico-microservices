package com.sistema.turnos;

import com.sistema.turnos.dto.TurnoRequest;
import com.sistema.turnos.dto.TurnoResponse;

public interface TurnosApi {
    TurnoResponse crearTurno(TurnoRequest request);
    TurnoResponse obtenerTurnoPorId(Long id);
    java.util.List<TurnoResponse> listarTurnosPorPaciente(Long pacienteId);
    java.util.List<TurnoResponse> listarTurnos();
    void cancelarTurno(Long id);
}
