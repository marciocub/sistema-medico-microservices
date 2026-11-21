package com.sistema.turnos.config;

import org.springframework.context.annotation.Configuration;

/**
 * DataSeeder simplificado para modulo-turnos.
 * La creación de especialidades por defecto se realiza en el servicio de profesionales.
 * Cada servicio es independiente y no puede acceder a clases de otros servicios.
 */
@Configuration
public class DataSeeder {
    // Sin beans de inicialización por ahora - cada servicio gestiona sus propios datos maestros
}
