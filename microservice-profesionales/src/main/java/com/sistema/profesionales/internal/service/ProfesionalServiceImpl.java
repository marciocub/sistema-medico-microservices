package com.sistema.profesionales.internal.service;

import com.sistema.profesionales.*;
import com.sistema.profesionales.dto.*;
import com.sistema.profesionales.internal.entity.ProfesionalEntity;
import com.sistema.profesionales.internal.repository.ProfesionalRepository;
import com.sistema.profesionales.util.ProfesionalMapperUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ProfesionalServiceImpl implements ProfesionalesApi {

    private final ProfesionalRepository profesionalRepository;

    public ProfesionalServiceImpl(ProfesionalRepository profesionalRepository) {
        this.profesionalRepository = profesionalRepository;
    }

    @Override
    @Transactional
    public ProfesionalResponse crearProfesional(ProfesionalRequest request) {
        ProfesionalEntity entity = ProfesionalEntity.builder()
                .usuarioId(null) // Se asignará cuando se cree el usuario en el servicio de seguridad
                .nombre(request.nombre())
                .apellido(request.apellido())
                .matricula(request.matricula())
                .especialidad(request.especialidad())
                .activo(true)
                .build();
        return ProfesionalMapperUtil.toResponse(profesionalRepository.save(entity));
    }

    @Override
    public ProfesionalResponse obtenerProfesionalPorId(Long id) {
        return profesionalRepository.findById(id)
                .map(ProfesionalMapperUtil::toResponse)
                .orElseThrow(() -> new IllegalArgumentException("Profesional no encontrado: " + id));
    }

    @Override
    public boolean existeProfesionalActivo(Long id) {
        return profesionalRepository.findById(id)
                .map(ProfesionalEntity::isActivo)
                .orElse(false);
    }

    @Override
    public boolean existeEspecialidad(String especialidad) {
        return profesionalRepository.findAll().stream()
                .anyMatch(p -> p.getEspecialidad().equalsIgnoreCase(especialidad));
    }

    @Override
    public java.util.List<ProfesionalResponse> listarProfesionales() {
        return profesionalRepository.findAll().stream()
                .map(ProfesionalMapperUtil::toResponse)
                .toList();
    }

    @Override
    public java.util.List<EspecialidadResponse> listarEspecialidades() {
        return profesionalRepository.findAll().stream()
                .map(p -> new EspecialidadResponse(null, p.getEspecialidad()))
                .distinct()
                .toList();
    }
}
