package com.sistema.orquestador.controller;

import com.sistema.orquestador.config.MicroserviceConfig;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@RestController
@RequestMapping("/api/administradores")
public class AdministradoresOrquestadorController {

    private final RestTemplate restTemplate;
    private final MicroserviceConfig config;

    public AdministradoresOrquestadorController(RestTemplate restTemplate, MicroserviceConfig config) {
        this.restTemplate = restTemplate;
        this.config = config;
    }

    @PostMapping
    public ResponseEntity<?> crearAdministrador(@RequestBody Map<String, Object> request) {
        String url = config.getSeguridadUrl() + "/api/administradores";
        try {
            return restTemplate.postForEntity(url, request, Map.class);
        } catch (Exception e) {
            return ResponseEntity.status(500).body(Map.of("error", "Error de conexión con servicio de seguridad"));
        }
    }
}
