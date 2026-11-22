package com.sistema.profesionales.service;

import com.sistema.profesionales.dto.EspecialidadRequest;
import com.sistema.profesionales.dto.EspecialidadResponse;
import com.sistema.profesionales.entity.EspecialidadEntity;
import com.sistema.profesionales.repository.EspecialidadRepository;
import com.sistema.profesionales.util.EspecialidadMapperUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class EspecialidadServiceImpl {

    private final EspecialidadRepository especialidadRepository;

    public EspecialidadServiceImpl(EspecialidadRepository especialidadRepository) {
        this.especialidadRepository = especialidadRepository;
    }

    public List<EspecialidadResponse> listarEspecialidades() {
        return especialidadRepository.findAll().stream()
                .map(EspecialidadMapperUtil::toResponse)
                .collect(Collectors.toList());
    }

    public EspecialidadResponse obtenerEspecialidadPorId(Long id) {
        return especialidadRepository.findById(id)
                .map(EspecialidadMapperUtil::toResponse)
                .orElseThrow(() -> new IllegalArgumentException("Especialidad no encontrada: " + id));
    }

    @Transactional
    public EspecialidadResponse crearEspecialidad(EspecialidadRequest request) {
        EspecialidadEntity entity = EspecialidadMapperUtil.toEntity(request);
        EspecialidadEntity saved = especialidadRepository.save(entity);
        return EspecialidadMapperUtil.toResponse(saved);
    }

    @Transactional
    public EspecialidadResponse actualizarEspecialidad(Long id, EspecialidadRequest request) {
        EspecialidadEntity entity = EspecialidadMapperUtil.toEntity(request);
        entity.setId(id);
        EspecialidadEntity saved = especialidadRepository.save(entity);
        return EspecialidadMapperUtil.toResponse(saved);
    }

    @Transactional
    public void eliminarEspecialidad(Long id) {
        especialidadRepository.deleteById(id);
    }
}