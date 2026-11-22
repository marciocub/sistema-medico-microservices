package com.sistema.seguridad.util;

import com.sistema.seguridad.dto.UsuarioResponse;
import com.sistema.seguridad.entity.UsuarioEntity;

public class UsuarioMapperUtil {

    public static UsuarioResponse toResponse(UsuarioEntity entity) {
        if (entity == null) {
            return null;
        }
        return new UsuarioResponse(
            entity.getId(),
            entity.getUsername(),
            entity.getRol(),
            entity.isEnabled(),
            entity.getCreatedAt()
        );
    }
}
