package com.sistema.pacientes;

import com.sistema.pacientes.dto.PacienteRequest;
import com.sistema.pacientes.dto.PacienteResponse;

public interface PacientesApi {
    PacienteResponse crearPaciente(PacienteRequest request);
    PacienteResponse obtenerPacientePorId(Long id);
    boolean existePacienteActivo(Long id);
    java.util.List<PacienteResponse> listarPacientes();
}
