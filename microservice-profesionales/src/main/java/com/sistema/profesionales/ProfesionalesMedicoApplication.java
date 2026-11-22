package com.sistema.profesionales;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@ComponentScan(basePackages = {
    "com.sistema.profesionales"
})
@EnableJpaRepositories(basePackages = {
    "com.sistema.profesionales.repository"
})
@EntityScan(basePackages = {
    "com.sistema.profesionales.entity"
})
public class ProfesionalesMedicoApplication {
    public static void main(String[] args) {
        SpringApplication.run(ProfesionalesMedicoApplication.class, args);
    }
}
