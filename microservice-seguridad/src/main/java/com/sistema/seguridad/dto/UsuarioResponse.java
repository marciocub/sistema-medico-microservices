package com.sistema.seguridad.dto;

import java.time.LocalDateTime;

public record UsuarioResponse(
    String id,
    String username,
    String rol,
    boolean enabled,
    LocalDateTime createdAt
) {}
