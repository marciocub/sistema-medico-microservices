package com.sistema.profesionales;

import com.sistema.profesionales.dto.ProfesionalRequest;
import com.sistema.profesionales.dto.ProfesionalResponse;
import com.sistema.profesionales.dto.EspecialidadResponse;

public interface ProfesionalesApi {
    ProfesionalResponse crearProfesional(ProfesionalRequest request);
    ProfesionalResponse obtenerProfesionalPorId(Long id);
    boolean existeProfesionalActivo(Long id);
    boolean existeEspecialidad(String especialidad);
    java.util.List<ProfesionalResponse> listarProfesionales();
    java.util.List<EspecialidadResponse> listarEspecialidades();
}
