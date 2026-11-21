package com.sistema.orquestador.controller;

import com.sistema.orquestador.config.MicroserviceConfig;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/auth")
public class AuthOrquestadorController {

    private final RestTemplate restTemplate;
    private final MicroserviceConfig config;

    public AuthOrquestadorController(RestTemplate restTemplate, MicroserviceConfig config) {
        this.restTemplate = restTemplate;
        this.config = config;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> credentials, HttpServletResponse response) {
        String url = config.getSeguridadUrl() + "/api/auth/login";
        try {
            ResponseEntity<Map> result = restTemplate.postForEntity(url, credentials, Map.class);
            // Copiar cookies de respuesta
            List<String> setCookieHeaders = result.getHeaders().get("Set-Cookie");
            if (setCookieHeaders != null) {
                for (String setCookie : setCookieHeaders) {
                    if (setCookie.startsWith("SESSION_TOKEN=")) {
                        String token = setCookie.split(";")[0].split("=")[1];
                        Cookie cookie = new Cookie("SESSION_TOKEN", token);
                        cookie.setHttpOnly(true);
                        cookie.setSecure(false);
                        cookie.setPath("/");
                        cookie.setMaxAge(86400);
                        response.addCookie(cookie);
                    }
                }
            }
            return ResponseEntity.status(result.getStatusCode()).body(result.getBody());
        } catch (Exception e) {
            return ResponseEntity.status(500).body(Map.of("error", "Error de conexión con servicio de seguridad"));
        }
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logout(HttpServletRequest request, HttpServletResponse response) {
        String url = config.getSeguridadUrl() + "/api/auth/logout";
        try {
            restTemplate.postForObject(url, Map.of(), Map.class);
        } catch (Exception e) {
            // Ignorar errores del servicio backend, limpiar cookie local
        }
        Cookie cookie = new Cookie("SESSION_TOKEN", "");
        cookie.setHttpOnly(true);
        cookie.setSecure(false);
        cookie.setPath("/");
        cookie.setMaxAge(0);
        response.addCookie(cookie);
        return ResponseEntity.ok(Map.of("message", "Logout exitoso"));
    }

    @GetMapping("/me")
    public ResponseEntity<?> me(HttpServletRequest request) {
        String url = config.getSeguridadUrl() + "/api/auth/me";
        try {
            // Forward cookies
            HttpHeaders headers = new HttpHeaders();
            Cookie[] cookies = request.getCookies();
            if (cookies != null) {
                String cookieHeader = Arrays.stream(cookies)
                        .map(c -> c.getName() + "=" + c.getValue())
                        .collect(Collectors.joining("; "));
                headers.set("Cookie", cookieHeader);
            }
            HttpEntity<Void> entity = new HttpEntity<>(headers);
            ResponseEntity<Map> result = restTemplate.exchange(url, HttpMethod.GET, entity, Map.class);
            return ResponseEntity.status(result.getStatusCode()).body(result.getBody());
        } catch (Exception e) {
            return ResponseEntity.status(401).body(Map.of("error", "No autenticado"));
        }
    }
}
