package com.sistema.profesionales.dto;

public record ProfesionalResponse(
    Long id,
    Long usuarioId,
    String nombre,
    String apellido,
    String matricula,
    String especialidad,
    boolean activo
) {}
