package com.sistema.profesionales.util;

import com.sistema.profesionales.dto.ProfesionalResponse;
import com.sistema.profesionales.entity.ProfesionalEntity;

public class ProfesionalMapperUtil {

    public static ProfesionalResponse toResponse(ProfesionalEntity entity) {
        if (entity == null) {
            return null;
        }
        return new ProfesionalResponse(
            entity.getId(),
            entity.getUsuarioId(),
            entity.getNombre(),
            entity.getApellido(),
            entity.getMatricula(),
            entity.getEspecialidad(),
            entity.isActivo()
        );
    }
}
