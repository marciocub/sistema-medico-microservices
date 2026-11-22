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
    "com.sistema.turnos.repository",
    "com.sistema.seguridad.repository",
    "com.sistema.pacientes.repository",
    "com.sistema.profesionales.repository"
})
@EntityScan(basePackages = {
    "com.sistema.turnos.entity",
    "com.sistema.seguridad.entity",
    "com.sistema.pacientes.entity",
    "com.sistema.profesionales.entity"
})
public class TurnosMedicoApplication {
    public static void main(String[] args) {
        SpringApplication.run(TurnosMedicoApplication.class, args);
    }
}
