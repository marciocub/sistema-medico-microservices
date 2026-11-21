package com.sistema.orquestador.controller;

import com.sistema.orquestador.config.MicroserviceConfig;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/turnos")
public class TurnosOrquestadorController {

    private final RestTemplate restTemplate;
    private final MicroserviceConfig config;

    public TurnosOrquestadorController(RestTemplate restTemplate, MicroserviceConfig config) {
        this.restTemplate = restTemplate;
        this.config = config;
    }

    @PostMapping
    public ResponseEntity<?> crearTurno(@RequestBody Map<String, Object> request) {
        String url = config.getTurnosUrl() + "/api/turnos";
        try {
            return restTemplate.postForEntity(url, request, Map.class);
        } catch (Exception e) {
            return ResponseEntity.status(500).body(Map.of("error", "Error de conexión con servicio de turnos"));
        }
    }

    @GetMapping
    public ResponseEntity<?> listarTurnos() {
        String url = config.getTurnosUrl() + "/api/turnos";
        try {
            return restTemplate.getForEntity(url, List.class);
        } catch (Exception e) {
            return ResponseEntity.status(500).body(Map.of("error", "Error de conexión con servicio de turnos"));
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> obtenerTurno(@PathVariable Long id) {
        String url = config.getTurnosUrl() + "/api/turnos/" + id;
        try {
            return restTemplate.getForEntity(url, Map.class);
        } catch (Exception e) {
            return ResponseEntity.status(500).body(Map.of("error", "Error de conexión con servicio de turnos"));
        }
    }

    @GetMapping("/paciente/{pacienteId}")
    public ResponseEntity<?> listarPorPaciente(@PathVariable Long pacienteId) {
        String url = config.getTurnosUrl() + "/api/turnos/paciente/" + pacienteId;
        try {
            return restTemplate.getForEntity(url, List.class);
        } catch (Exception e) {
            return ResponseEntity.status(500).body(Map.of("error", "Error de conexión con servicio de turnos"));
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> cancelarTurno(@PathVariable Long id) {
        String url = config.getTurnosUrl() + "/api/turnos/" + id;
        try {
            restTemplate.delete(url);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.status(500).body(Map.of("error", "Error de conexión con servicio de turnos"));
        }
    }
}
