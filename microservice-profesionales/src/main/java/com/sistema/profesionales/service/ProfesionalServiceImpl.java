package com.sistema.profesionales.service;

import com.sistema.profesionales.dto.ProfesionalRequest;
import com.sistema.profesionales.dto.ProfesionalResponse;
import com.sistema.profesionales.entity.ProfesionalEntity;
import com.sistema.profesionales.repository.ProfesionalRepository;
import com.sistema.profesionales.util.ProfesionalMapperUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class ProfesionalServiceImpl {

    private final ProfesionalRepository profesionalRepository;

    public ProfesionalServiceImpl(ProfesionalRepository profesionalRepository) {
        this.profesionalRepository = profesionalRepository;
    }

    public ProfesionalResponse crearProfesional(ProfesionalRequest request) {
        ProfesionalEntity entity = ProfesionalMapperUtil.toEntity(request);
        ProfesionalEntity saved = profesionalRepository.save(entity);
        return ProfesionalMapperUtil.toResponse(saved);
    }

    public ProfesionalResponse obtenerProfesionalPorId(Long id) {
        return profesionalRepository.findById(id)
                .map(ProfesionalMapperUtil::toResponse)
                .orElseThrow(() -> new IllegalArgumentException("Profesional no encontrado: " + id));
    }

    public List<ProfesionalResponse> listarProfesionales() {
        return profesionalRepository.findAll().stream()
                .map(ProfesionalMapperUtil::toResponse)
                .collect(Collectors.toList());
    }

    @Transactional
    public ProfesionalResponse actualizarProfesional(Long id, ProfesionalRequest request) {
        ProfesionalEntity entity = ProfesionalMapperUtil.toEntity(request);
        entity.setId(id);
        ProfesionalEntity saved = profesionalRepository.save(entity);
        return ProfesionalMapperUtil.toResponse(saved);
    }

    @Transactional
    public void eliminarProfesional(Long id) {
        profesionalRepository.deleteById(id);
    }

    public boolean existeProfesionalActivo(Long id) {
        return profesionalRepository.findById(id)
                .map(ProfesionalEntity::isActivo)
                .orElse(false);
    }

    public boolean existeEspecialidad(String especialidad) {
        return profesionalRepository.findAll().stream()
                .anyMatch(p -> p.getEspecialidad().equalsIgnoreCase(especialidad));
    }

    public List<EspecialidadResponse> listarEspecialidades() {
        return profesionalRepository.findAll().stream()
                .map(p -> new EspecialidadResponse(null, p.getEspecialidad()))
                .distinct()
                .collect(Collectors.toList());
    }
}