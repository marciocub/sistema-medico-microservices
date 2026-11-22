package com.sistema.pacientes.service;

import com.sistema.pacientes.dto.*;
import com.sistema.pacientes.entity.PacienteEntity;
import com.sistema.pacientes.repository.PacienteRepository;
import com.sistema.pacientes.util.PacienteMapperUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class PacienteServiceImpl {

    private final PacienteRepository pacienteRepository;

    public PacienteServiceImpl(PacienteRepository pacienteRepository) {
        this.pacienteRepository = pacienteRepository;
    }

    public PacienteResponse crearPaciente(PacienteRequest request) {
        PacienteEntity entity = PacienteMapperUtil.toEntity(request);
        PacienteEntity saved = pacienteRepository.save(entity);
        return PacienteMapperUtil.toResponse(saved);
    }

    public PacienteResponse obtenerPacientePorId(Long id) {
        return pacienteRepository.findById(id)
                .map(PacienteMapperUtil::toResponse)
                .orElseThrow(() -> new IllegalArgumentException("Paciente no encontrado: " + id));
    }

    public List<PacienteResponse> listarPacientes() {
        return pacienteRepository.findAll().stream()
                .map(PacienteMapperUtil::toResponse)
                .collect(Collectors.toList());
    }

    public PacienteResponse actualizarPaciente(Long id, PacienteRequest request) {
        PacienteEntity entity = PacienteMapperUtil.toEntity(request);
        entity.setId(id);
        PacienteEntity saved = pacienteRepository.save(entity);
        return PacienteMapperUtil.toResponse(saved);
    }

    public void eliminarPaciente(Long id) {
        pacienteRepository.deleteById(id);
    }
}