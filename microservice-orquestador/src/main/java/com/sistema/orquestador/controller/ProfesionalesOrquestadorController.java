package com.sistema.orquestador.controller;

import com.sistema.orquestador.config.MicroserviceConfig;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class ProfesionalesOrquestadorController {

    private final RestTemplate restTemplate;
    private final MicroserviceConfig config;

    public ProfesionalesOrquestadorController(RestTemplate restTemplate, MicroserviceConfig config) {
        this.restTemplate = restTemplate;
        this.config = config;
    }

    @GetMapping("/profesionales")
    public ResponseEntity<?> listarProfesionales() {
        String url = config.getProfesionalesUrl() + "/api/profesionales";
        try {
            return restTemplate.getForEntity(url, List.class);
        } catch (Exception e) {
            return ResponseEntity.status(500).body(Map.of("error", "Error de conexión con servicio de profesionales"));
        }
    }

    @GetMapping("/profesionales/{id}")
    public ResponseEntity<?> obtenerProfesional(@PathVariable Long id) {
        String url = config.getProfesionalesUrl() + "/api/profesionales/" + id;
        try {
            return restTemplate.getForEntity(url, Map.class);
        } catch (Exception e) {
            return ResponseEntity.status(500).body(Map.of("error", "Error de conexión con servicio de profesionales"));
        }
    }

    @GetMapping("/especialidades")
    public ResponseEntity<?> listarEspecialidades() {
        String url = config.getProfesionalesUrl() + "/api/especialidades";
        try {
            return restTemplate.getForEntity(url, List.class);
        } catch (Exception e) {
            return ResponseEntity.status(500).body(Map.of("error", "Error de conexión con servicio de profesionales"));
        }
    }

    @PostMapping("/medicos")
    public ResponseEntity<?> crearMedico(@RequestBody Map<String, Object> request) {
        String url = config.getProfesionalesUrl() + "/api/medicos";
        try {
            return restTemplate.postForEntity(url, request, Map.class);
        } catch (Exception e) {
            return ResponseEntity.status(500).body(Map.of("error", "Error de conexión con servicio de profesionales"));
        }
    }

    @PostMapping("/especialidades")
    public ResponseEntity<?> crearEspecialidad(@RequestBody Map<String, Object> request) {
        String url = config.getProfesionalesUrl() + "/api/especialidades";
        try {
            return restTemplate.postForEntity(url, request, Map.class);
        } catch (Exception e) {
            return ResponseEntity.status(500).body(Map.of("error", "Error de conexión con servicio de profesionales"));
        }
    }

    @PutMapping("/especialidades/{id}")
    public ResponseEntity<?> actualizarEspecialidad(@PathVariable Long id, @RequestBody Map<String, Object> request) {
        String url = config.getProfesionalesUrl() + "/api/especialidades/" + id;
        try {
            restTemplate.put(url, request);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.status(500).body(Map.of("error", "Error de conexión con servicio de profesionales"));
        }
    }

    @DeleteMapping("/especialidades/{id}")
    public ResponseEntity<?> eliminarEspecialidad(@PathVariable Long id) {
        String url = config.getProfesionalesUrl() + "/api/especialidades/" + id;
        try {
            restTemplate.delete(url);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.status(500).body(Map.of("error", "Error de conexión con servicio de profesionales"));
        }
    }
}
