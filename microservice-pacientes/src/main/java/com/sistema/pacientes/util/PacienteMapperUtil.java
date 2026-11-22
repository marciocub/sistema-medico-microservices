package com.sistema.pacientes.util;

import com.sistema.pacientes.dto.PacienteRequest;
import com.sistema.pacientes.dto.PacienteResponse;
import com.sistema.pacientes.entity.PacienteEntity;

import java.time.LocalDate;

public class PacienteMapperUtil {

    public static PacienteResponse toResponse(PacienteEntity entity) {
        if (entity == null) {
            return null;
        }
        LocalDate fechaNacimiento = null;
        if (entity.getCreatedAt() != null) {
            fechaNacimiento = entity.getCreatedAt().toLocalDate();
        }
        return new PacienteResponse(
            entity.getId(),
            entity.getId(),  // usuarioId usa el mismo id por diseño
            entity.getNombre(),
            entity.getApellido(),
            entity.getDocumento(),  // mapeado desde dni del request
            fechaNacimiento,
            entity.getTelefono(),
            "",  // direccion no existe en la entidad, usar vacío
            entity.isActivo()
        );
    }

    public static PacienteEntity toEntity(PacienteRequest request) {
        PacienteEntity entity = new PacienteEntity();
        entity.setNombre(request.nombre());
        entity.setApellido(request.apellido());
        entity.setDocumento(request.dni());
        entity.setTelefono(request.telefono());
        entity.setActivo(true);
        return entity;
    }
}
