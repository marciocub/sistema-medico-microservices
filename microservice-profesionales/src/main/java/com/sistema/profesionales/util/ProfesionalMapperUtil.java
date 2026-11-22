package com.sistema.profesionales.util;

import com.sistema.profesionales.dto.EspecialidadResponse;
import com.sistema.profesionales.dto.ProfesionalRequest;
import com.sistema.profesionales.dto.ProfesionalResponse;
import com.sistema.profesionales.entity.ProfesionalEntity;

public class ProfesionalMapperUtil {

    public static ProfesionalResponse toResponse(ProfesionalEntity entity) {
        if (entity == null) {
            return null;
        }
        return new ProfesionalResponse(
            entity.getId(),
            entity.getId(),  // usuarioId usa el mismo id por diseño
            entity.getNombre(),
            entity.getApellido(),
            entity.getMatricula(),
            entity.getEspecialidad(),
            entity.isActivo()
        );
    }

    public static ProfesionalEntity toEntity(ProfesionalRequest request) {
        ProfesionalEntity entity = new ProfesionalEntity();
        entity.setNombre(request.nombre());
        entity.setApellido(request.apellido());
        entity.setMatricula(request.matricula());
        entity.setEspecialidad(request.especialidad());
        entity.setActivo(true);
        return entity;
    }
}
