package com.sistema.profesionales.util;

import com.sistema.profesionales.dto.EspecialidadResponse;
import com.sistema.profesionales.entity.EspecialidadEntity;

public class EspecialidadMapperUtil {

    public static EspecialidadResponse toResponse(EspecialidadEntity entity) {
        if (entity == null) {
            return null;
        }
        return new EspecialidadResponse(
            entity.getId(),
            entity.getNombre()
        );
    }
}
