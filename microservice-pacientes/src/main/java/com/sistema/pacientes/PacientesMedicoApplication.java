package com.sistema.pacientes;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@ComponentScan(basePackages = {
    "com.sistema.pacientes"
})
@EnableJpaRepositories(basePackages = {
    "com.sistema.pacientes.internal.repository"
})
@EntityScan(basePackages = {
    "com.sistema.pacientes.internal.entity"
})
public class PacientesMedicoApplication {
    public static void main(String[] args) {
        SpringApplication.run(PacientesMedicoApplication.class, args);
    }
}
