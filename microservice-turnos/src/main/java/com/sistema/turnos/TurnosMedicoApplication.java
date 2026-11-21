package com.sistema.turnos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@ComponentScan(basePackages = {
    "com.sistema.turnos",
    "com.sistema.seguridad",
    "com.sistema.pacientes",
    "com.sistema.profesionales"
})
@EnableJpaRepositories(basePackages = {
    "com.sistema.turnos.internal.repository",
    "com.sistema.seguridad.internal.repository",
    "com.sistema.pacientes.internal.repository",
    "com.sistema.profesionales.internal.repository"
})
@EntityScan(basePackages = {
    "com.sistema.turnos.internal.entity",
    "com.sistema.seguridad.internal.entity",
    "com.sistema.pacientes.internal.entity",
    "com.sistema.profesionales.internal.entity"
})
public class TurnosMedicoApplication {
    public static void main(String[] args) {
        SpringApplication.run(TurnosMedicoApplication.class, args);
    }
}
