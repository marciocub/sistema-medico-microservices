package com.sistema.pacientes.internal.service;

import com.sistema.pacientes.*;
import com.sistema.pacientes.dto.*;
import com.sistema.pacientes.internal.entity.PacienteEntity;
import com.sistema.pacientes.internal.repository.PacienteRepository;
import com.sistema.pacientes.util.PacienteMapperUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PacienteServiceImpl implements PacientesApi {

    private final PacienteRepository pacienteRepository;

    public PacienteServiceImpl(PacienteRepository pacienteRepository) {
        this.pacienteRepository = pacienteRepository;
    }

    @Override
    @Transactional
    public PacienteResponse crearPaciente(PacienteRequest request) {
        PacienteEntity entity = PacienteEntity.builder()
                .usuarioId(null) // Se asignará cuando se cree el usuario en el servicio de seguridad
                .nombre(request.nombre())
                .apellido(request.apellido())
                .dni(request.dni())
                .fechaNacimiento(request.fechaNacimiento())
                .telefono(request.telefono())
                .direccion(request.direccion())
                .activo(true)
                .build();
        return PacienteMapperUtil.toResponse(pacienteRepository.save(entity));
    }

    @Override
    public PacienteResponse obtenerPacientePorId(Long id) {
        return pacienteRepository.findById(id)
                .map(PacienteMapperUtil::toResponse)
                .orElseThrow(() -> new IllegalArgumentException("Paciente no encontrado: " + id));
    }

    @Override
    public boolean existePacienteActivo(Long id) {
        return pacienteRepository.findById(id)
                .map(PacienteEntity::isActivo)
                .orElse(false);
    }

    @Override
    public java.util.List<PacienteResponse> listarPacientes() {
        return pacienteRepository.findAll().stream()
                .map(PacienteMapperUtil::toResponse)
                .toList();
    }
}
