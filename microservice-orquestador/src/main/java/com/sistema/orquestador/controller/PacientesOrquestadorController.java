package com.sistema.orquestador.controller;

import com.sistema.orquestador.config.MicroserviceConfig;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/pacientes")
public class PacientesOrquestadorController {

    private final RestTemplate restTemplate;
    private final MicroserviceConfig config;

    public PacientesOrquestadorController(RestTemplate restTemplate, MicroserviceConfig config) {
        this.restTemplate = restTemplate;
        this.config = config;
    }

    @PostMapping("/registro")
    public ResponseEntity<?> registro(@RequestBody Map<String, Object> request) {
        String url = config.getPacientesUrl() + "/api/pacientes/registro";
        try {
            return restTemplate.postForEntity(url, request, Map.class);
        } catch (Exception e) {
            return ResponseEntity.status(500).body(Map.of("error", "Error de conexión con servicio de pacientes"));
        }
    }

    @GetMapping
    public ResponseEntity<?> listar() {
        String url = config.getPacientesUrl() + "/api/pacientes";
        try {
            return restTemplate.getForEntity(url, List.class);
        } catch (Exception e) {
            return ResponseEntity.status(500).body(Map.of("error", "Error de conexión con servicio de pacientes"));
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> obtener(@PathVariable Long id) {
        String url = config.getPacientesUrl() + "/api/pacientes/" + id;
        try {
            return restTemplate.getForEntity(url, Map.class);
        } catch (Exception e) {
            return ResponseEntity.status(500).body(Map.of("error", "Error de conexión con servicio de pacientes"));
        }
    }
}
