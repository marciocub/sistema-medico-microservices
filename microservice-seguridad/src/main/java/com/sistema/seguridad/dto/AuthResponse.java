package com.sistema.seguridad.dto;

public record AuthResponse(
    String token,
    String username,
    String rol
) {}
